package com.example.demoanimalbot.model.keyboardButtons;

public enum Buttons {
    /**
     * Для объекта САТ
     */
    CAT("Приют для кошек"),
    CAT_INFO("Информация о приюте"),
    TAKE_CAT("Взять питомца из приюта"),
    REPORT_CAT("Прислать отчет о питомце"),
    HELP_CAT("Вызвать волонтера"),





    /**
     * Для объекта DOG
     */
    // 1 Этап
    DOG("Приют для собак"),
    DOG_INFO("Информация о приюте"),
    TAKE_DOG("Взять питомца из приюта"),
    REPORT_DOG("Прислать отчет о питомце"),
    // 2 Этап
    INFO_DOG_SHELTER("Общая информация о приюте"),
    OPENING_DIRECTIONS_DOG("Расписание работы приюта и адрес, схема проезда."),
    CONTACT_REGISTRATION_CAR_DOG("Контактные данные охраны для оформления пропуска на машину."),
    SAFETY_DOG_SHELTER("Общие рекомендации по технике безопасности"),
    CONTACTS_FOR_COMMUNICATION_DOG("Оставить свои контакты для связи."),
    HELP_DOG("Вызвать волонтера"),
    BACK_DOG("Вернуться назад"),
    // 3 Этап
    INTRODUCING_THE_DOG("Знакомство с собаками."),
    REQUIRED_DOCUMENTS_DOG("Необходимые документы."),
    TRANSPORT_RECOMMENDATIONS_DOG("Рекомендаций по транспортировке."),
    HOUSE_FOR_THE_PUPPY_RECOMMENDATION_DOG("Дом для щенка. Рекомендации."),
    HOUSE_FOR_ADULT_ANIMAL_RECOMMENDATION_DOG("Дом для взрослого животного. Рекомендации."),
    HOUSE_FOR_ANIMAL_WITH_DISABILITIES_RECOMMENDATION_DOG("Дом для животного с ограниченными возможностями. Рекомендации."),
    CYNOLOGIST_ADVICE_DOG("Советы кинолога."),
    LIST_OF_VERIFIED_CATENORS_DOG("Список проверенных кинологов."),
    REASONS_FOR_REFUSAL_DOG("Причины отказа."),
    DOG_CONTACTS("Контакт"),
    BACK_DOG1("Вернуться назад");

    private String title;

    Buttons(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
