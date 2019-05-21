package ru.bjcreslin.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrippleCode implements Serializable {
    Long watermanCode;
    Long spCode;
    Long kitCode;
}
