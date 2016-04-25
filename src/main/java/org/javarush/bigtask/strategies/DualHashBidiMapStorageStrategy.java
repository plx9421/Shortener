package com.javarush.test.level33.lesson15.big01.strategies;


import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Map;

/**
 * Created by Alexey on 21.03.2016.
 */
public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap data = new DualHashBidiMap();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);

    }

    @Override
    public Long getKey(String value) {
//        for (Map.Entry<Long, String> m: data.entrySet()){
//            if (m.getValue().equals(value)) return m.getKey();
//        }
//        return null;

        return (Long) data.getKey(value);

    }

    @Override
    public String getValue(Long key) {
        return (String) data.get(key);
    }
}
