package utils;

import com.github.javafaker.Faker;

import java.time.Month;
import java.time.YearMonth;

import static tests.TestData.*;

public class RandomUtils {
    static Faker faker = new Faker();

    public static Integer getRandomYear() {
        return faker.number().numberBetween(1900, 2100);
    }

    public static String getRandomDayByYearAndMonth(int year, String monthName) {
        Month month = Month.valueOf(monthName.trim().toUpperCase());
        int days = YearMonth.of(year, month.getValue()).lengthOfMonth();

        return String.format("%02d", faker.number().numberBetween(1, days));
    }

//    public static String getRandomCityByState(String state) {
//        return switch (state) {
//            case "NCR" -> faker.options().option(citiesOfNCR);
//            case "Uttar Pradesh" -> faker.options().option(citiesOfUttarPradesh);
//            case "Haryana" -> faker.options().option(citiesOfHaryana);
//            case "Rajasthan" -> faker.options().option(citiesOfRajasthan);
//            default -> "";
//        };
//    }
}
