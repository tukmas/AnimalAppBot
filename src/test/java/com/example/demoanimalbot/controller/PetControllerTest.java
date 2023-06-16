package com.example.demoanimalbot.controller;

import com.example.demoanimalbot.model.pets.Cat;
import com.example.demoanimalbot.model.pets.Dog;
import com.example.demoanimalbot.model.users.UserCat;
import com.example.demoanimalbot.model.users.UserDog;
import com.example.demoanimalbot.service.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @Mock
    private PetService petServiceMock;

    @InjectMocks
    private PetController out;
    Cat cat = new Cat("a", 1, "b");
    Dog dog = new Dog("a", 1, "b");
    List<Cat> cats = new ArrayList<>();
    List<Dog> dogs = new ArrayList<>();
    UserDog userDog = new UserDog("b", "m", "d");
    UserCat userCat = new UserCat("b", "m", "d");

    @Test
    void createCat() {
        when(petServiceMock.createCat("a", 1, "b"))
                .thenReturn(cat);
        assertEquals(out.createCat("a", 1, "b"), cat);
        verify(petServiceMock, times(1)).createCat("a", 1, "b");
    }

    @Test
    void createDog() {
        when(petServiceMock.createDog("a", 1, "b"))
                .thenReturn(dog);
        assertEquals(out.createDog("a", 1, "b"), dog);
        verify(petServiceMock, times(1)).createDog("a", 1, "b");
    }

    @Test
    void findCatById() {
        when(petServiceMock.findCat(1l))
                .thenReturn(Optional.of(cat));
        assertEquals(out.findCatById(1l), Optional.of(cat));
        verify(petServiceMock, times(1)).findCat(1l);
    }

    @Test
    void findDogById() {
        when(petServiceMock.findDog(1l))
                .thenReturn(Optional.of(dog));
        assertEquals(out.findDogById(1l), Optional.of(dog));
        verify(petServiceMock, times(1)).findDog(1l);
    }

    @Test
    void takeCatAtHome() {
        userCat.setId(1l);
        when(petServiceMock.takeCatAtHome(1l, 1l))
                .thenReturn(cat);
        assertEquals(out.takeCatAtHome(1l, 1l), cat);
        verify(petServiceMock, times(1)).takeCatAtHome(1l, 1l);
    }

    @Test
    void takeDogAtHome() {
        userDog.setId(1l);
        when(petServiceMock.takeDogAtHome(1l, 1l))
                .thenReturn(dog);
        assertEquals(out.takeDogAtHome(1l, 1l), dog);
        verify(petServiceMock, times(1)).takeDogAtHome(1l, 1l);
    }

    @Test
    void findCatsByUserId() {
        when(petServiceMock.findCatsByUserId(1l))
                .thenReturn(cats);
        assertEquals(out.findCatsByUserId(1l), cats);
        verify(petServiceMock, times(1)).findCatsByUserId(1l);
    }

    @Test
    void findDogsByUserId() {
        when(petServiceMock.findDogsByUserId(1l))
                .thenReturn(dogs);
        assertEquals(out.findDogsByUserId(1l), dogs);
        verify(petServiceMock, times(1)).findDogsByUserId(1l);
    }
}