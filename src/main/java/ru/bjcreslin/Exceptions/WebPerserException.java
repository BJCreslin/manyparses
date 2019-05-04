package ru.bjcreslin.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebPerserException extends Exception {
    private String address;
}
