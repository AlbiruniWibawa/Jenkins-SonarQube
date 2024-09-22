package com.project.simple;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SimpleApplication.class)
class GarageServiceTest {

    @Autowired
    private GarageService garageService;

    @Test
    void rentAGarage() {
        Person person = Person.builder()
                .name("Samsudin")
                .gender('L')
                .build();

        List<Car> cars = List.of(
                Car.builder().name("Lambo").color("Black").build(),
                Car.builder().name("Ferrari").color("Red").build(),
                Car.builder().name("Mustang GT").color("Gray").build());

        Garage rentedGarage = garageService.rentGarage(person, cars);

        Assertions.assertSame(person.getName(), rentedGarage.getOwner().getName());
        Assertions.assertSame(person.getGender(), rentedGarage.getOwner().getGender());
        Assertions.assertSame(cars.get(0).getName(), rentedGarage.getCars().get(0).getName());
        Assertions.assertSame(cars.get(0).getColor(), rentedGarage.getCars().get(0).getColor());
        Assertions.assertSame(cars.get(1).getName(), rentedGarage.getCars().get(1).getName());
        Assertions.assertSame(cars.get(2).getName(), rentedGarage.getCars().get(2).getName());
    }

}
