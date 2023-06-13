package com.example.demoanimalbot.model.pets;

import com.example.demoanimalbot.model.users.UserCat;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Cat extends Pet {

    @ManyToOne
    private UserCat user;

    public Cat(String name, int age, String breed) {
        super(name, age, breed);
    }

    public Cat(long id, String name, int age) {
        super(id, name, age);
    }
}
