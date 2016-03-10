package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class DetailedInfo extends BasicGameObject implements Singleton
{
	
	private final Animation bunnyJump;
	private final Animation bunnyJump2;
	
	private DetailedInfo() throws SlickException
	{
		super(SPRITE_DIR + "game_information/detailed_info.png");
		
		bunnyJump = new Animation(createImageArrayFromDirectory(SPRITE_DIR + "bunny/bonus/jump"), FPS, true);
		bunnyJump2 = bunnyJump.copy();
		
		x = (WINDOW_WIDTH - image.getWidth()) / 2;
		y = (WINDOW_HEIGHT - image.getHeight()) / 2;
	}
	
	@Override
	public void update(Input input) throws SlickException {}
	
	@Override
	public void render() throws SlickException
	{
		if (InfoIcon.isPlayerIsReadingInfo())
		{
			super.render();
			
			bunnyJump.draw(155, 130);
			
			bunnyJump2.draw(500, 130);
		}
	}

}
