package ru.itis.inf403.labirint;


import java.io.*;
import java.util.Scanner;


public class Labirint {
    public static void main(String[] args) {
        starting();
        Player player;
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                if (s.equals("l")) {
                    if (!new File("saving.txt").exists()) {
                        throw new FileNotFoundException();
                    }
                    player = loadingPlayer("saving.txt");
                    break;
                } else if (s.equals("new")) {
                    player = new Player();
                    break;
                } else {
                    throw new RuntimeException("Введены некорректные данные");
                }
            } catch(FileNotFoundException e) {
                System.out.println("Сохраненией нет");
            } catch(RuntimeException e) {
                System.out.println("Введены некорректные данные");
            }
        }
        player.setPokazpolya(mesto(1,1, player.getPokazpolya(), 0));
        vivodmassiva(player.getPokazpolya());
        gameLogic(player);
    }
    public static void starting() {
        System.out.println("Лабиринт");
        System.out.println("Вы не видите стен, но должны за минимальное количество ходов добраться до выхода");
        System.out.println("Для передвижения по полю используйте r(вправо),l(влево),d(вниз),u(вверх)");
        System.out.println("Ударившись об стену, ход будет засчитан, но вы останетесь на том же месте");
        System.out.println("Загрузить последнее сохранение: l");
        System.out.println("Новая игра: new");
    }
    public static String[][] mesto(int x, int y, String[][] shovv,int kuda) {
        String[][] show = shovv;
        show[x][y] = " X ";
        if (kuda == 1) {
            show[x+1][y] = " x ";
        }
        if (kuda == 2) {
            show[x-1][y] = " x ";
        }
        if (kuda == 3) {
            show[x][y-1] = " x ";
        }
        if (kuda == 4) {
            show[x][y+1] = " x ";
        }
        return show;

    }
    public static String[][] stena(int x1,int y1,String[][] shovv1) {
        String[][] show1 = shovv1;
        show1[x1][y1] = " 1 ";
        return show1;
    }
    public static void vivodmassiva(String[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j=0; j < m[0].length; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }
    public static char hod() {
        Scanner hod1 = new Scanner(System.in);
        char hod2 = hod1.next().charAt(0);
        return hod2;
    }
    public static void gameLogic(Player player) {
        int indx = player.getIndx();
        int indy = player.getIndy();
        int[][] x = player.getField();
        int cnt = player.getCnt();
        String[][] z = player.getPokazpolya();
        while (indx != 7 & indy != 7) {
            System.out.println("Сделайте ход");
            char hodit = hod();
            if (hodit == 's') {
                savingPlayer(player);
                return;
            }
            if (hodit == 'u') {
                if (x[indx-1][indy] == 1) {
                    System.out.println("Там стена");
                    z = stena(indx-1,indy,z);
                    vivodmassiva(z);
                    cnt++;
                } else {
                    --indx;
                    z = mesto(indx,indy,z,1);
                    vivodmassiva(z);
                    cnt++;
                }
            }
            if (hodit == 'd') {
                if (x[indx+1][indy] == 1) {
                    System.out.println("Там стена");
                    z = stena(indx+1,indy,z);
                    vivodmassiva(z);
                    cnt++;
                } else {
                    ++indx;
                    z = mesto(indx,indy,z,2);
                    vivodmassiva(z);
                    cnt++;
                }
            }
            if (hodit == 'r') {
                if (x[indx][indy+1] == 1) {
                    System.out.println("Там стена");
                    z = stena(indx,indy+1,z);
                    vivodmassiva(z);
                    cnt++;
                } else {
                    ++indy;
                    z = mesto(indx,indy,z,3);
                    vivodmassiva(z);
                    cnt++;
                }
            }
            if (hodit == 'l') {
                if (x[indx][indy-1] == 1) {
                    System.out.println("Там стена");
                    z = stena(indx,indy-1,z);
                    vivodmassiva(z);
                    cnt++;
                } else {
                    --indy;
                    z = mesto(indx,indy,z,4);
                    vivodmassiva(z);
                    cnt++;
                }
            }
            player.setIndx(indx);
            player.setIndy(indy);
            player.setField(x);
            player.setPokazpolya(z);
            player.setCnt(cnt);
        }
        savingPlayer();
        System.out.println("Вы прошли лабиринт за " + cnt + " ходов");
    }

    public static void savingPlayer(Player player) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saving.txt"))){
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void savingPlayer() {
        Player player = new Player();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saving.txt"))){
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadingPlayer(String filename) {
        Player player = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saving.txt"))) {
            player = (Player) ois.readObject();
            return player;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return player;
    }
}
