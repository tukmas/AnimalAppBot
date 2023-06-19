package com.example.demoanimalbot.model.keyboardButtons;

public enum Buttons {
    /**
     * Для объекта САТ
     */
    CAT("Приют для кошек"),

    /**
     * Для объекта DOG
     */
    // 1 Этап
    DOG("Приют для собак"),
    SHELTER_INFO("Информация о приюте"),
    TAKE_PET("Взять питомца из приюта"),
    REPORT("Прислать отчет о питомце"),
    // 2 Этап
    INFO_SHELTER("Общая информация о приюте"),
    OPENING_DIRECTIONS("Расписание работы приюта и адрес, схема проезда."),
    CONTACT_REGISTRATION_CAR("Контактные данные охраны для оформления пропуска на машину."),
    SAFETY_SHELTER("Общие рекомендации по технике безопасности"),
    CONTACTS_FOR_COMMUNICATION("Оставить свои контакты для связи."),
    BACK("Вернуться назад"),
    // 3 Этап
    INTRODUCING("Знакомство с питомцем."),
    REQUIRED_DOCUMENTS("Необходимые документы."),
    TRANSPORT_RECOMMENDATIONS("Рекомендаций по транспортировке."),
    HOUSE_FOR_THE_PUPPY_RECOMMENDATION("Дом для маленького питомца. Рекомендации."),
    HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION("Дом для взрослого животного. Рекомендации."),
    HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION("Дом для животного с ограниченными возможностями. Рекомендации."),
    CYNOLOGIST_ADVICE_DOG("Советы кинолога."),
    LIST_OF_VERIFIED_CATENORS_DOG("Список проверенных кинологов."),
    REASONS_FOR_REFUSAL("Причины отказа."),
    CONTACTS("Контакты"),
    BACK1("Вернуться назад");

    private final String title;

    Buttons(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
