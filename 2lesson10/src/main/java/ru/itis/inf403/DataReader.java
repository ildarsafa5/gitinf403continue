package ru.itis.inf403;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static void main(String[] args) {
        DataReader reader = new DataReader();
        List<RawRow> rawrows = reader.readFile("med.csv");
        List<Row> rows = reader.clearData(rawrows);
    }


    public List<RawRow> readFile(String filename) {
        List<RawRow> result = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            return result;
        }
        try (InputStream is = new FileInputStream(file);
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader)
        ) {
            br.readLine();
            while (br.ready()) {
                String[] array = br.readLine().split(";");
                if (array.length<24) continue;
                RawRow row = new RawRow();
                row.setBmi(array[16]);
                row.setSmokingstatus(array[19]);
                row.setPsCVRM(array[18]);
                row.setDiastolicBloodPressure(array[14]);
                row.setSystolicBloodPressure(array[13]);
                row.setTotalCholesterol(array[11]);
                row.setGlucoseFasting(array[9]);
                row.setMdrd(array[7]);
                row.setRiskScoreCVRM(array[6]);
                row.setHypertension(array[23]);
                row.setAge(array[22]);
                result.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Row> clearData(List<RawRow> raw) {
        return raw.stream()
                .filter(r -> !r.badRecord())
                .map(b -> converter(b))
                .toList();

    }

    public Row converter(RawRow bad) {
        Row r = new Row();
        r.setRiskScoreCVRM(Integer.parseInt(bad.getRiskScoreCVRM()));
        r.setMdrd(Integer.parseInt(bad.getMdrd()));
        r.setAge(Integer.parseInt(bad.getAge()));
        return r;
    }

    public void tree() {
        Node root = new Node();

        Node[] tree = new Node[63];

        for (int i = 0; i < tree.length; i++) {
            tree[i].setFunction(
                    r ->
            );
        }
    }
}
