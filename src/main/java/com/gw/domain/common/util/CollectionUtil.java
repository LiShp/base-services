package com.gw.domain.common.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zoujialiang
 */
@Component
public class CollectionUtil {

    /**
     * 按int String 类型降序
     *
     * @param list
     * @param key
     */
    public static void listSort(List<Map<String, Object>> list, String key) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = Integer.parseInt(o1.get(key).toString());
                Integer name2 = Integer.parseInt(o2.get(key).toString());
                return name2.compareTo(name1);
            }
        });
    }

    /**
     * 按date类型升序 注意处理日期为null的值
     *
     * @param list
     * @param key
     */
    public static void listSortDate(List<Map<String, Object>> list, String key) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = format.parse("0000-0000-00 00:00:00.000");
                    date2 = format.parse("0000-0000-00 00:00:00.000");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    if (null != o1.get(key)) {
                        date1 = format.parse((String) o1.get(key));
                    }
                    if (null != o2.get(key)) {
                        date2 = format.parse((String) o2.get(key));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date1.compareTo(date2);
            }
        });
    }

    /**
     * List集合分批
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list) {
        //数据库批量操作1000条数据会报错在in的情况下，
        int len = 500;
        //声明返回对象存值
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        //计算循环次数
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
