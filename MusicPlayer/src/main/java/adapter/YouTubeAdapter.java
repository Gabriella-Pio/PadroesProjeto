package adapter;

import com.google.api.services.youtube.model.SearchResult;

public class YouTubeAdapter implements IPlayableItem {
    private SearchResult video;

    public YouTubeAdapter(SearchResult video) {
        this.video = video;
    }

    @Override
    public String getTrackName() {
        return video.getSnippet().getTitle();
    }

    @Override
    public String getArtistName() {
        return video.getSnippet().getChannelTitle();
    }

    @Override
    public void play() {
        try {
            String videoId = video.getId().getVideoId();
            String url = "https://www.youtube.com/watch?v=" + videoId;
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getSource() {
        return "YOUTUBE";
    }
}