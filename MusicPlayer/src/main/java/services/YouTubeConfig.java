package services;

import io.github.cdimascio.dotenv.Dotenv;

public class YouTubeConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String API_KEY = dotenv.get("YOUTUBE_API_KEY");
}