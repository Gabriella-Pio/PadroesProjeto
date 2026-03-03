package repository;

import entity.LocalMusic;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriella
 */
public class LocalMusicRepository {
    
    private String musicFolderPath;

    public LocalMusicRepository(String folderPath) {
        this.musicFolderPath = folderPath;
    }

    public List<LocalMusic> findAll() {
        List<LocalMusic> musics = new ArrayList<>();
        File folder = new File(musicFolderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("[Repository] Pasta não encontrada: " + musicFolderPath);
            return musics;
        }

        File[] files = folder.listFiles((dir, name) -> {
            String lower = name.toLowerCase();
            return lower.endsWith(".mp3") ||
                    lower.endsWith(".wav") ||
                    lower.endsWith(".flac");
        });

        if (files != null) {
            for (File file : files) {
                musics.add(new LocalMusic(file.getAbsolutePath()));
            }
        }

        System.out.println("[Repository] " + musics.size() + " arquivos encontrados em: " + musicFolderPath);
        return musics;
    }
}
