package ru.bjcreslin.service;

import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.Item;

public interface ParserItem {

     Item getItemByCode(Long code) throws WebParserException;
    //public Item getItemByaddress(String address);

}
