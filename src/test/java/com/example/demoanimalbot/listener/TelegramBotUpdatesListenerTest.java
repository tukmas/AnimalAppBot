package com.example.demoanimalbot.listener;

import com.example.demoanimalbot.model.keyboardButtons.Buttons;
import com.example.demoanimalbot.model.reports.DogReport;
import com.example.demoanimalbot.model.users.AnswerStatus;
import com.example.demoanimalbot.model.users.ShelterMark;
import com.example.demoanimalbot.repository.DogReportRepository;
import com.example.demoanimalbot.repository.DogRepository;
import com.example.demoanimalbot.repository.UserCatRepository;
import com.example.demoanimalbot.repository.UserDogRepository;
import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
    void sendAfterStart() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%text%", "/start"), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.message().chat().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Привет! Я помогу тебе выбрать питомца. " +
                "Нажмите кнопку ниже, чтобы перейти в приют," +
                " в котором живут кошки или собаки");
    }

       @Test
    void sendAfterPetInfo() throws URISyntaxException, IOException{

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", "Информация о приюте"), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
         "Выберите нужный раздел, чтобы узнать интересующую Вас информацию");

    }
    @Test
    void sendAfterPetShelter() throws URISyntaxException, IOException{
        String name = "Приют для кошек";
        String catShelter = "Добро пожаловать в приют для кошек. Выберите нужный раздел";
        String dogShelter = "Добро пожаловать в приют для собак. Выберите нужный раздел";

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", name), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);
        if (name.equals("Приют для кошек")) {
            Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
            Assertions.assertEquals(actual.getParameters().get("text"),
                    catShelter);
        }
        if (name.equals("Приют для собак")) {
            Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
            Assertions.assertEquals(actual.getParameters().get("text"),
                    dogShelter);

        }
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