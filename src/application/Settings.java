package application;

import java.io.File;
import java.util.prefs.Preferences;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Die Klasse Settings verwaltet die Einstellungen der Anwendung. Sie bietet
 * Funktionalität zum Speichern und Laden von Einstellungen,
 * <p>
 * sowie zum Zugriff auf die aktuellen Einstellungen.
 *
 * @author Kristian Kovács
 */
public class Settings {
    private static final Settings INSTANCE = new Settings();

    private Preferences preferences;

    private boolean musicOn;
    private boolean soundOn;
    private String difficulty;
    private String theme;
    private String audio = "src/application/img/SnakeGame.mp3";
    private Media media = new Media(new File(audio).toURI().toString());
    private	MediaPlayer mediaPlayer = new MediaPlayer(media);

    /**
     * Erstellt eine neue Instanz von Settings.
     */
    private Settings() {
        preferences = Preferences.userNodeForPackage(Settings.class);
        load();
    }

    /**
     * Gibt die einzige Instanz von Settings zurück.
     *
     * @return Instanz von Settings
     */
    public static Settings getInstance() {
        return INSTANCE;
    }

    /**
     * Gibt zurück, ob die Musik eingeschaltet ist.
     *
     * @return true, wenn Musik eingeschaltet ist, andernfalls false
     */
    public boolean isMusicOn() {
        return musicOn;
    }

    /**
     * Schaltet die Musik ein oder aus.
     *
     * @param musicOn true, um die Musik einzuschalten, false, um sie auszuschalten
     */
    public void setMusicOn(boolean music) {
        this.musicOn = music; 
         if(music==true) {
         	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
         	mediaPlayer.play();
         }
         else
         	mediaPlayer.pause();
     }
    /**
     * Gibt zurück, ob der Sound eingeschaltet ist.
     *
     * @return true, wenn Sound eingeschaltet ist, andernfalls false
     */
    public boolean isSoundOn() {
        return soundOn;
    }

    /**
     * Schaltet den Sound ein oder aus.
     *
     * @param soundOn true, um den Sound einzuschalten, false, um ihn auszuschalten
     */
    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    /**
     * Speichert die aktuellen Einstellungen.
     */
    public void save() {
        preferences.putBoolean("musicOn", musicOn);
        preferences.putBoolean("soundOn", soundOn);
        preferences.put("difficulty", difficulty);
        preferences.put("theme", theme);
    }

    /**
     * Lädt die gespeicherten Einstellungen. Wenn keine gespeicherten Einstellungen
     * vorhanden sind, werden die Standardwerte verwendet (Musik und Sound
     * eingeschaltet).
     */
    public void load() {
        musicOn = preferences.getBoolean("musicOn", true);
        this.setMusicOn(musicOn);
        soundOn = preferences.getBoolean("soundOn", true);
        difficulty = preferences.get("difficulty", "easy");
        theme = preferences.get("theme", "Hell");
    }
    

    /**
     * Legt den Schwierigkeitsgrad für das Spiel fest.
     *
     * @param diff Der gewählte Schwierigkeitsgrad (einfach, mittel, schwer)
     */
    public void setDifficulty(String diff) {
        this.difficulty = diff;
    }

    public String getDifficulty() {
    	return difficulty;
    }
    
    /**
     * Legt das Design für das Spiel fest.
     *
     * @param theme Das gewählte Design (hell, dunkel, Halloween)
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public String getTheme() {
    	return theme;
    }
}
