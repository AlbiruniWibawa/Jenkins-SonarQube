package com.project.simple;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Garage {

    private Person owner;
    private List<Car> cars;

}
