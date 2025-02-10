
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;
    public String amOrPm = "";
    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, String amPm)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        amOrPm = amPm;
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        //checks for a certain meridian and time to flip the meridian
        if(hours.getValue() == 11 && minutes.getValue() == 59 && (amOrPm.charAt(0) == 'p' ||amOrPm.charAt(0) == 'P'))
        {
            minutes.increment();
            amOrPm = "AM";
        }
        else if(hours.getValue() == 11 && minutes.getValue() == 59 && (amOrPm.charAt(0) == 'a' ||amOrPm.charAt(0) == 'A'))
        {
            minutes.increment();
            amOrPm = "PM";
        }
        else if(hours.getValue() == 12 && minutes.getValue() == 59 && (amOrPm.charAt(0) == 'p' ||amOrPm.charAt(0) == 'P'))
        {
            minutes.increment();
            amOrPm = "AM";
        }
        else if(hours.getValue() == 12 && minutes.getValue() == 59 && (amOrPm.charAt(0) == 'a' ||amOrPm.charAt(0) == 'A'))
        {
            minutes.increment();
            amOrPm = "PM";
        }
        else
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        //checks if the time is greater than 12 to account for military time and subtract it to make it 12 hour
        if(hours.getValue() > 12)
        {
            hours.setValue(hours.getValue()-12);
        }
        
        //checks if the time is 0 to make it 12
        if(hours.getValue() == 0)
        {
            hours.setValue(12);
        }
        
        //prints the time with correct meridian
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + amOrPm;
    }
}
