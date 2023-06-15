package com.example.demoanimalbot.model.reports;

import com.example.demoanimalbot.model.pets.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
