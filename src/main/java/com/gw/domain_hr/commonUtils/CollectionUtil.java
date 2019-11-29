package com.gw.domain_hr.commonUtils;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class CollectionUtil {

    public static void listSort(List<Map<String, Object>> list, String key) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = Integer.parseInt(o1.get(key).toString());
                Integer name2 = Integer.parseInt(o2.get(key).toString());
                return name1.compareTo(name2);
            }
        });
    }
}
