package com.example.demoanimalbot.service;

import com.example.demoanimalbot.listener.TelegramBotUpdatesListener;
import com.example.demoanimalbot.model.reports.Photo;
import com.example.demoanimalbot.repository.DogReportRepository;
import com.example.demoanimalbot.repository.PhotoRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
@Transactional
public class PhotoService {
    @Value("photo")
    private String photoDir;
    private final TelegramBot telegramBot;
    private final PhotoRepository photoRepository;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);


    public PhotoService(TelegramBot telegramBot, PhotoRepository photoRepository) {
        this.telegramBot = telegramBot;
        this.photoRepository = photoRepository;

    }

    public Photo uploadPhoto(Update update, Long reportId) {
        Message message = update.message();
        String fileId = message.photo()[0].fileId();
        GetFile request = new GetFile(fileId);
        GetFileResponse getFileResponse = telegramBot.execute(request);
        File file = getFileResponse.file();

        String fileName = file.fileUniqueId();
        Path path = Path.of(photoDir, fileName);

        try {
            Files.write(path, telegramBot.getFileContent(file));
        } catch (IOException e) {
            logger.error("Ошибка при сохранении"
            + e.getMessage()
            + Arrays.toString(e.getStackTrace()),
                    e);

        }
        Photo photo = new Photo(String.valueOf(path), reportId);
        photoRepository.save(photo);
        return photo;
    }
}
