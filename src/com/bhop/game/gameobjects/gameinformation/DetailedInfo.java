package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class DetailedInfo extends BasicGameObject implements Singleton
{
	
	private DetailedInfo() throws SlickException
	{
		super(SPRITE_DIR + "game_information/detailed_info.png");
		
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
		}
	}

}
