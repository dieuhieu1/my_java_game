package object;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class obj_duck extends MainObject {
	public obj_duck () {
		name = "Duck";
		InputStream is = getClass().getResourceAsStream("/player/player_sprite.png");
		try {
			image = ImageIO.read(is);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
