package com.example.demoanimalbot.model.reports;

import com.example.demoanimalbot.model.pets.Dog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class DogReport extends Reports {

    @ManyToOne
    private Dog dog;
}
