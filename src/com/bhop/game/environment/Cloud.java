package com.bhop.game.environment;

import org.newdawn.slick.SlickException;

import com.bhop.game.util.generator.GeneratedObject;

class Cloud extends GeneratedObject
{

	Cloud(float x) throws SlickException
	{
		super(x, "res/backgrounds_new/clouds/");
	}
	
	@Override
	protected void update(float cameraSpeed)
	{
		cameraSpeed *= cameraSpeed > 0 ? -1 : 1;
		
		super.update(cameraSpeed * 4);
	}

}
