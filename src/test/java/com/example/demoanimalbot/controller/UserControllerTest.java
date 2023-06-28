package com.example.demoanimalbot.controller;

import com.example.demoanimalbot.model.pets.Cat;
import com.example.demoanimalbot.model.users.UserCat;
import com.example.demoanimalbot.model.users.UserDog;
import com.example.demoanimalbot.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userServiceMock;
    @InjectMocks
    private UserController out;
    UserDog userDog = new UserDog("b", "m", "d");
    UserCat userCat = new UserCat("b", "m", "d");

    @Test
    void createUserCat() {
        when(userServiceMock.createUserCat("b", "m", "d"))
                .thenReturn(userCat);
        assertEquals(out.createUserCat("b", "m", "d"), userCat);
        verify(userServiceMock, times(1)).createUserCat("b", "m", "d");
    }

    @Test
    void createUserDog() {
        when(userServiceMock.createUserDog("b", "m", "d"))
                .thenReturn(userDog);
        assertEquals(out.createUserDog("b", "m", "d"), userDog);
        verify(userServiceMock, times(1)).createUserDog("b", "m", "d");
    }

    @Test
    void findUserCatById() {
        userCat.setId(1l);
        when(userServiceMock.findUserCat(1l))
                .thenReturn(Optional.of(userCat));
        assertEquals(out.findUserCatById(1l), Optional.of(userCat));
        verify(userServiceMock, times(1)).findUserCat(1l);
    }

    @Test
    void findUserDogById() {
        userDog.setId(1l);
        when(userServiceMock.findUserDog(1l))
                .thenReturn(Optional.of(userDog));
        assertEquals(out.findUserDogById(1l), Optional.of(userDog));
        verify(userServiceMock, times(1)).findUserDog(1l);
    }
}