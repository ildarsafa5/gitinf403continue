package ru.itis.inf403;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileHandler {

    @Test
    void testMerge() {
        long sum = new File("photo.jpg").length()+new File("pushkin.txt").length();
        FileHandler.merge("pushkin.txt","photo.jpg","merge.txt");
        long fin = new File("merge.txt").length();
        assertTrue(fin == sum);
        try (InputStream is = new FileInputStream("pushkin.txt");InputStream is2 = new FileInputStream("merge.txt")) {
            int cnt = 0;
            int i;
            List<Character> list1 = new ArrayList();
            while ((i = is.read()) > -1) {
                list1.add((char)i);
            }

            List<Character> list2 = new ArrayList();
            while(cnt<list1.size()) {
                list2.add((char)is2.read());
                cnt++;
            }
            for (int j = 0; j < list1.size(); j++) {
                assertEquals(list1.get(j),list2.get(j));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckJSON() {
        try {
            assertEquals(FileHandler.checkJSON("adding.json"),5);
            assertThrows(NullPointerException.class,() -> FileHandler.checkJSON(null));
            assertThrows(FileNotFoundException.class, () -> FileHandler.checkJSON("ggfd.json"));
            assertThrows(IllegalArgumentException.class, () -> FileHandler.checkJSON("adding2.json"));
            assertEquals(FileHandler.checkJSON("adding1.json"),0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException r) {
            r.printStackTrace();
        }
    }
}
