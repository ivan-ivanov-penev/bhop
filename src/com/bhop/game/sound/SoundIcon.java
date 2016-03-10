package com.bhop.game.sound;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.sound.SoundWatcher.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.singleton.Singleton;

public class SoundIcon implements Singleton, GameObject
{
	
	private final Image soundIcon;
	
	private final Image soundIconUnchecked;
	
	private Image renderImage;
	
	private float x;
	
	private float y;
	
	private SoundIcon() throws SlickException
	{
		soundIcon = new Image(SPRITE_DIR + "sound_icon/music_icon.png");
		soundIconUnchecked = new Image(SPRITE_DIR + "sound_icon/music_icon_unwanted.png");
		renderImage = isSoundEnabled() ? soundIcon : soundIconUnchecked;
		x = 8;
		y = WINDOW_HEIGHT - renderImage.getHeight() - 7;
	}

	@Override
	public void update(Input input) throws SlickException
	{
		if (mouseIsOverImage(input, renderImage, x, y) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			alertPlayerHasClickedIcon();
			
			renderImage = renderImage == soundIcon ? soundIconUnchecked : soundIcon;
			
			x += renderImage == soundIcon ? -2 : +2;
			y += renderImage == soundIcon ? -2 : +2;
		}
	}

	@Override
	public void render() throws SlickException
	{
		renderImage.draw(x, y);
	}
	
}
