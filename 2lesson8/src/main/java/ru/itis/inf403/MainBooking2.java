package ru.itis.inf403;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainBooking2 {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Bookings bookings = mapper.readValue(new File("bookings.json"), Bookings.class);
        printChildren(bookings);
        System.out.println("------------------");
        printHotels(bookings);
        System.out.println("------------------");
        printHotelMen(bookings);
        System.out.println("------------------");
        printHotelWomen(bookings);
        System.out.println("------------------");
        printPersentSamara(bookings);
        System.out.println("------------------");
        printCityHotels(bookings);
        System.out.println("------------------");
        printPersentAdults(bookings);
        System.out.println("------------------");
        printUniquePeople(bookings);
        System.out.println("------------------");
        printCities(bookings);
    }

    public static void printChildren(Bookings bookings) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2011-04-23");
            bookings
                    .getBookings()
                    .stream()
                    .filter(p -> p.getPerson().getBirthdate()!=null)
                    .filter(p -> p.getPerson().getBirthdate().after(date))
                    .map(s -> s.getPerson())
                    .forEach(System.out::println);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printHotels(Bookings bookings) {
        Map<String,Integer> result =
                bookings.getBookings()
                .stream()
                .collect(Collectors.toMap(
                        b -> b.getHotel().getName(),
                        b -> 1,
                        (x,y) -> x+y));

        result.entrySet()
                .stream()
                .forEach(a -> System.out.println(a.getKey()+ " " + a.getValue()));
    }

    public static void printHotelWomen(Bookings bookings) {
        Map<String,Integer> result = bookings
                .getBookings()
                .stream()
                .filter(b -> b.getPerson().getGender()!=null)
                .filter(b -> b.getPerson().getGender().equals("Female"))
                .collect(Collectors.toMap(
                        b -> b.getHotel().getName(),
                        b -> 1,
                        (x,y) -> x+y));
        result.entrySet()
                .stream()
                .forEach(a -> System.out.println(a.getKey()+ " " + a.getValue()));
    }

    public static void printHotelMen(Bookings bookings) {
        Map<String,Integer> result = bookings
                .getBookings()
                .stream()
                .filter(b -> b.getPerson().getGender()!=null)
                .filter(b -> b.getPerson().getGender().equals("Male"))
                .collect(Collectors.toMap(
                        b -> b.getHotel().getName(),
                        b -> 1,
                        (x,y) -> x+y));
        result.entrySet()
                .stream()
                .forEach(a -> System.out.println(a.getKey()+ " " + a.getValue()));
    }

    public static void printPersentSamara(Bookings bookings) {
        long all = bookings
                .getBookings()
                .stream()
                .count();
        long samara = bookings
                .getBookings()
                .stream()
                .filter(b -> b.getPerson().getFromcity().contains("Самарская область"))
                .count();
        System.out.println(((double)samara/(double)all)*100 + " процентов");
    }

    public static void printCityHotels(Bookings bookings) {
        Map<String, Set<String>> result =
                bookings.getBookings()
                        .stream()
                        .collect(Collectors.toMap(
                                b -> b.getHotel().getName(),
                                b -> {
                                    Set a = new HashSet();
                                    a.add(b.getPerson().getFromcity());
                                    return a;
                                },
                                (x,y) -> {
                                    x.addAll(y);
                                    return x;
                                }));

        result.entrySet()
                .stream()
                .forEach(a -> System.out.println(a.getKey()+ " " + a.getValue()));
    }

    public static void printPersentAdults(Bookings bookings) {
        try {
            long all = bookings
                    .getBookings()
                    .stream()
                    .count();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("1985-04-23");
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("1980-04-23");
            long cur = bookings
                    .getBookings()
                    .stream()
                    .filter(p -> p.getPerson().getBirthdate()!=null)
                    .filter(p -> p.getPerson().getBirthdate().after(date2))
                    .filter(p -> p.getPerson().getBirthdate().before(date1))
                    .count();
            System.out.println(((double) cur / (double) all)*100 + " процентов");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static void printCities(Bookings bookings) {
        List<String> a = new ArrayList(bookings
                .getBookings()
                .stream()
                .map(b -> b.getPerson().getFromcity())
                .map(b -> b.replace("-", " "))
                .map(b -> {
                    if (b.contains(" г ")) {
                        return City(b," г ");
                    }
                    if (b.contains("Город")) {
                        return City(b,"Город");
                    }
                    if (b.contains(" г. ")) {
                        return City(b," г. ");
                    }
                    return null;
                })
                .filter(b -> b != null)
                .toList());
        Map<String,Integer> result = a
                .stream()
                .collect(Collectors.toMap(
                        b -> b,
                        b -> 1,
                        (x,y) -> x+y));
        
        result.entrySet()
                .stream()
                .forEach(ab -> System.out.println(ab.getKey()+ " " + ab.getValue()));
    }

    private static String City(String str,String regex) {
        String str1 = str.replaceAll("[,]","");
        String[] ay = str1.split(" ");
        int cnt = 0;
        for (String n : ay) {
            if (n.equals(regex.trim())) {
                return ay[cnt+1];
            }
            cnt++;
        }
        return null;
    }

//    private static String getCity(String str) {
//        Pattern pattern = Pattern.compile("(([Гг](?:\\.?\\s)|[Гг]ород\\s)([А-Яа-яЁё-]+))");
//        Matcher matcher = pattern.matcher(str);
//
//        if (matcher.find()) {
//            matcher.group();
//        }
//    }

    public static void printUniquePeople(Bookings bookings) {
        long all = bookings
                .getBookings()
                .stream()
                .count();
        Set set = bookings
                .getBookings()
                .stream()
                .filter(b -> b.getPerson().getGender()!=null)
                .filter(b -> b.getPerson().getBirthdate()!=null)
                .map(b -> b.getPerson())
                .collect(Collectors.toSet());
        long cnt = set
                .stream()
                .count();
        System.out.println(((double)cnt)/((double)all)*100 + " процентов");
    }
}
