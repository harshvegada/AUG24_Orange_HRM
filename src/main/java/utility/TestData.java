package utility;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TestData {

    public static String getSkillName(){
        Faker faker = new Faker();
        return faker.job().position();
    }

    public static String getSkillDescription(){
        Faker faker = new Faker();
        return faker.job().keySkills();
    }

    public static String getFirstName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String getLastName(){
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String getMiddleName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String getFutureDate(){
        // Generate a random number of days to add (between 1 and 365)
        int daysToAdd = ThreadLocalRandom.current().nextInt(1, 366);
        // Get the current date and add the random days
        LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);
        // Format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = futureDate.format(formatter);
        // Print the future date
        return formattedDate;
    }

    public static String getBirthdayDate(){
        Faker faker = new Faker();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(faker.date().birthday());
    }

    public static String getSSN(){
        Faker faker = new Faker();
        return faker.number().digits(10);
    }

    public static String getLicenceNumber(){
        Faker faker = new Faker();
        return faker.number().digits(10);
    }

}
