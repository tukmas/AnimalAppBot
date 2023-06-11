package com.example.demoanimalbot.timer;

import com.example.demoanimalbot.repository.NotificationTaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class NotificationTaskTimer {

    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskTimer(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void task(){

    }
}
