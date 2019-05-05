package ru.bjcreslin.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Модель для передачи двух кодов со страницы в обработку
 */

@Data
public class DoubleCode implements Serializable {
    private Long firstCode;
    private Long secondCode;
}
