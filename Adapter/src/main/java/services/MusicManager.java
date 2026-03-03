/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import adapter.IPlayableItem;
import adapter.LocalMusicAdapter;
import adapter.YouTubeAdapter;

import entity.LocalMusic;
import repository.LocalMusicRepository;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author gabriella
 */
public class MusicManager {

  private static MusicManager instance;
  private LocalMusicRepository localRepository;
  private YouTube youtubeService;
  private String apiKey;

  // Construtor privado: ninguém pode instanciar a não ser o getInstance() - método de acesso global
  private MusicManager() {
    // Carrega a chave do arquivo .env
    Dotenv dotenv = Dotenv.load();
    this.apiKey = dotenv.get("YOUTUBE_API_KEY");

    // Detecta pasta de músicas do sistema do usuário
    String musicFolder = System.getProperty("user.home") + "/Músicas";
    this.localRepository = new LocalMusicRepository(musicFolder);

    // Inicializa o serviço do YouTube
    try {
      youtubeService = new YouTube.Builder(
          new com.google.api.client.http.javanet.NetHttpTransport(),
          new com.google.api.client.json.gson.GsonFactory(),
          request -> {
          }).setApplicationName("Musicoteca-Adapter").build();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Ponto de acesso global — garante instância única. */
  public static synchronized MusicManager getInstance() {
    if (instance == null) {
      instance = new MusicManager();
    }
    return instance;
  }

  /**
   * Carrega todas as músicas disponíveis (locais + Youtube).
   */
  public List<IPlayableItem> getAllTracks() {
    List<IPlayableItem> allMusics = new ArrayList<>();

    // Busca e adapta músicas locais
    try {
      List<LocalMusic> localMusics = localRepository.findAll();
      for (LocalMusic lm : localMusics) {
        allMusics.add(new LocalMusicAdapter(lm));
      }
    } catch (Exception e) {
      System.err.println("Erro ao carregar músicas locais: " + e.getMessage());
    }

    // Busca e adapta músicas do YouTube
    try {
      YouTube.Search.List search = youtubeService.search().list(Collections.singletonList("id,snippet"));
      search.setKey(apiKey);
      search.setQ("rock");
      search.setType(Collections.singletonList("video"));
      search.setMaxResults(10L);

      SearchListResponse response = search.execute();
      List<SearchResult> results = response.getItems();

      for (SearchResult res : results) {
        allMusics.add(new YouTubeAdapter(res));
      }
    } catch (Exception e) {
      System.err.println("Erro ao carregar YouTube: " + e.getMessage());
    }
    return allMusics;
  }
}
