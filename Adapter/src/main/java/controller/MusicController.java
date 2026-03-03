package controller;

import javax.swing.DefaultListModel;

import adapter.IPlayableItem;
import services.MusicManager;

import java.util.List;

/**
 *
 * @author gabriella
 */
public class MusicController {
    private final MusicManager service;

    public MusicController() {
        this.service = MusicManager.getInstance();
    }

    public void loadMusics(DefaultListModel<IPlayableItem> listModel) {
        listModel.clear();
        List<IPlayableItem> musics = service.getAllTracks();
        for (IPlayableItem m : musics) {
            listModel.addElement(m);
        }
    }

    /**
     * Reproduz a faixa selecionada.
     */
    public void playMusic(IPlayableItem item) {
        if (item != null) {
            System.out.println("[Controller] Reproduzindo: " + item.getTrackName() + " — " + item.getArtistName());
            item.play();
        }
    }
}
