import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        JSONMapper mapper = new JSONMapper();
        Student student = new Student("Сафиуллин","Ильдар","Рустэмович","11-403",18);
        String s = mapper.toJson(student);
        Student student1 = (Student) mapper.parseJSON(s,Student.class);
        System.out.println(student1);
        System.out.println(mapper.toJson(student));
    }
}
