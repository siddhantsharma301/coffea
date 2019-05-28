//import java.io.File;
//public class Song
//{
//    private File song;
//    /**
//     * Constructor
//     * Creates a File object and puts song into it
//     * @param path of file to be loaded
//     */
//    public Song (String filePath)
//    {
//        song = new File(Song.class.getResource(filePath).getFile());
//    }
//
//    /**
//     * Returns File path
//     * @return song's file path
//     */
//    public File getSong()
//    {
//        return song;
//    }
//}

import java.io.File;

import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Song {
    private File song;
    private String title;
    private String artist;
    private Media music;
    private MediaPlayer mp;

    public Song(String filePath) {
        song = new File(Song.class.getResource(filePath).getFile());
        music = new Media(song.toURI().toString());
        mp = new MediaPlayer(music);
        artist = (String) mp.getMedia().getMetadata().get("artist");
        title = (String) music.getMetadata().get("title");
        //artist = "test";
        //album = "test";
        //title = "test";
    }


    public void play() {
        mp.play();
    }

    public void pause() {
        mp.pause();
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public File getSong(){
        return song;
    }
}