package com.example.demoanimalbot.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Класс для объектов, которые берут
 * из приюта кошек
 */
@AllArgsConstructor

@Setter
@Getter
@Entity
public class UserCat extends User {
    public UserCat(String name, long chatId) {
        super(name, chatId);
    }

    public UserCat(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
}
