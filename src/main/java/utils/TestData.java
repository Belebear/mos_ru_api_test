package utils;

import com.github.javafaker.Faker;

public class TestData {

    private static final Faker faker = new Faker();

    public static String headerCaptcha = "018d2337fc5adcfeb237499d71dbf321";

    public static String getMessage() {return faker.lorem().characters(50, 100);}
    public static String getCardNubmer() {return "77" + faker.number().digits(11);}
    public static String getEmail() {return faker.name().firstName() + "@mail.ru" ;}
    public static String getPhoneNubmer() {return "+7" + faker.number().digits(10);}
    public static String getSurname() {return faker.name().lastName();}
    public static String getName() {return faker.name().firstName();}
    public static String getCardSeries() {return faker.number().digits(6);}
}
