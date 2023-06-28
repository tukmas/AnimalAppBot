package com.example.demoanimalbot.service;

public enum ReportAnswers {
    BAD_REPORT("Недобросовестное заполнение отчета"),
    PROBATION_EXTENSION_14_DAYS("Продление испытатльного срока на 14 дней"),
    PROBATION_EXTENSION_30_DAYS("Продление испытатльного срока на 30 дней"),
    SUCCESSFUL_END_OF_PROBATION("Успешное завершение испытатльного срока"),
    UNSUCCESSFUL_END_OF_PROBATION("Испытательный срок не пройден");

    private String title;

    ReportAnswers(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
