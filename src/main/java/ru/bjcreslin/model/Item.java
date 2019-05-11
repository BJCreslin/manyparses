package ru.bjcreslin.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@MappedSuperclass
@Data
public abstract class Item  {

}
