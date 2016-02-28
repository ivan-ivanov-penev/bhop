package com.bhop.game.gameobjects.environment;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

import static com.bhop.game.util.GameUtils.*;

@SingletonClass
public class LightObject implements Singleton, GameObject
{
	
	private final Image image;
	
	public LightObject() throws SlickException
	{
		image = GameUtils.getImageAccordingToTimePeriod(SPRITE_DIR + "backgrounds/light_objects/");
	}

	@Override
	public void update(Input input) throws SlickException {}

	@Override
	public void render() throws SlickException
	{
		image.draw();
	}

}
