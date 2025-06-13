package ru.itis.inf403.labirint;

import java.io.Serializable;

public class Player implements Serializable {
    private int indx;
    private int indy;
    private int cnt;

    private String[][] pokazpolya;

    private int[][] field;

    public Player() {
        pokazpolya = new String[9][9];
        for (int i = 0; i < pokazpolya.length; i++) {
            for (int j = 0; j < pokazpolya.length; j++) {
                pokazpolya[i][j] = " - ";
            }
        }
        for (int i = 0; i < pokazpolya.length; i++){
            pokazpolya[i][0] = " 0 ";
            pokazpolya[i][8] = " 0 ";
            pokazpolya[0][i] = " 0 ";
            pokazpolya[8][i] = " 0 ";
        }
        field = new int[][] {
                {1,1,1,1,1,1,1,1,1},
                {1,0,0,1,1,1,1,1,1},
                {1,1,0,0,0,1,1,1,1},
                {1,1,0,1,0,1,1,1,1},
                {1,1,0,1,1,1,0,1,1},
                {1,1,0,1,1,1,0,1,1},
                {1,1,0,0,0,0,0,1,1},
                {1,1,1,1,1,1,0,1,1},
                {1,1,1,1,1,1,1,1,1}
        };
        indx = 1;
        indy = 1;
        cnt=0;
    }

    public int getIndx() {
        return indx;
    }

    public void setIndx(int indx) {
        this.indx = indx;
    }

    public int getIndy() {
        return indy;
    }

    public void setIndy(int indy) {
        this.indy = indy;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String[][] getPokazpolya() {
        return pokazpolya;
    }

    public void setPokazpolya(String[][] pokazpolya) {
        this.pokazpolya = pokazpolya;
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }
}
