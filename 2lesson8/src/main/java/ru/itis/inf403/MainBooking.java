package ru.itis.inf403;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MainBooking {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Bookings bookings = mapper.readValue(new File("bookings.json"), Bookings.class);

        printCount(bookings);
        printCountWomen(bookings);

        printCountFromMoscow(bookings);
    }

    public static void printCount(Bookings bookings) {
        System.out.println(bookings
                .getBookings()
                .stream()
                .count()
        );
    }

    public static void printCountWomen(Bookings bookings) {
        System.out.println(bookings
                .getBookings()
                .stream()
                .filter(b -> b.getPerson().getGender() != null)
                .filter(a -> a.getPerson().getGender().equals("Female"))
                .count()
        );
    }

    public static void printCountFromMoscow(Bookings boookings) {
        System.out.println(boookings
                .getBookings()
                .stream()
                .filter(b -> b.getPerson().getFromcity().contains("Москва"))
                .count()
        );
    }
}
