package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 21.03.2016.
 */
public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startTime = new Date();
        for (String s: strings) ids.add(shortener.getId(s));
        Date stopTime = new Date();

        return stopTime.getTime() - startTime.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){

        Date startTime = new Date();
        for (Long l : ids) strings.add(shortener.getString(l));
        Date stopTime = new Date();

        return stopTime.getTime() - startTime.getTime();

    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());


        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> longSet1 = new HashSet<>();
        Set<Long> longSet2 = new HashSet<>();
        Long l1 = getTimeForGettingIds(shortener1, origStrings, longSet1);
        Long l2 = getTimeForGettingIds(shortener2, origStrings, longSet2);
        Assert.assertTrue(l1 > l2);


        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        Long l3 = getTimeForGettingStrings(shortener1, longSet1, s1);
        Long l4 = getTimeForGettingStrings(shortener2, longSet2, s2);
        Assert.assertEquals(l3, l4, 5);
    }

}
