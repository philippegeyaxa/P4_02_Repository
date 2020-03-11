package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

    private static final String REGISTRATION_NUMBER_TEST_VALUE_ABCDEF = "ABCDEF";
	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() throws Exception{
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn(REGISTRATION_NUMBER_TEST_VALUE_ABCDEF);
        dataBasePrepareService.clearDataBaseEntries();
    }

    @AfterAll
    private static void tearDown(){

    }

    @Test
    public void givenVehicle_whenProcessIncomingVehicle_ticketIsRegisteredAndSpotIsSetUnavailable(){
    	// GIVEN
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        // WHEN
        parkingService.processIncomingVehicle();
        Ticket ticket = ticketDAO.getTicket(REGISTRATION_NUMBER_TEST_VALUE_ABCDEF);
        // THEN
        assertNotNull(ticket);
        assertFalse(ticket.getParkingSpot().isAvailable());
    }

    @Test
    public void givenVehicleInParking_whenProcessExitingVehicle_registeredFareIsCorrectAndSpotIsAvailable(){
    	// GIVEN
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        ParkingSpot parkingSpot = parkingService.getNextParkingNumberIfAvailable();
        parkingSpot.setAvailable(false);
        parkingSpotDAO.updateParking(parkingSpot);
        Date inTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // Let's say the vehicle entered 24 hours ago
        Ticket ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber(REGISTRATION_NUMBER_TEST_VALUE_ABCDEF);
        ticket.setPrice(0);
        ticket.setInTime(inTime);
        ticket.setOutTime(null);
        ticketDAO.saveTicket(ticket);
        // WHEN
        parkingService.processExitingVehicle();
        Ticket ticketOut = ticketDAO.getTicket(REGISTRATION_NUMBER_TEST_VALUE_ABCDEF);
        // THEN
        assertEquals(24 * Fare.CAR_RATE_PER_HOUR, ticketOut.getPrice(), 0.001);
        assertTrue(ticketOut.getParkingSpot().isAvailable());
    }

}
