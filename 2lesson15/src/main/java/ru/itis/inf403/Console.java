package ru.itis.inf403;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class Console {
    public PlayList playList;
    private Track currentTrack;

    public Console() {
        playList = new PlayList();
        playList.load();
    }

    public void showMenu() {
        if (currentTrack == null) {
            System.out.println("Ничего не проигрывается");
        } else {
            System.out.println("Проигрывается " + currentTrack);
        }
        System.out.println("   1. Показать все треки\n" +
                "   2. Найти трек по названию\n" +
                "   3. Найти трек по автору\n" +
                "   4. Проиграть трек по номеру\n" +
                "   5. Остановить проигрывание\n" +
                "   6. Следующий трек\n" +
                "   7. Предыдущий трек\n" +
                "   8. Добавить трек\n" +
                "   9. Найти трек по номеру\n" +
                "   10. Выход");
    }

    public void startCommand() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            showMenu();
            System.out.println("Введите команду");
            int command = scanner.nextInt();
            flag = commandhandler(command);
        }
    }

    public boolean commandhandler(int command) {
        Scanner sc = new Scanner(System.in);
        switch (command) {
            case 10:
                return false;
            case 1:
                playList.showAll();
                break;
            case 4:
                System.out.println("Введите номер трека");
                int i = sc.nextInt();
                if (i > playList.getPlaylist().size()) {
                    System.out.println("Трека под таким номером не существует");
                    break;
                }
                currentTrack = playList.getPlaylist().get(i-1);
                PlaySound.play(currentTrack);
                break;
            case 2:
                System.out.println("Введите название трека");
                playList.findByName(sc.nextLine());
                break;
            case 3:
                System.out.println("Введите автора");
                playList.findByAuthor(sc.nextLine());
                break;
            case 9:
                System.out.println("Введите номер трека");
                Optional track = playList.findByNumber(sc.nextInt());
                if (track.isEmpty()) {
                    System.out.println("Трека под таким номером не существует");
                    break;
                }
                System.out.println(track.get());
                break;
            case 8:
                System.out.println("Введите путь до файла с треком");
                String path = sc.nextLine();
                if (!new File(path).exists()) {
                    System.out.println("Такого файла не существует");
                    break;
                }
                System.out.println("Введите названия трека");
                String name = sc.nextLine();
                System.out.println("Введите название автора");
                String author = sc.nextLine();
                playList.add(new Track(name,author,path));
                playList.save();
                break;
            case 5:
                if (currentTrack == null) {
                    System.out.println("Ничего не проигрывается");
                    break;
                }
                PlaySound.getClip().stop();
                currentTrack = null;
                break;
            case 6:
                if (currentTrack == null) {
                    System.out.println("Сейчас ничего не проигрывается");
                    break;
                }
                int cur = currentTrack.getNumber()-1+1;
                if (cur == playList.getPlaylist().size()) {
                    System.out.println("Следующего трека нет");
                    break;
                }
                PlaySound.getClip().stop();
                PlaySound.play(playList.getPlaylist().get(cur));
                currentTrack = playList.getPlaylist().get(cur);
                break;
            case 7:
                if (currentTrack == null) {
                    System.out.println("Сейчас ничего не проигрывается");
                    break;
                }
                int curN = currentTrack.getNumber()-1-1;
                if (curN == -1) {
                    System.out.println("Предыдущего трека нет");
                    break;
                }
                PlaySound.getClip().stop();
                PlaySound.play(playList.getPlaylist().get(curN));
                currentTrack = playList.getPlaylist().get(curN);
                break;
        }
        return true;
    }
}
