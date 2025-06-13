package ru.itis.inf403;

import java.lang.reflect.Field;

public class JSONMapper {

    public String toJson(Object obj) throws IllegalAccessException{
        Field[] fields = obj.getClass().getFields();
        StringBuilder sb = new StringBuilder("{");
        for (Field f : fields) {
            sb.append("\"").append(f.getName()).append("\":");
            if (f.getType().equals(String.class)) {
                sb.append("\"").append(f.get(obj)).append("\":");
            } else if (f.getType().getSuperclass().equals(Number.class)) {
                sb.append(f.get(obj).toString());
            } else {
                sb.append("\"").append(f.get(obj).toString()).append("\":");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");

        return sb.toString();
    }

    public Object parseJSON(String json, Class clazz) {
        return null;
    }


}
