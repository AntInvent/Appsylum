package com.antinvent.appsylum;

import java.io.Serializable;

/**
 * Created by antinvent on 25/04/2017.
 */

public class EventListing implements Serializable {

    public enum Day{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private String mName = new String();
    private Day mDay = Day.MONDAY;
    private int mHour = 0;
    private int mMinutes = 0;
    private String mLocation = new String();

    EventListing(String name, Day day, int hour, int minutes, String location)
    {
        this.mName = name;
        this.mDay = day;
        this.mHour = hour;
        this.mMinutes = minutes;
        this.mLocation = location;
    }

    public String getName(){
        return this.mName;
    }

    public Day getDay(){
        return this.mDay;
    }

    public int getHour(){
        return this.mHour;
    }

    public int getMinutes(){
        return this.mMinutes;
    }

    public String getLocation(){
        return this.mLocation;
    }

}
