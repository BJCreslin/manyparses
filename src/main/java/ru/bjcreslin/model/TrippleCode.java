package ru.bjcreslin.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Модель для передачи трех кодов со страницы в обработку
 */
@Data
public class TrippleCode implements Serializable {
    Long watermanCode;
    Long spCode;
    Long kitCode;
}
