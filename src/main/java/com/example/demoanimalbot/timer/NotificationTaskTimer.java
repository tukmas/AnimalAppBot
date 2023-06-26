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

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void task() {
        catRepository.findAllByDeadlineTime(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        ).forEach(cat -> {
            telegramBot.execute(
                    new SendMessage(cat.getUser().getChatId(), "Вы забыли отправить ежедневный отчет о питомце по имени " + cat.getName()));
            cat.setDeadlineTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1));
        });
        dogRepository.findAllByDeadlineTime(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        ).forEach(dog -> {
            telegramBot.execute(
                    new SendMessage(dog.getUser().getChatId(), "Вы забыли отправить ежедневный отчет о питомце по имени " + dog.getName()));
            dog.setDeadlineTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1));
        });

        catRepository.findAllByDeadlineTime(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).minusDays(1)
        ).forEach(cat -> telegramBot.execute(
                new SendMessage(1857886455, "Напомните пользователю с chatId " +cat.getUser().getChatId() + " о необходимости" +
                        "отправлять отчет о питомце " + cat.getName())));
        dogRepository.findAllByDeadlineTime(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).minusDays(1)
        ).forEach(dog -> telegramBot.execute(
                new SendMessage(1857886455, "Напомните пользователю с chatId " +dog.getUser().getChatId() + " о необходимости" +
                        "отправлять отчет о питомце " + dog.getName())));
        catRepository.findAllByEndOfProbation(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        ).forEach(cat -> telegramBot.execute(
                new SendMessage(1857886455, "Не забудьте отправить пользователю с chatId " +cat.getUser().getChatId() + " уведомление" +
                        "о завершении испытательного срока для питомца " + cat.getName())));
        dogRepository.findAllByEndOfProbation(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        ).forEach(dog -> telegramBot.execute(
                new SendMessage(1857886455, "Не забудьте отправить пользователю с chatId " +dog.getUser().getChatId() + " уведомление" +
                        "о завершении испытательного срока для питомца " + dog.getName())));
    }
}
