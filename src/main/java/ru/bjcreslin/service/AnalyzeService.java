package ru.bjcreslin.service;

import ru.bjcreslin.model.Item;

import java.util.List;

/**
 * Что и как анализируем
 */
public interface AnalyzeService {
    /**
     * Находит все элементы, в котрых цена Waterman дороже
     * @return List Элекментов Item
     */
    List<Item> findAllCheaps();
}
