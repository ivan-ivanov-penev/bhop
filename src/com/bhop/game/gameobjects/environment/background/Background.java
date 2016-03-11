package com.bhop.game.gameobjects.environment.background;

import static com.bhop.game.util.GameUtils.SPRITE_DIR;

import org.newdawn.slick.SlickException;

import com.bhop.game.util.generator.GeneratedObject;

public class Background extends GeneratedObject
{
	
	Background(float x) throws SlickException
	{
		super(x, SPRITE_DIR + "backgrounds/landscapes/");
	}
	
	@Override
	protected void update(float cameraSpeed)
	{
		x -= cameraSpeed / 15;
	}
	
}
