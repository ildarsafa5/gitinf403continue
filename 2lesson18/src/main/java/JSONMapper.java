
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class JSONMapper {

    public String toJson(Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder("{");

        for (Field f : fields) {
            sb.append("\"").append(f.getName()).append("\":");
//            f.setAccessible(true);

            Method getter = obj.getClass().getMethod("get"+f.getName().toUpperCase().charAt(0) + f.getName().substring(1));
            if (f.getType().equals(String.class)) {
                sb.append("\"").append(getter.invoke(obj)).append("\"");
            } else if (f.getType().getSuperclass().equals(Number.class)) {
                sb.append(getter.invoke(obj).toString());
            } else {
                sb.append("\"").append(getter.invoke(obj).toString()).append("\"");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");

        return sb.toString();
    }

    public Object parseJSON(String json, Class clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object object = clazz.newInstance();
        String[] array = json.replaceAll("\"","").replaceAll("}","").replace("{","").split(",");
        HashMap<String,String> hashMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            hashMap.put(array[i].split(":")[0], array[i].split(":")[1]);
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            String title = f.getName().toUpperCase().charAt(0) + f.getName().substring(1);
            Method setter = object.getClass().getMethod("set" + title,f.getType());
            if (f.getType() == Integer.class) {
                setter.invoke(object, Integer.parseInt(hashMap.get(f.getName())));
            } else if (f.getType() == Double.class) {
                setter.invoke(object, Double.parseDouble(hashMap.get(f.getName())));
            } else if (f.getType() == Long.class) {
                setter.invoke(object, Long.parseLong(hashMap.get(f.getName())));
            } else if (f.getType() == String.class) {
                setter.invoke(object, hashMap.get(f.getName()));
            }
        }
        return object;
    }


}
