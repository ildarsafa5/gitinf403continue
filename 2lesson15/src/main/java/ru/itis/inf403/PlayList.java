package ru.itis.inf403;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayList {
    private List<Track> playlist;

    public PlayList() {}

    public void save() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("playlist.pst"))) {
            oos.writeObject(playlist);
            oos.flush();
        } catch(IOException r) {
            throw new RuntimeException();
        }
    }

    public void load() {
        if (new File("playlist.pst").length()==0) {
            playlist = new ArrayList<>();
            return;
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("playlist.pst"))) {
            playlist = (List<Track>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }


    public void add(Track track) {
        track.setNumber(playlist.size()+1);
        playlist.add(track);
        save();
    }

    public void showAll() {
        for (Track track: playlist) {
            System.out.println(track.getNumber() + ": " + track.getAuthor() + " " + track.getName());
        }
    }

    public void findByName(String name) {
        System.out.println("huy");
        playlist
                .stream()
                .filter(p -> p.getName().toUpperCase().contains(name.toUpperCase()))
                .forEach(track -> System.out.println(track.getNumber() + ": " + track.getAuthor() + " " + track.getName()));
    }

    public void findByAuthor(String name) {
        playlist
                .stream()
                .filter(p -> p.getAuthor().toUpperCase().contains(name.toUpperCase()))
                .forEach(track -> System.out.println(track.getNumber() + ": " + track.getAuthor() + " " + track.getName()));
    }

    public Optional<Track> findByNumber(Integer number) {
        return playlist
                .stream()
                .filter(track -> track.getNumber().equals(number))
                .findFirst();
    }

    public List<Track> getPlaylist() {
        return playlist;
    }
}
