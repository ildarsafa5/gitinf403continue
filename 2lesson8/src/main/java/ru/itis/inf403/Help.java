package ru.itis.inf403;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Help {
    public static void main(String[] args) throws ParseException {
        String str ="Самарская область, г Вятские Поляны";
        Pattern pattern = Pattern.compile("(([Гг](?:\\.?\\s)|[Гг]ород\\s)([А-Яа-яЁё-]+))");
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            System.out.println(matcher.group());
        }


    }
}
