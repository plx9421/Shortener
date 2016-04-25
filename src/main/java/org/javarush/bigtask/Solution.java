package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 17.03.2016.
 */
public class Solution {
    //    для переданного множества строк возвращать множество
//    идентификаторов. Идентификатор для каждой отдельной строки нужно
//    получить, используя  shortener.
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> longSet = new HashSet<>();
        for (String s : strings) longSet.add(shortener.getId(s));
        return longSet;
    }

    //    возвращать множество строк, которое соответствует переданному
//    множеству идентификаторов.
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();
        for (Long l : keys) stringSet.add(shortener.getString(l));
        return stringSet;
    }

    //    Метод будет
//    тестировать работу переданной стратегии на определенном количестве
//    элементов elementsNumber. Реализация метода должна:
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName().toString());

        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        Date startTime = new Date();
        Set<Long> longSet = getIds(shortener, stringSet);
        Date stopTime = new Date();
        String st = Long.toString(stopTime.getTime() - startTime.getTime());
        Helper.printMessage(st);

        startTime = new Date();
        Set<String> stringSet1 = getStrings(shortener, longSet);
        stopTime = new Date();
        st = Long.toString(stopTime.getTime() - startTime.getTime());
        Helper.printMessage(st);

        if (stringSet.size() == stringSet1.size() &&
                stringSet.containsAll(stringSet1)) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");

    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10_000);
        testStrategy(new OurHashMapStorageStrategy(), 10_000);
        testStrategy(new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10_000);
        testStrategy(new HashBiMapStorageStrategy(), 10_000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10_000);
    }
}
