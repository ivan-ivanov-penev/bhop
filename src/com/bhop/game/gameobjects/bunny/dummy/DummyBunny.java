package com.bhop.game.gameobjects.bunny.dummy;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.coloroptions.ColorOption;

public class DummyBunny implements GameObject
{
	
	private float x;

	private float y;

	private final DummyBunnyAnimation animation;
	
	DummyBunny(ColorOption colorOption) throws SlickException
	{
		animation = new DummyBunnyAnimation(colorOption);
		x = (colorOption.getImageWidth() - animation.getImageWidth()) / 2 + colorOption.getX() + 5;
		y = colorOption.getImageHeight() / 4 + colorOption.getY();
	}

	@Override
	public void render()
	{
		animation.draw(x, y);
	}

	@Override
	public void update(Input input) throws SlickException
	{
		animation.attempChangeAnimation();
	}

}
