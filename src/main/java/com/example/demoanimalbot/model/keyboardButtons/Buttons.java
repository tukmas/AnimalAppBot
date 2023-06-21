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
    INFO("О приюте"),
    TAKE("Взять питомца"),
    REPORT("Прислать отчет о питомце"),
    // 2 Этап
    SHELTER("Общая информация"),
    CONTACTS("Контакты"),
   // CONTACT_REGISTRATION_CAR("Контактные данные охраны для оформления пропуска на машину."),
    SAFETY_SHELTER("Правила поведения в приюте"),
    BACK_CONTACTS("Оставить свои контакты для связи."),
    BACK("Вернуться назад"),
    // 3 Этап
    INTRODUCING("Знакомимся с питомцем."),
    DOCUMENTS("Необходимые документы."),
    TRANSPORTATION("Рекомендаций по транспортировке."),
    PUPPY_HOME("Дом для маленького питомца. Рекомендации."),
    PET_HOME("Дом для взрослого животного. Рекомендации."),
    DISABLED_PET_HOME("Дом для животного с ограниченными возможностями. Рекомендации."),
    CYNOLOGIST_ADVICE("Советы кинолога."),
    LIST_OF_CYNOLOGISTS("Список проверенных кинологов."),
    REASONS_FOR_REFUSAL("Причины отказа."),
    BACK1("Вернуться назад");

    private final String title;

    Buttons(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
