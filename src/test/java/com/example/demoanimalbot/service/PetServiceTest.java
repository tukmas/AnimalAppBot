package com.example.demoanimalbot.service;

import com.example.demoanimalbot.model.Cat;
import com.example.demoanimalbot.model.Status;
import com.example.demoanimalbot.repository.CatRepository;
import com.example.demoanimalbot.repository.DogRepository;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private CatRepository catRepositoryMock;
    @Mock
    private DogRepository dogRepository;
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
    void createDog() {}


    @Test
    void findCat() {
        Cat cat = new Cat(1,"dd",1,"22","ww", Status.HOME, LocalDate.now(),null);
        when(catRepositoryMock.findById(1l))
                .thenReturn(Optional.of(cat));
        assertEquals(out.findCat(1l), Optional.of(cat));
        verify(catRepositoryMock, times(1)).findById(1l);
    }

    @Test
    void findDog() {
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