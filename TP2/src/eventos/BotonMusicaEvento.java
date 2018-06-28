package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;

public class BotonMusicaEvento implements EventHandler<ActionEvent> {

	private MediaPlayer reproductor;

	public BotonMusicaEvento(MediaPlayer reproductor) {
		this.reproductor = reproductor;
	}

	@Override
	public void handle(ActionEvent event) {
		reproductor.setMute(!reproductor.isMute());
	}

}
