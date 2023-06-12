package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.entity.NotificationTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
}
