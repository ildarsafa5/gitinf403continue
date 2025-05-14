package ru.itis.inf403;

import java.util.List;

public class Bookings {
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    private List<Booking> bookings;
}
