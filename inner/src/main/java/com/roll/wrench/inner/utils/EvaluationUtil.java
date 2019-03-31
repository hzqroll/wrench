package com.roll.wrench.inner.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用反射实现赋值demo
 *
 * @author zongqiang.hao
 * created on 2019-02-27 11:30.
 */
public class EvaluationUtil {

    /**
     * 获取类中的所有成员变量: 包括所有类型, 超类, 超接口
     *
     * @param clazz 类对象
     * @return map, 如果没有对象, 返回长度为0的map
     */
    private static Map<String, Field> getAllField(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>(fields.length);
        for (Field field : fields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    /**
     * 根据给定的变量名复制
     *
     * @param object   对象
     * @param valueMap key:变量名, value 变量值
     */
    public static void evaluationByName(Object object, Map<String, Object> valueMap) {
        if (object == null) {
            throw new NullPointerException("Class不能为Object或者Void");
        }
        Class clazz = object.getClass();
        Map<String, Field> fieldMap = getAllField(clazz);
        for (Map.Entry<String, Object> valueEntry : valueMap.entrySet()) {
            Field field = fieldMap.get(valueEntry.getKey());
            if (field != null) {
                try {
                    if (object.getClass().isAssignableFrom(clazz)) {
                        field.setAccessible(true);
                        if (valueEntry.getValue() instanceof Boolean) {
                            field.setBoolean(object, (Boolean) valueEntry.getValue());
                        }
                        if (valueEntry.getValue() instanceof String) {
                            field.set(object, valueEntry.getValue().toString());
                        } else {
                            field.set(object, valueEntry.getValue());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
