package com.example.demoanimalbot.timer;

import com.example.demoanimalbot.repository.CatRepository;
import com.example.demoanimalbot.repository.DogRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationTaskTimer {

    private final CatRepository catRepository;
    private final DogRepository dogRepository;
    private final TelegramBot telegramBot;

    public NotificationTaskTimer(CatRepository catRepository, DogRepository dogRepository, TelegramBot telegramBot) {
        this.catRepository = catRepository;
        this.dogRepository = dogRepository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "* 30 21 * * *")
    public void task() {
        catRepository.findAllByDeadlineTime(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        ).forEach(cat -> {
            telegramBot.execute(
                    new SendMessage(cat.getUser().getChatId(), "Вы забыли отправить ежедневный отчет о питомце по имени " + cat.getName()));
            cat.setDeadlineTime(LocalDateTime.now().plusDays(1));
        });
    }

}
