package com.example.demoanimalbot.service;

import com.example.demoanimalbot.repository.DogReportRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PhotoService {
@Value("photo")
    private String photoDir;
private final DogReportRepository dogReportRepository;


    public PhotoService(DogReportRepository dogReportRepository) {
        this.dogReportRepository = dogReportRepository;
    }

    public void uploadPhoto() {

    }
}
