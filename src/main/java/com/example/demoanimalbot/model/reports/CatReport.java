package com.example.demoanimalbot.model.reports;

import com.example.demoanimalbot.model.pets.Cat;
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
public class CatReport extends Reports {

    @ManyToOne
    private Cat cat;

}
