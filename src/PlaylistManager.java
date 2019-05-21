import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class PlaylistManager
{
    private List<Playlist> playlists;

    /**
     * Default constructor
     */
    public PlaylistManager()
    {
        playlists = new ArrayList<>();
    }

    /**
     * Passes parameters to a playlist within playlists
     * @param File text file with songs to be read
     * @param index index of playlist
     */
    public void loadSongs (File file, int index)
    {
        playlists.get(index).loadSongs(file);
    }

    /**
     * Calls Playlist and Song methods to return File object of
     * song index
     * @param currentPlaylist index of playlist to be loaded
     * @param currentSong index of song to be loaded
     */
    public File getSong (int currentPlaylist, int currentSong)
    { return playlists.get(currentPlaylist).getSong(currentSong).getSong(); }

    public void add (Playlist play)
    { playlists.add(play); }

    public Playlist getPlaylist (int index)
    { return playlists.get(index); }

    public int getNumberPlaylists()
    { return playlists.size(); }
}