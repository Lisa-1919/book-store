package com.bookstoreversion2.data.entities;

public enum Status {
    PROCESSING("Обработка"),
    WAITING_OF_DELIVERY("Ожидание доставки"),
    NOT_RECEIVED("Не получен"),
    RECEIVED("Получен");

    private String statusName;

    Status(String statusName){
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
