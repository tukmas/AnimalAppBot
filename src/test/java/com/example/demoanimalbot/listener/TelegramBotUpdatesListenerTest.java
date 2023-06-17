package com.example.demoanimalbot.listener;

import com.example.demoanimalbot.model.reports.DogReport;
import com.example.demoanimalbot.model.users.AnswerStatus;
import com.example.demoanimalbot.model.users.ShelterMark;
import com.example.demoanimalbot.repository.DogReportRepository;
import com.example.demoanimalbot.repository.DogRepository;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TelegramBotUpdatesListenerTest {
    @Mock
    private TelegramBot telegramBotMock;
    @Mock
    private UserDogRepository userDogRepositoryMock;
    @Mock
    private UserCatRepository userCatRepositoryMock;
    @Mock
    private DogRepository dogRepositoryMock;
    @Mock
    private Map<Long, AnswerStatus> statusMapMock = new HashMap<>();
    @Mock
    private Map<Long, ShelterMark> markMapMock = new HashMap<>();
    @Mock
    private DogReport dogReportMock = new DogReport();
    @Mock
    private DogReportRepository dogReportRepositoryMock;
    @Mock
    private Logger loggerMock = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @InjectMocks
    private TelegramBotUpdatesListener out;



    @Test
    void sendAfterStart() {

    }

    @Test
    void sendAfterPetShelter() {
    }

    @Test
    void sendAfterPetInfo() {
    }

    @Test
    void adoptPetFromShelter() {
    }

    @Test
    void InfoPetShelter() {
    }

    @Test
    void OpeningDirections() {
    }

    @Test
    void contactPassForTheCar() {
    }

    @Test
    void SafetyShelter() {
    }

    @Test
    void Introducing() {
    }

    @Test
    void RequiredDocuments() {
    }

    @Test
    void TransportRecommendations() {
    }

    @Test
    void HouseForThePuppyRecommendation() {
    }

    @Test
    void HouseForAdultAnimalRecommendation() {
    }

    @Test
    void HouseForAnimalWithDisabilitiesRecomendation() {
    }

    @Test
    void CynologistAdvice() {
    }

    @Test
    void ListOfVerifiedCatenors() {
    }

    @Test
    void ReasonsForRefusal() {
    }
}