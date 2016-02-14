package com.bhop.game.environment;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.singleton.Singleton;

public class Sky implements Singleton, GameObject
{
	
	private final Image image;
	
	private Sky() throws SlickException
	{
		image = GameUtils.getImageAccordingToTimePeriod("res/backgrounds_new/skies/");
	}

	@Override
	public void update(Input input) throws SlickException {}

	@Override
	public void render() throws SlickException
	{
		image.draw();
	}

}
