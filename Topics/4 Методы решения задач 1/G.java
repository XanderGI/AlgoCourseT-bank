//package Tink.less4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskG4TicketOfficeAndScanLine {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int duration;

    static class Event implements Comparable {
        int hours;
        int minutes;
        int seconds;
        int timeInSeconds;
        char sign;

        public Event(int hours, int minutes, int seconds, char sign) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
            this.timeInSeconds = this.hours * 3600 + this.minutes * 60 + this.seconds;
            this.sign = sign;
        }


        @Override
        public int compareTo(Object o) {
            Event e = (Event) o;
            if (this.timeInSeconds != e.timeInSeconds) {
                return this.timeInSeconds - e.timeInSeconds;
            }
            return this.sign - e.sign;
        }

        @Override
        public String toString() {
            return hours + ":" + minutes + ":" + seconds + " (" + sign + ")";
        }
    }

    static void findDuration(List<Event> arrayOfEvents, int counter, int countOfTicket) {
        Integer left = null;
        for (Event event : arrayOfEvents) {
            if (event.sign == '+') {
                counter++;
                if (counter == countOfTicket) {
                    left = event.timeInSeconds;
                }
            } else {
                if (counter == countOfTicket) {
                    duration += event.timeInSeconds - left;
                }
                counter--;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        List<Event> arrayOfEvents = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            String[] duration = reader.readLine().split(" ");
            Event start = new Event(Integer.parseInt(duration[0]),Integer.parseInt(duration[1]),Integer.parseInt(duration[2]),'+');
            Event end = new Event(Integer.parseInt(duration[3]),Integer.parseInt(duration[4]),Integer.parseInt(duration[5]),'-');
            if (start.timeInSeconds == end.timeInSeconds) {
                counter++;
                continue;
            } else if (end.timeInSeconds < start.timeInSeconds) {
                arrayOfEvents.add(start);
                arrayOfEvents.add(new Event(24, 0, 0, '-'));
                arrayOfEvents.add(new Event(0, 0, 0, '+'));
                arrayOfEvents.add(end);
            } else {
                arrayOfEvents.add(start);
                arrayOfEvents.add(end);
            }


        }
        Collections.sort(arrayOfEvents);
        findDuration(arrayOfEvents,counter,n);
        System.out.println(duration);
    }
}