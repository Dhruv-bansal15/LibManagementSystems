package com.library.util;

import java.util.Date;
import java.util.Scanner;

import com.library.exception.NegativeNumberException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Helper {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static String outputDateFormat(Date date) {
        return format.format(date);
    }

    public static Date inputDateFormat(String dateString) throws ParseException {
        return format.parse(dateString);
    }

    public static int integerInput(Scanner scanner) {
        int input;
        while(true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < 0) throw new NegativeNumberException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid option");
            } catch (NegativeNumberException e) {
                System.out.println("Cannot enter a negative value");
            }
        }
        return input;
    }  
}
