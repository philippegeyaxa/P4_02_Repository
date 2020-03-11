package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();
        double duration = (outHour - inHour) / 60.0 / 60.0 / 1000.0;
        double tariff = 0;
        
        // Fare is null if duration is less than 30 minutes 
        if (duration < 0.5) duration = 0; 
        
        // Tariff depends on recurrence
        if (ticket.isRecurring()) tariff = Fare.TARIFF_RECURRING_USER_5_PERCENT_OFF;
        else tariff = Fare.TARIFF_STANDARD;


        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(duration * tariff * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(duration * tariff * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}