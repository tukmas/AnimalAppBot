package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.users.UserCat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCatRepository extends JpaRepository<UserCat, Long> {

}
