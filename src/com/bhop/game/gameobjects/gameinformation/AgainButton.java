package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.gameobjects.timecounter.GameEndWatcher.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.states.Play;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class AgainButton implements GameObject, Singleton
{
	
	private final Image image;
	
	private final float x;
	
	private final float y;
	
	private AgainButton() throws SlickException
	{
		image = new Image(SPRITE_DIR + "again_button/again_button.png");
		
		x = (WINDOW_WIDTH - image.getWidth()) * 0.5f;
		y = WINDOW_HEIGHT * 0.5f;
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		if (isGameEnd() && mouseIsOverImage(input, image, x, y) && input.isMousePressed(0))
		{
			Play.alertPlayerWantsToRestart();
		}
	}
	
	@Override
	public void render() throws SlickException
	{
		if (isGameEnd())
		{
			image.draw(x, y);
		}
	}

}
