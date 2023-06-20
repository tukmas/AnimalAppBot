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
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
    void sendAfterPetInfo() throws URISyntaxException, IOException {

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.INFO.toString()), Update.class);

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
    void sendAfterPetShelter() throws URISyntaxException, IOException {

        String catShelter = "Добро пожаловать в приют для кошек. Выберите нужный раздел";
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", "CAT"), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                catShelter);

    }

    @Test
    void adoptPetFromShelter(Long chatId, ShelterMark shelterMark) throws URISyntaxException, IOException {
        markMapMock.put(chatId, ShelterMark.DOG);

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.TAKE.toString()), Update.class);

        out.process(Collections.singletonList(update));
        Mockito.when((markMapMock.get(123L)))
                .thenReturn(shelterMark);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        // InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        // Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Консультация с потенциальным хозяином животного из приюта");


    }

    @Test
    void InfoPetShelter() throws URISyntaxException, IOException {
        markMapMock.put(123L, ShelterMark.CAT);
        String text;
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.SHELTER.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");
        if (markMapMock.get(123L).equals(ShelterMark.CAT)) {
            text = "кошек";
        } else text = "собак";
        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Приют для " + text + " — место содержания бездомных, потерянных или брошенных животных." +
                        " Приюты являются одной из ключевых составляющих защиты животных и выполняют четыре" +
                        " сновных функции: оперативная помощь и забота о животном, включая облегчение страданий посредством ветеринарной" +
                        " помощи или эвтаназии; долгосрочная забота о животном, не нашедшем немедленно старого или нового хозяина; усилия по" +
                        " воссоединению потерянного животного с его прежним хозяином; поиск нового места обитания или нового хозяина для" +
                        " бездомного животного");
    }

    @Test
    void OpeningDirections() throws URISyntaxException, IOException {
    }

    @Test
    void contactPassForTheCar() throws URISyntaxException, IOException {
    }

    @Test
    void SafetyShelter() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.SAFETY_SHELTER.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Работники и посетители приюта обязаны соблюдать правила личной гигиены, в том числе" +
                        " мыть руки с дезинфицирующими средствами после общения с животными. Нахождение на территории в излишне" +
                        " возбужденном состоянии, а также в состоянии алкогольного, наркотического или медикаментозного опьянения" +
                        " строго запрещено.");
    }

    @Test
    void Introducing() throws URISyntaxException, IOException {

    }

    @Test
    void RequiredDocuments() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.DOCUMENTS.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Перечень документов на получение животного из приюта:\n" +
                        "Паспорт;\n" +
                        "Заявление на выдачу животного;\n" +
                        "Свидетельство об ознакомлении правил редачи животного;\n" +
                        "Квитанция об оплате госпошлины;\n" +
                        "Медицинское заключение (форма N 083/У-89).");
    }

    @Test
    void TransportRecommendations() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.TRANSPORTATION.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Tребования к перевозке домашних животных в автомобиле:\n" +
                        "1. Закон 2023 года разрешает перевозить питомцев в машине, но предъявляет ряд требований.\n" +
                        "2. Животные не считаются пассажирами – это имущество. Поэтому, в соответствии с ПДД их перевозка приравнивается к транспортировке грузов.\n" +
                        "3. Пункт 23.2 Правил обязывает водителя не просто расположить, но и надёжно закрепить перевозимый груз. В этом смысле домашних животных нужно пристегнуть за шлейку ремнём безопасности или обмотать поводок на штырь заднего сиденья.\n" +
                        "4. Питомцы не должны свободно перемещаться по салону автомобиля, поскольку в таком случае они могут создавать помехи водителю – а это уже нарушение. Нежелательно также держать из на коленках.\n" +
                        "5. Не допускается, чтобы кошка или собака ехали лёжа на передней панели машины. Да, это мило – но за подобное можно получить штраф от инспектора ГИБДД.\n" +
                        "6. Желательно использовать для перевозки четвероногих друзей закрывающиеся клетки или переноски. Главное, зафиксировать бокс с помощью штатного ремня или иным образом.\n" +
                        "7. Важно следить, чтобы животное не высовывалось из окна автомобиля – это чревато простудой и травмами для хвостатого.\n" +
                        "8. За неправильную перевозку домашних животных в машине предусмотрены два наказания в соответствии " +
                        "со ст. 12.21 КоАП – штраф 500 рублей или предупреждение.\n" +
                        "9. Но если вы оставите кошку, собаку или другого питомца в автомобиле без присмотра, " +
                        "а сами уйдёте, и об этом станет известно инспекторам ГИБДД – вас могут привлечь к уголовной ответственности.");
    }

    @Test
    void HouseForThePuppyRecommendation() throws URISyntaxException, IOException {

    }

    @Test
    void HouseForAdultAnimalRecommendation() throws URISyntaxException, IOException {
    }

    @Test
    void HouseForAnimalWithDisabilitiesRecomendation() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.DISABLED_PET_HOME.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Дом для животного с ограниченными возможностями. Рекомендации.\n" +
                        "1. Уход за глухими любимцами. \n" +
                        "Слабослышащие (или не слышащие вовсе) коты в квартире живут практически полноценной жизнью. Единственная разница между" +
                        " здоровым животным и инвалидом заключается в том, что последний не может прийти на зов хозяина. Однако это не помешает" +
                        " коту быстро прибежать к миске, как только он учует вкусный запах.Глухие собаки, особенно крупных пород, в первую очередь," +
                        " нуждаются в особой дрессировке. В противном случае опасность может грозить не только им, но и окружающим. После обучения " +
                        "хозяин сможет общаться с питомцем на языке жестов: с помощью фонарика, прикосновений и мимики." +
                        "2. Уход за питомцами с ограниченной подвижностью.\n" +
                        " К данной категории можно отнести собак и кошек, перенесших травму позвоночника, лишившихся конечностей, потерявших" +
                        " чувствительность лап вследствие перенесенных заболеваний и т. д. Для таких животных, в первую очередь, необходимо обеспечить" +
                        " удобство передвижения по территории постоянного проживания. Если питомец волочит заднюю часть туловища, необходимо убрать с" +
                        " пола ковры, которые могут препятствовать движению. При этом для защиты конечностей от образования мозолей потребуется приобрести" +
                        " специальные фиксирующиеся накладки. Решить проблему туалета помогут специальные подгузники и одноразовые пеленки. В некоторых" +
                        " случаях для собаки или кота можно подобрать подходящую инвалидную коляску или ходунки. Такие конструкции должны иметь достаточно" +
                        " легкий вес, но при этом не прогибаться под массой питомца. Крепления не должны натирать шкуру или вызывать иной дискомфорт." +
                        "Для того чтобы питомец мог самостоятельно преодолевать различные препятствия (например, порожки или ступени), хозяин может заказать" +
                        " специальные пандусы. Такие конструкции чаще всего изготавливаются по индивидуальным размерам. Для отделки внешней поверхности" +
                        " используются специальные противоскользящие материалы. Надежная фиксация пандусов достигается за счет упоров и креплений." +
                        "3. Уход за парализованным любимцем.\n" +
                        "У лежачего пса или кота обычно ограничены возможности самогигиены и при этом наблюдается недержание мочи и кала. Для решения первой" +
                        " проблемы питомца следует мыть не реже одного раза в два-три дня. Справиться с дискомфортом от неконтролируемых испражнений помогут" +
                        " подгузники и пеленки. Иногда из-за частых водных процедур кожа любимца становится сухой и начинает шелушиться. Снять неприятный симптом" +
                        " помогут специальные увлажняющие средства. Постоянное нахождение питомца в одной и той же позе может привести к образованию пролежней." +
                        " Для их профилактики необходимо использовать специальные защитные повязки и накладки. Для крупных животных производители предлагают" +
                        " специальные ортопедические кровати.\n" +
                        "Важно! Для глухих, слепых или ограниченно подвижных животных категорически запрещен свободный выгул.");
    }

    @Test
    void CynologistAdvice() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.CYNOLOGIST_ADVICE.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Советы кинолога.\n" +
                        "1. Начало начал\n" +
                        "Возраст собаки: Казалось бы, этот пункт не требует обсуждения. На вопрос: «С какого возраста можно дрессировать собаку?» большинство" +
                        " уверенно ответит: «С детства». Только вот где это детство начинается и где заканчивается, вряд ли кто-нибудь скажет. Поэтому подскажем мы." +
                        " Кинологи рекомендуют начинать обучение с 8-и месяцев. С этого возраста собака воспринимает команды хозяина без ущерба для собственной" +
                        " психики. До 8-ми месяцев щенка нужно не учить, а воспитывать." +
                        "Обязательная экипировка:\n" +
                        "\n" +
                        "Поводок\n" +
                        "Ошейник\n" +
                        "Намордник\n" +
                        "Вода! Вы же не забываете брать воду, когда идёте в спортзал? Вот и ваш любимец может захотеть попить.\n" +
                        "Лакомства\n" +
                        "Сумочка для лакомств (зачем, расскажем чуть позже)." +
                        "2. Готовимся покорять интеллектуальный олимп вместе\n" +
                        "Способ поощрения: Перед тем, как начать покорять вершины мировой дрессировки, выберете метод, которым будете поощрять своего питомца." +
                        " Сделать это будет не сложно, ибо их всего два:\n" +
                        "Поощрение игрушкой\n" +
                        "Поощрение лакомствами\n" +
                        "Для того, чтобы у собаки была мотивация в получении лакомства, перед тренировкой пропустите один приём пищи. Так собачка захочет" +
                        " получить лакомство ещё сильнее.Из списка вкусняшек мы сразу исключаем:" +
                        " сосиски, колбасу, сыр и прочие жирные продукты. Всё вышеперечисленное может изрядно подкосить здоровье вашего любимца, чего мы," +
                        " естественно, не хотим. Один из основных принципов " +
                        "дрессировки — поощрять собаку СРАЗУ после того, как она выполнила команду. Тобишь, когда Тузик принёс мячик, он должен получить свою" +
                        " заслуженную вкусняшку, а не ждать пока вы, наконец, найдёте то," +
                        " что нужно. Собака забудет, за что её похвалили. Помните, лакомство должно быть маленьким! Иначе животное отвлечётся от процесса" +
                        " тренировки.\n" +
                        " Выбор места: Новичкам лучше всего начинать с квартиры. Там и раздражителей поменьше, и обстановка для питомца максимально " +
                        "знакомая. По мере усвоения, можете перейти на собачьи площадки и улицу. Важно! Всё время менять локацию! Если вы тренируете собаку только дома," +
                        " выполнять команду на улице она не станет.\n" +
                        "3. Как сделать так, чтобы для питомца дрессировка стала самым лакомым моментом?\n" +
                        "5 простых правил, как сделать дрессировку весёлой для вас и вашего питомца:\n" +
                        "1. ОЧЕНЬ много хвалить и поощрять собаку на первых порах. Первые тренировки — не что иное, как конфетно-букетный период. Комплименты," +
                        " восхищение, угощения — делайте всё, чтобы собака чувствовала себя лучшим существом на планете. А чтобы хозяин возводил её на небеса, всего-то " +
                        "надо принести мячик и прижать свою попу по команде.\n" +
                        "2. Делать перерывы по 5-10 минут. Не забывайте, что собака — живое существо." +
                        " Во время отдыха не тревожьте животинку, пусть питомец поделает то, что ему хочется. Как только собака привыкнет к интеллектуальным нагрузкам, " +
                        "вы сможете сокращать перерывы. Но первый месяц пёсель должен отдыхать как минимум 10-20 минут за тренировку.\n" +
                        "3. Не очеловечивайте животное и не злитесь, если питомец вас не понимает." +
                        " Сами по себе слова «сидеть», «лежать» и «апорт» для вашего питомца пустые звуки. Собакен ассоциирует команду с конкретным" +
                        " действием только после длительных тренировок. Если бы ваш пёсель понимал человеческий, к чему бы были нужны все эти дрессировки?\n" +
                        "4. Быть на позитиве! За тысячелетнее соседство с человеком, собаки научились отлично понимать настроение своего хозяина по тону голоса, жестам," +
                        " мимике. Если вы приступили к занятиям с угрюмым настроением, с вероятностью 99,9% ничего не получится. Пёсель, как и вы, захочет поскорее" +
                        " закончить тренировку. Поэтому очень важно получать от процесса неподдельный кайф. Наслаждайтесь общением с вашим любимцем, вашими успехами, свежим воздухом.\n" +
                        "5. Самое главное, сделайте так, чтобы собаке ХОТЕЛОСЬ возвращаться к занятиям. Нельзя, чтобы тренировка превращалась для собаки в каторгу, куда её ведут волоком" +
                        " и заставляют насильно выполнять то, что ей не нравится. Тренировка должна быть наполнена лакомыми моментами. Общение с хозяином, вкусняшки, похвала — это ли не" +
                        " рай для любого питомца?");
    }

    @Test
    void ListOfVerifiedCatenors() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.LIST_OF_CYNOLOGISTS.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Список проверенных кинологов.\n" +
                        "Разенков Николай г. Санкт-Петербург\n" +
                        "Антон Иванов, г. Омск\n" +
                        "Тукмачев Сергей, г. Ижевск\n" +
                        "Колчанов Евгений, г. Ижевск\n");
    }

    @Test
    void ReasonsForRefusal() throws URISyntaxException, IOException {
        String json = Files.readString(
                Path.of(TelegramBotUpdatesListenerTest.class.getResource("update.callbackquery.json").toURI()));
        Update update = BotUtils.fromJson(json.replace("%data%", Buttons.REASONS_FOR_REFUSAL.toString()), Update.class);

        out.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBotMock).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        InlineKeyboardMarkup keyboardMarkup = (InlineKeyboardMarkup) actual.getParameters().get("reply_markup");

        Assertions.assertNotNull(keyboardMarkup);

        Assertions.assertEquals(actual.getParameters().get("chat_id"), update.callbackQuery().from().id());
        Assertions.assertEquals(actual.getParameters().get("text"),
                "Причины отказа.\n" +
                        "Существует пять причин, по которым чаще всего отказывают желающим «усыновить» домашнего любимца.\n" +
                        "1 Большое количество животных дома\n" +
                        "2 Нестабильные отношения в семье\n" +
                        "3 Наличие маленьких детей\n" +
                        "4 Съемное жилье\n" +
                        "5 Животное в подарок или для работы");
    }
}