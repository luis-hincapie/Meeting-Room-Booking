package com.javainterns.bookingroom.utils;

import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeValidation {
    public void isValidTimeRange(Integer endTime, Integer startTime){
        if(endTime<=startTime || (endTime==0 && startTime !=0))
            throw new StartTimeIsGreaterThanEndTime("startTime could not be greater than finishTime");
    }

    public Boolean bookingHourValidation(Booking booking, List<Booking> booked){
        return booked.stream().allMatch(x ->
                (booking.getStartTime()<x.getStartTime() && booking.getEndTime()<=x.getStartTime())
                        ||
                        (booking.getStartTime()>=x.getEndTime() && booking.getEndTime()>x.getEndTime()));
    }

    public Boolean bookingRoomHourValidation(Booking booking, Room room){
        return ((booking.getStartTime()>=room.getStartTime()) && (booking.getEndTime()<=room.getFinishTime()));
    }
}
