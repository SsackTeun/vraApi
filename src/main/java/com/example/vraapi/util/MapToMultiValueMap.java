package com.example.vraapi.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Iterator;
import java.util.Map;

public class MapToMultiValueMap {
    public static MultiValueMap<String, String> convert(Map<String, String> map){
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        MultiValueMap convertedMap = new LinkedMultiValueMap();
        for (Map.Entry<String,String> i : map.entrySet()){
            System.out.println(i.getKey() + ":" + i.getValue());
            convertedMap.add(i.getKey(), i.getValue());
        }

        convertedMap.forEach((o, o2) -> {
            System.out.println(o + ":" + o2);
        });
        return convertedMap;
    }
}
