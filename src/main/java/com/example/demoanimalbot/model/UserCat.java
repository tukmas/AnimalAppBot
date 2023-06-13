package com.example.demoanimalbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Класс для объектов, которые берут
 * из приюта кошек
 */
@AllArgsConstructor

@Setter
@Getter
@Entity
public class UserCat extends User{

    public UserCat(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
}
