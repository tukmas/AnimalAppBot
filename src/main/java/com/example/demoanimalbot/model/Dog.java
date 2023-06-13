package com.example.demoanimalbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Dog extends Pet {

    @ManyToOne
    private UserDog user;

    public Dog(String name, int age, String breed) {
        super(name, age, breed);
    }

    public Dog(long id, String name, int age) {
        super(id, name, age);
    }
}
