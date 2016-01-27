package com.bhop.game.objects.log;

import org.newdawn.slick.SlickException;

import com.bhop.game.objects.BasicGameObject;
import com.bhop.game.util.GameUtils;

public class Log extends BasicGameObject
{

	public Log() throws SlickException
	{
		super("res/obsticales/log.png");
		
		x = GameUtils.WINDOW_WIDTH;
		y = GameUtils.WINDOW_HEIGHT - 240;
	}

}
