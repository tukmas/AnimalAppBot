package com.example.demoanimalbot.service;

import com.example.demoanimalbot.model.pets.Cat;
import com.example.demoanimalbot.model.pets.Dog;
import com.example.demoanimalbot.model.users.UserCat;
import com.example.demoanimalbot.model.users.UserDog;
import com.example.demoanimalbot.repository.CatRepository;
import com.example.demoanimalbot.repository.DogRepository;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import org.junit.jupiter.api.BeforeAll;
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
class PetServiceTest {

    @Mock
    private CatRepository catRepositoryMock;
    @Mock
    private DogRepository dogRepositoryMock;
    @Mock
    private UserCatRepository userCatRepository;
    @Mock
    private UserDogRepository userDogRepository;


    @InjectMocks
    private PetService out;


    Cat cat = new Cat("a", 1, "b");
    Dog dog = new Dog("a", 1, "b");
    List<Cat> cats = new ArrayList<>();
    List<Dog> dogs = new ArrayList<>();
    UserDog userDog = new UserDog( "b",  "m", "d");
    UserCat userCat = new UserCat("b", "m", "d");

    @Test
    void createCat() {

        when(catRepositoryMock.save(cat))
                .thenReturn(cat);
        assertEquals(out.createCat("a", 1, "b"), cat);
        verify(catRepositoryMock, times(1)).save(new Cat("a", 1, "b"));
    }

    @Test
    void createDog() {

        when(dogRepositoryMock.save(dog))
                .thenReturn(dog);
        assertEquals(out.createDog("a", 1, "b"), dog);
        verify(dogRepositoryMock, times(1)).save(new Dog("a", 1, "b"));
    }


    @Test
    void findCat() {

        when(catRepositoryMock.findById(1l))
                .thenReturn(Optional.of(cat));
        assertEquals(out.findCat(1l), Optional.of(cat));
        verify(catRepositoryMock, times(1)).findById(1l);
    }

    @Test
    void findDog() {

        when(dogRepositoryMock.findById(1l))
                .thenReturn(Optional.of(dog));
        assertEquals(out.findDog(1l), Optional.of(dog));
        verify(dogRepositoryMock, times(1)).findById(1l);
    }

    @Test
    void takeCatAtHome() {
        userCat.setId(1l);
        when(catRepositoryMock.findById(1l))
                .thenReturn(Optional.of(cat));
        when(userCatRepository.findById(1l))
                .thenReturn(Optional.of(userCat));
        assertNotEquals(out.takeCatAtHome(1l,1l), cat);
        verify(catRepositoryMock, times(1)).findById(1l);
        verify(userCatRepository, times(1)).findById(1l);

    }

    @Test
    void takeDogAtHome() {
        userDog.setId(1l);
        when(dogRepositoryMock.findById(1l))
                .thenReturn(Optional.of(dog));
        when(userDogRepository.findById(1l))
                .thenReturn(Optional.of(userDog));
        assertNotEquals(out.takeDogAtHome(1l,1l), dog);
        verify(dogRepositoryMock, times(1)).findById(1l);
        verify(userDogRepository, times(1)).findById(1l);

    }

    @Test
    void findCatsByUserId() {
        when(catRepositoryMock.findByUserId(1l))
                .thenReturn(cats);
        assertEquals(out.findCatsByUserId(1l), cats);
        verify(catRepositoryMock, times(1)).findByUserId(1l);
    }

    @Test
    void findDogsByUserId() {
        when(dogRepositoryMock.findByUserId(1l))
                .thenReturn(dogs);
        assertEquals(out.findDogsByUserId(1l), dogs);
        verify(dogRepositoryMock, times(1)).findByUserId(1l);
    }
}