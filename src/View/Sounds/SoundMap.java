package View.Sounds;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundMap {

	private static volatile SoundMap instance;
	private static Object mutex = new Object();

	private Map<String, MediaPlayer> SoundMap;

	private SoundMap() {
		SoundMap = new HashMap<>();
		SoundSystem();
	}

	public static SoundMap getInstance() {
		SoundMap result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new SoundMap();
			}
		}
		return result;
	}
//BeginTheGame.mp4
	private void SoundSystem() {
		SoundMap.put("StartMenu", new MediaPlayer(new Media(Paths.get("BeginTheGame.mp4").toUri().toString())));
		SoundMap.put("StartGame", new MediaPlayer(new Media(Paths.get("StartPlay.mp3").toUri().toString())));
		SoundMap.put("Explosion", new MediaPlayer(new Media(Paths.get("explosion.mp3").toUri().toString())));
		SoundMap.put("ShotBullets", new MediaPlayer(new Media(Paths.get("Fire.mp3").toUri().toString())));
		SoundMap.put("Lazer", new MediaPlayer(new Media(Paths.get("Lazer.mp4").toUri().toString())));
		SoundMap.put("Monester", new MediaPlayer(new Media(Paths.get("Monestor.mp3").toUri().toString())));
		SoundMap.put("LoseTheGame", new MediaPlayer(new Media(Paths.get("SadTrombone.mp4").toUri().toString())));
		SoundMap.put("Win", new MediaPlayer(new Media(Paths.get("ClappingPeople.mp4").toUri().toString())));
		SoundMap.put("Trap", new MediaPlayer(new Media(Paths.get("Trap.mp3").toUri().toString())));
		SoundMap.put("HitWall", new MediaPlayer(new Media(Paths.get("WallHit.mp3").toUri().toString())));
		SoundMap.put("GiftSound", new MediaPlayer(new Media(Paths.get("GiftSound.mp3").toUri().toString())));
		SoundMap.put("CoinEffect", new MediaPlayer(new Media(Paths.get("CollcetCoin.mp3").toUri().toString())));
	}
	
	public MediaPlayer getMediaPlayer(String key) {
		return SoundMap.get(key);
	}

}
