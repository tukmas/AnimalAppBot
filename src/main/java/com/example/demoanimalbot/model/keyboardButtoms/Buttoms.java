package com.example.demoanimalbot.model.keyboardButtoms;

public enum Buttoms {
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
    DOG("Приют для собак"),
    DOG_INFO("Информация о приюте"),
    TAKE_DOG("Взять питомца из приюта"),
    REPORT_DOG("Прислать отчет о питомце"),
    HELP_DOG("Вызвать волонтера");

    private String title;

    Buttoms(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
