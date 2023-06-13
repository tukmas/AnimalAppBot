package com.example.demoanimalbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
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
