package ru.bjcreslin.service;

import ru.bjcreslin.Exceptions.WebPerserException;
import ru.bjcreslin.model.Item;

public interface ItemParser {

     Item getItemByCode(Long code) throws WebPerserException;
    //public Item getItemByaddress(String address);

}
