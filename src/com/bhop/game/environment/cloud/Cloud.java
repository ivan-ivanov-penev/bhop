package com.bhop.game.environment.cloud;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.generator.GeneratedObject;

class Cloud extends GeneratedObject
{
	
	private final float y;

	Cloud(float x) throws SlickException
	{
		super(x, "res/backgrounds/clouds/");
		
		y = (float) (Math.random() * 120);
	}
	
	@Override
	protected void update(float cameraSpeed)
	{
		cameraSpeed *= cameraSpeed > 0 ? -1 : 1;
		
		super.update(cameraSpeed * 2);
	}
	
	@Override
	protected void render() throws SlickException
	{
		image.draw(x, y, new Color(1, 1, 1, 0.6f));
	}

}
