package ru.itis.inf403.database;

import ru.itis.inf403.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DBUtils {
    public static final String TABLE = "db/student.tbl";
    public static List<Index> index = new ArrayList();
    static class Index implements Serializable {
        int id;
        long position;

        public Index(int id, long position) {
            this.id = id;
            this.position = position;
        }
        @Override
        public boolean equals(Object object) {
            if (id == ((Index)object).id && position == ((Index)object).position) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (int)(position*31)+id;
        }
    }
    private static void sort() {
        index = new ArrayList(index.stream()
                .sorted((o1,o2) -> o1.id - o2.id)
                .toList());
    }

    public static void writeIndex() {
        try(DataOutputStream dos = new DataOutputStream(
                    new FileOutputStream("db/student.idx"))) {
            for (int i = 0; i < index.size(); i++) {
                byte[] data = null;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                    oos.writeObject(index.get(i));
                    data = baos.toByteArray();
                    dos.writeInt(data.length);
                    dos.write(data);
                }
            }
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void readIndex() {
        try(DataInputStream dis = new DataInputStream(new FileInputStream("db/student.idx"))) {
            while(true) {
                int size = dis.readInt();
                byte[] data = new byte[size];
                dis.read(data);
                try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                    Index x = (Index) ois.readObject();
                    index.add(x);
                }
            }

        } catch(FileNotFoundException k) {
            index = new ArrayList<>();
        } catch(EOFException f) {}
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public static Student findStudent(int id) {
        if (index.isEmpty()) {
            return null;
        }
        int left = 0;
        int right = index.size()-1;
        int mid;
        while(left<=right) {
            mid = (left+right)/2;
            if (index.get(mid).id>=id) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        if (left==index.size()) {
            return null;
        }
        Index cur = index.get(left);
        if (cur.id != id) {
            return null;
        }
        long position1 = cur.position;
        try(DataInputStream dos = new DataInputStream(new FileInputStream("db/student.tbl"))) {
            dos.skipBytes((int)position1);
            dos.skipBytes(5);
            int size = dos.readInt();
            byte[] data = new byte[size];
            dos.read(data);
            try(ByteArrayInputStream baos = new ByteArrayInputStream(data);ObjectInputStream oos = new ObjectInputStream(baos)) {
                Student student = (Student) oos.readObject();
                return student;
            }
        } catch (EOFException f) {}
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
        return null;
    }

    public static void appendObject(Student student) {
        if (findPosition(student.getId())!=-1) {
            editStudent(student);
            return;
        }

        byte[] studentdata = null;

        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(student);
            studentdata = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        File file = new File(TABLE);
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file,true))) {
            dos.writeInt(student.getId());
            dos.writeByte(1);
            dos.writeInt(studentdata.length);
            dos.write(studentdata);
            dos.flush();
        } catch (IOException e) {
            //Не удалось записать данные
            throw new RuntimeException("Не удалось записать файл");
        }
        index.add(new Index(student.getId(),findPosition(student.getId())));
        sort();
    }

    public static List<Student> readAll() {
        List<Student> result = new ArrayList<>();
        File file = new File(TABLE);
        if (!file.exists()) {
            return null;
        }

        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            while(true) {
                int id = dis.readInt();
                byte flag = dis.readByte();
                int size = dis.readInt();
                byte[] data = new byte[size];
                dis.read(data);
                if (flag == 1) {
                    try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                        Student student = (Student) ois.readObject();
                        result.add(student);
                    }
                }
            }
        } catch (EOFException e) {}
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
        return result;
    }

    public static void editStudent(Student student) {
        if (findPosition(student.getId()) == -1) {
            return;
        }
        long position = findPosition(student.getId());
        // Меняем 1 байт в файле
        File file = new File(TABLE);
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(position + 4);
            raf.write(0); // Write byte 0 (overwrites original byte at this offset).
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        appendObject(student);
        for (int i = 0; i < index.size(); i++) {
            Index index1 = index.get(i);
            if (index1.id == student.getId()) {
                index1.position = findPosition(student.getId());
                break;
            }
        }
    }

    public static void delete(int id) {
        if (findPosition(id) == -1) {
            return;
        }
        long position = findPosition(id);
        // Меняем 1 байт в файле
        File file = new File(TABLE);
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(position + 4);
            raf.write(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        index.remove(new Index(id,position));
    }

    public static long findPosition(int searchId) {
        long position = 0;
        boolean searchFlag = false;

        File file = new File(TABLE);
        if (!file.exists()) {
            return -1;
        }

        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            while(true) {
                int id = dis.readInt();
                byte flag = dis.readByte();
                int size = dis.readInt();
                dis.skipBytes(size);
                if (flag == 1 && id == searchId) {
                    searchFlag = true;
                    break;
                }
                position += 4 + 1 + 4 + size;
            }
        } catch(EOFException e) {}
        catch (IOException e) {
            throw new RuntimeException();
        }

        if (searchFlag) {
            return position;
        }
        return -1;
    }

    public static String htmlBuild(){
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");

        html.append("<head>\n");
        html.append("    <meta charset=\"utf-8\"/>\n");
        html.append("</head>\n");

        html.append("<body>\n");
        html.append("<h1>Студенты</h1>\n");
        html.append("<table>\n");
        html.append("    <tr>\n");
        html.append("        <th>id</th><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Группа</th>\n");
        html.append("    </tr>\n");

        for(Student student : readAll()){

            html.append("    <tr>\n");
            html.append("        <td>" + student.getId() + "</td>");
            html.append("        <td>" + student.getLastName() + "</td>");
            html.append("        <td>" + student.getName() + "</td>");
            html.append("        <td>" + student.getFatherName() + "</td>");
            html.append("        <td>" + student.getGroup() + "</td>");
            html.append("    </tr>\n");
        }
        html.append("</table>\n");
        html.append("</body>\n");
        html.append("</html>");
        return html.toString();
    }
    public static void returnFile(){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter("db/students.html"))){
            bf.write(htmlBuild());
            bf.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
