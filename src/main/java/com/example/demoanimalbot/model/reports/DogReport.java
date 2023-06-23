package com.example.demoanimalbot.model.reports;

import com.example.demoanimalbot.model.pets.Dog;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class DogReport extends Reports {

    @ManyToOne
    private Dog dog;

    public DogReport(LocalDateTime sendDate, Dog dog) {
        super(sendDate);
        this.dog = dog;
    }


    }

