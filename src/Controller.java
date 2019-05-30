import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
//import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable
{

    boolean firstTime = true;
    @FXML
    private ToggleButton playPause;
    @FXML
    private MediaView player;
    @FXML
    private Button prevButton, nextButton;
//    @FXML
//    private Slider volume;
    @FXML
    private Label songName;
    @FXML
    private ImageView albumArt;
    @FXML
    private Button browse;
    private ArrayList<Media> mediaFiles = new ArrayList();
    private int counter = -1;

    @Override
    /**
     * Handles initialization
     */
    public void initialize(URL url, ResourceBundle rb)
    {
        Platform.runLater(() -> {
            playPause.requestFocus();
        });

    }

    @FXML
    /**
     * Handles when the play / pause button is clicked
     */
    private void playPauseClicked(ActionEvent event)
    {
        if (firstTime) {
            File file = null;
            Stage stage = (Stage) playPause.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter("Audio files", "*.mp3", "*.wav");
            fileChooser.getExtensionFilters().add(fileExtension);
            file = fileChooser.showOpenDialog(stage);
            System.out.println(file);
            if (file != null)
            {
                Media media;
                media = new Media(file.toURI().toASCIIString());
                mediaFiles.add(media);
                MediaPlayer mediaplayer = new MediaPlayer(media);
                prevButton.setDisable(false);
                nextButton.setDisable(false);
                ++counter;
                player = new MediaView(mediaplayer);
                mediaplayer.setAutoPlay(true);
                player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) -> {
                    if (change.wasAdded())
                    {
                        if (change.getKey().equals("image"))
                        {
                            Image art = (Image) change.getValueAdded();
                            double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                            albumArt.setX(200);
                            albumArt.setImage(art);
                            albumArt.setX(200);
                        }
                        if(change.getKey().equals("title")){
                            String name = (String) change.getValueAdded();
                            songName.setText(name);
                        }
                    }
                });
//                volume.valueProperty().addListener((Observable observable) ->
//                {
//                    if (volume.isValueChanging())
//                    {
//                        player.getMediaPlayer().setVolume(volume.getValue() / 100);
//                    }
//                });
                player.getMediaPlayer().setOnEndOfMedia(() ->
                {
                    playPause.setSelected(false);
                });
                firstTime = false;

            } else {
                playPause.setSelected(false);
            }
        } else {
            if (playPause.isSelected())
            {
                player.getMediaPlayer().play();
            } else {
                player.getMediaPlayer().pause();
            }
        }
    }

    @FXML
    /**
     * Handles the choosing of a song file
     */
    private void browseClicked(ActionEvent event)
    {
        firstTime = false;
        Stage stage = (Stage) playPause.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter("Audio files", "*.mp3", "*.wav");
        fileChooser.getExtensionFilters().add(fileExtension);
        File file = fileChooser.showOpenDialog(stage);
        Media media;
        media = new Media(file.toURI().toASCIIString());
        mediaFiles.add(media);
        MediaPlayer mediaplayer = new MediaPlayer(media);
        try
        {
            player.getMediaPlayer().dispose();
        } catch (Exception e)
        {

        }
        prevButton.setDisable(false);
        nextButton.setDisable(false);
        playPause.setSelected(true);
        ++counter;
        mediaplayer.setAutoPlay(true);
        player = new MediaView(mediaplayer);
        player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) ->
        {
            if (change.wasAdded())
            {
                if (change.getKey().equals("image"))
                {
                    Image art = (Image) change.getValueAdded();
                    double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                    albumArt.setX(50);
                    albumArt.setImage(art);
                    albumArt.setX(50);
                }
                if(change.getKey().equals("title")){
                    String name = (String) change.getValueAdded();
                    songName.setText(name);
                }
            }
        });
//        volume.valueProperty().addListener((Observable observable) ->
//        {
//            if (volume.isValueChanging())
//            {
//                player.getMediaPlayer().setVolume(volume.getValue() / 100);
//            }
//        });
        player.getMediaPlayer().setOnEndOfMedia(() ->
        {
            playPause.setSelected(false);
        });
    }

    @FXML
    /**
     * Handles clicking of previous button
     */
    private void prevClicked(ActionEvent event)
    {
        if (counter == 0)
        {
            player.getMediaPlayer().seek(Duration.ZERO);
        } else {
            player.getMediaPlayer().dispose();
            albumArt.setImage(null);
            player = new MediaView(new MediaPlayer(mediaFiles.get(--counter)));
            player.getMediaPlayer().play();
            playPause.setSelected(true);
            player.getMediaPlayer().setOnEndOfMedia(() -> {
                playPause.setSelected(false);
            });
            player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) ->
            {
            if (change.wasAdded())
            {
                if (change.getKey().equals("image"))
                {
                    Image art = (Image) change.getValueAdded();
                    double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                    albumArt.setX(50);
                    albumArt.setImage(art);
                    albumArt.setX(50);
                }
                if(change.getKey().equals("title")){
                    String name = (String) change.getValueAdded();
                    songName.setText(name);
                }
            }
        });
        }
    }

    @FXML
    /**
     * Handles clicking of next button
     */
    private void nextClicked(ActionEvent event)
    {
        if (counter + 1 == mediaFiles.size())
        {
            player.getMediaPlayer().stop();
            playPause.setSelected(false);
        } else {
            player.getMediaPlayer().dispose();
            albumArt.setImage(null);
            player = new MediaView(new MediaPlayer(mediaFiles.get(++counter)));
            playPause.setSelected(true);
            player.getMediaPlayer().play();
            player.getMediaPlayer().setOnEndOfMedia(() -> {
                playPause.setSelected(false);
            });
            player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) ->
            {
            if (change.wasAdded())
            {
                if (change.getKey().equals("image"))
                {
                    Image art = (Image) change.getValueAdded();
                    double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                    albumArt.setX(50);
                    albumArt.setImage(art);
                    albumArt.setX(50);
                }
                if(change.getKey().equals("title")){
                    String name = (String) change.getValueAdded();
                    songName.setText(name);
                }
            }
        });
        }
    }
}
