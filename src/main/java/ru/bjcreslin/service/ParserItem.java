package ru.bjcreslin.service;

import ru.bjcreslin.Exceptions.WebPerserException;
import ru.bjcreslin.model.Item;

public interface ParserItem {

     Item getItemByCode(Long code) throws WebPerserException;
    //public Item getItemByaddress(String address);

}
