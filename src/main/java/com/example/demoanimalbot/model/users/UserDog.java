package com.example.demoanimalbot.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс для объектов, которые берут
 * из приюта собак
 */
@AllArgsConstructor

@Setter
@Getter
@Entity
public class UserDog extends User {
    public UserDog(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
}
