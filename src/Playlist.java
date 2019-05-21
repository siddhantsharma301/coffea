import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Playlist
{
    private List<Song> playlist;

    /**
     * Default constructor
     */
    public Playlist()
    {
        playlist = new ArrayList<Song>();
    }

    /**
     * Load Song objects from a text file
     * @param songs text file with song paths to be read
     */
    public void loadSongs(File songs) throws IOException
    {
        FileInputStream files = new FileInputStream(songs);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(files));
        String ln = null;
        while ((ln =bReader.readLine()) != null) {
            playlist.add(new Song(line));
        }
        bReader.close();
    }

    /**
     * Returns Song object at given index
     *
     * @param index find song at given index
     * @return Song object at index
     */
    public Song getSong(int index)
    { return playlist.get(index); }

    /**
     * Returns size of playlist
     * @return returns size of playlist
     */
    public int getSize()
    {
        return playlist.size();
    }
}
