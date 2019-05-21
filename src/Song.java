import java.io.File;
public class Song
{
    private File song;
    /**
     * Constructor
     * Creates a File object and puts song into it
     * @param path of file to be loaded
     */
    public Song (String filePath)
    {
        song = new File(Song.class.getResource(filePath).getFile());
    }

    /**
     * Returns File path
     * @return song's file path
     */
    public File getSong()
    {
        return song;
    }
}
