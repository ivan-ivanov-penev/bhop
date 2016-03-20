package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.gameobjects.timecounter.GameEndWatcher.*;
import static com.bhop.game.util.ImageUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.states.Play;
import com.bhop.game.util.InputUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class AgainButton implements GameObject, Singleton
{
	
	private final Image image;
	
	private float x;
	
	private float y;
	
	private boolean playerHasPressedButton;
	
	private AgainButton() throws SlickException
	{
		image = createImage("again_button/again_button.png");
		
		x = (WINDOW_WIDTH - image.getWidth()) * 0.5f;
		y = WINDOW_HEIGHT * 0.5f;
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		if (isGameEnd() && mouseIsOverImage(input, image, x, y) && InputUtils.isLeftMouseButtonPressed())
		{
			playerHasPressedButton = true;
		}
		
		attemtPlayButtonAnimation();
	}

	private void attemtPlayButtonAnimation()
	{
		if (playerHasPressedButton)
		{
			x += 1;
			y += 1;
			
			alertPlayerWantsRestartIfAnimationFinished();
		}
	}

	private void alertPlayerWantsRestartIfAnimationFinished()
	{
		if (y == WINDOW_HEIGHT * 0.5f + 2)
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
