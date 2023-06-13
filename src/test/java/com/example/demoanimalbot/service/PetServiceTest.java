package com.example.demoanimalbot.service;

import com.example.demoanimalbot.model.pets.Cat;
import com.example.demoanimalbot.model.pets.Dog;
import com.example.demoanimalbot.repository.CatRepository;
import com.example.demoanimalbot.repository.DogRepository;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private  UserDogRepository userDogRepository;


    @InjectMocks
    private PetService out;
    @Test
    void createCat() {
        Cat cat = new Cat("a", 1,"b");
        when(catRepositoryMock.save(cat))
                .thenReturn(cat);
        assertEquals(out.createCat("a", 1,"b"), cat);
    verify(catRepositoryMock, times(1)).save(new Cat("a", 1,"b"));
    }

    @Test
    void createDog() {
        Dog dog = new Dog("a", 1,"b");
        when(dogRepositoryMock.save(dog))
                .thenReturn(dog);
        assertEquals(out.createDog("a", 1,"b"), dog);
        verify(dogRepositoryMock, times(1)).save(new Dog("a", 1,"b"));
    }


    @Test
    void findCat() {
        Cat cat = new Cat(1,"b",1);
        when(catRepositoryMock.findById(1l))
                .thenReturn(Optional.of(cat));
        assertEquals(out.findCat(1l), Optional.of(cat));
        verify(catRepositoryMock, times(1)).findById(1l);
    }

    @Test
    void findDog() {
        Dog dog = new Dog(1,"b",1);
        when(dogRepositoryMock.findById(1l))
                .thenReturn(Optional.of(dog));
        assertEquals(out.findDog(1l), Optional.of(dog));
        verify(dogRepositoryMock, times(1)).findById(1l);
    }

    @Test
    void takeCatAtHome() {

    }

    @Test
    void takeDogAtHome() {
    }

    @Test
    void findCatsByUserId() {

    }

    @Test
    void findDogsByUserId() {
    }
}