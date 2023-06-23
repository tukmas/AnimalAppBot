package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.pets.Cat;
import com.example.demoanimalbot.model.pets.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findByUserId(long userId);
    Dog findByUserChatIdAndName(long chatId, String name);
    List<Dog> findAllByDeadlineTime(LocalDateTime localDateTime);
}
