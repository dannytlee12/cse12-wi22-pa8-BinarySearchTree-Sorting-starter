/**
 * Name: Danny Lee
 * ID: A17209209
 * Email:dtl001@ucsd.edu
 * File description: this file contains the MyCalendar class which is able to
 book events given a start time and end time. Events are not allowed to have
 overlapping times.
 */

public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new MyTreeMap<Integer, Integer>();
    }

    public boolean book(int start, int end) {
        if(start < 0 || start >= end){
          throw new IllegalArgumentException();
        }

        if(calendar.ceilingKey(start) >= start
            || calendar.floorKey(end) < end){
          return false;
        }
        calendar.put(start, end);
        return true;
    }

    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}
