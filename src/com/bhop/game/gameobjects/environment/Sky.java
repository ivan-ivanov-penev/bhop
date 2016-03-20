package com.bhop.game.gameobjects.environment;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.ImageUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class Sky implements Singleton, GameObject
{
	
	private final Image image;
	
	private Sky() throws SlickException
	{
		image = ImageUtils.getImageAccordingToTimePeriod("backgrounds/skies/");
	}

	@Override
	public void update(Input input) throws SlickException {}

	@Override
	public void render() throws SlickException
	{
		image.draw();
	}

}
