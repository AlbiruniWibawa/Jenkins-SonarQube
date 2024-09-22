package com.project.simple;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GarageService {

    public Garage rentGarage(Person person, List<Car> cars) {
        return new Garage(person, cars);
    }

}
