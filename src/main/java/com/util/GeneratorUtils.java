package com.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.models.Person;
import com.models.Product;
import com.models.Student;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GeneratorUtils {
    public static List<Person> generatePersonData() throws IOException {
        List<Person> personList = new ArrayList<>();
        String[] firstNames = {"John", "Jane", "Alex", "Maria", "Sam", "Priya", "Wei", "Ahmed", "Olga", "Carlos","Shubham"};
        String[] lastNames = {"Smith", "Doe", "Brown", "Patel", "Wang", "Singh", "Garcia", "Kim", "Ivanov", "Nguyen","Singh"};
        String[] streets = {"Main St", "New York","Elm St", "Oak Ave", "Pine Rd", "Maple Dr", "Cedar Ln", "Birch Blvd","Cherry Ct", "Walnut Way", "Spruce Cir","Bamboo St"};
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "mail.com", "example.com"};
        Random rand = new Random();

        for (int i = 0; i < 500; i++) {
            Person p = new Person();
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            p.setFirstName(firstName);
            p.setSecondName(lastName);
            p.setAddress((rand.nextInt(999) + 1) + " " + streets[rand.nextInt(streets.length)]);
            p.setAge(rand.nextInt(60) + 18);
            p.setBirthDate(new Date(System.currentTimeMillis() - (long)rand.nextInt(40 * 365) * 24 * 60 * 60 * 1000));
            p.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + (rand.nextInt(1000)) + "@" + domains[rand.nextInt(domains.length)]);
            String phone1 = String.format("%03d-%03d-%04d", rand.nextInt(1000), rand.nextInt(1000), rand.nextInt(10000));
            String phone2 = String.format("%03d-%03d-%04d", rand.nextInt(1000), rand.nextInt(1000), rand.nextInt(10000));
            p.setPhoneNumber(List.of(phone1,phone2));
            personList.add(p);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("person_data.json"), personList);
        return personList;
    }

    public static List<Person> readPersonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Files.readString(Path.of("person_data.json")), new TypeReference<List<Person>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
    }

    public static List<Product> generateProductData() {
        List<Product> productList = new ArrayList<>();
        String[] productNames = {"Laptop", "Smartphone", "Tablet", "Headphones", "Smartwatch", "Camera", "Printer", "Monitor", "Keyboard", "Mouse"};
        Random rand = new Random();

        for (int i = 0; i < 500; i++) {
            Product p = new Product();
            String productName = productNames[rand.nextInt(productNames.length)];
            p.setProductName(productName);
            p.setPrice(rand.nextDouble() * 1000);
            List<Timestamp> timestamps = new ArrayList<>();
            Timestamp time1 = new Timestamp((long) (Math.random()*1000000));
            Timestamp time2 = new Timestamp((long) (Math.random()*1000000));
            timestamps.add(time1);
            timestamps.add(time2);
            p.setOrderModificationTimestamp(timestamps);
            Double price1 = Math.random()*100*3;
            Double price2 = Math.random()*100*2;
            p.setPrices(List.of(price1,price2));
            p.setOrderModificationTimestamp(timestamps);
            p.setQuantity((long) (Math.random()*10*3));
            productList.add(p);
        }
        return productList;
    }

    public static List<Student> generateStudentData() {
        List<Student> studentList = new ArrayList<>();
        String[] firstNames = {"John", "Jane", "Alex", "Maria", "Sam", "Priya", "Wei", "Ahmed", "Olga", "Carlos","Shubham"};
        String[] lastNames = {"Smith", "Doe", "Brown", "Patel", "Wang", "Singh", "Garcia", "Kim", "Ivanov", "Nguyen","Singh"};
        Random rand = new Random();
        for (int i = 0; i < 500; i++) {
            Student s = new Student();
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            s.setFirstName(firstName);
            s.setLastName(lastName);
            List<Integer> grades = IntStream.range(0, 5).map(x -> rand.nextInt(101)).boxed().toList();
            s.setGrades(grades);
            studentList.add(s);
        }
        return studentList;
    }
}
