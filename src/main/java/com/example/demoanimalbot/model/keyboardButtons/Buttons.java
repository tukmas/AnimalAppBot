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
    DOG("Приют для собак"),
    DOG_INFO("Информация о приюте"),
    TAKE_DOG("Взять питомца из приюта"),
    REPORT_DOG("Прислать отчет о питомце"),
    INFO_DOG_SHELTER("Общая информация о приюте"),
    SAFETY_DOG_SHELTER("Общие рекомендации по технике безопасности"),
    HELP_DOG("Вызвать волонтера"),
    BACK_DOG("Вернуться назад");

    private String title;

    Buttons(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
