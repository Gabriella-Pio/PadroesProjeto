/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import entity.LocalMusic;
import java.io.File;

/**
 *
 * @author gabriella
 */
public class LocalMusicAdapter implements IPlayableItem {

  private LocalMusic localMusic;
  private String trackName;
  private String artistName;

  public LocalMusicAdapter(LocalMusic localMusic) {
    this.localMusic = localMusic;
    loadMetadata();
  }

  private void loadMetadata() {
    try {
      org.jaudiotagger.audio.AudioFile audio = org.jaudiotagger.audio.AudioFileIO
          .read(new File(localMusic.getAbsolutePath()));
      org.jaudiotagger.tag.Tag tag = audio.getTag();

      String title = tag.getFirst(org.jaudiotagger.tag.FieldKey.TITLE);
      this.trackName = (title != null && !title.isBlank()) ? title : extractNameFromPath();

      String artist = tag.getFirst(org.jaudiotagger.tag.FieldKey.ARTIST);
      this.artistName = (artist != null && !artist.isBlank()) ? artist : "Artista Desconhecido";

    } catch (Exception e) {
      // Fallback: usa nome do arquivo quando não há metadados
      this.trackName = extractNameFromPath();
      this.artistName = "Artista Desconhecido";
    }
  }

  private String extractNameFromPath() {
    String fileName = new File(localMusic.getAbsolutePath()).getName();
    return fileName.replaceAll("\\.(mp3|wav|flac|ogg)$", "");
  }

  @Override
  public String getTrackName() {
    return trackName;
  }

  @Override
  public String getArtistName() {
    return artistName;
  }

  @Override
  public void play() {
    try {
      // Abre o arquivo no player padrão do sistema operacional
      java.awt.Desktop.getDesktop().open(new File(localMusic.getAbsolutePath()));
    } catch (Exception e) {
      System.err.println("[LocalAdapter] Erro ao abrir arquivo: " + e.getMessage());
    }
  }

  @Override
  public String getSource() {
    return "LOCAL";
  }

  @Override
  public String toString() {
    return getTrackName() + " -- " + getArtistName();
  }
}
