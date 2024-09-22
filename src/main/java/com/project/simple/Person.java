package com.project.simple;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Person {

    private String name;
    private Character gender;

}
