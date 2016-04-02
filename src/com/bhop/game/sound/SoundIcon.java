package com.bhop.game.sound;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.sound.SoundWatcher.*;
import static com.bhop.game.util.ImageUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.InputUtils;
import com.bhop.game.util.singleton.Singleton;

public class SoundIcon implements Singleton, GameObject
{
	
	private final Image soundIcon;
	
	private final Image soundIconUnchecked;
	
	private final SoundPlayer soundPlayer;
	
	private Image renderImage;
	
	private float x;
	
	private float y;
	
	private static boolean clickedOnIcon;
	
	private SoundIcon() throws SlickException
	{
		soundIcon = createImage("sound_icon/music_icon.png");
		soundIconUnchecked = createImage("sound_icon/music_icon_unwanted.png");
		renderImage = isSoundEnabled() ? soundIcon : soundIconUnchecked;
		soundPlayer = new SoundPlayer("res/sound/button.wav");
		x = 8;
		y = WINDOW_HEIGHT - renderImage.getHeight() - 7;
	}

	@Override
	public void update(Input input) throws SlickException
	{
		if (mouseIsOverImage(input, renderImage, x, y) && InputUtils.isLeftMouseButtonPressed())
		{
			soundPlayer.playSoundOnce();
			
			alertPlayerHasClickedIcon();
			
			renderImage = renderImage == soundIcon ? soundIconUnchecked : soundIcon;
			
			x += renderImage == soundIcon ? -2 : +2;
			y += renderImage == soundIcon ? -2 : +2;
			
			clickedOnIcon = true;
		}
		else
		{
			clickedOnIcon = false;
			
			soundPlayer.alertSoundHasToBePlayed();
		}
	}

	@Override
	public void render() throws SlickException
	{
		renderImage.draw(x, y);
	}

	public static boolean isClickedOnIcon()
	{
		return clickedOnIcon;
	}
	
}
