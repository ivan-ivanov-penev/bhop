package com.bhop.game.objects.bunny;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameUtils;

class BunnyAnimation
{

	private Animation animation;

	private Animation animationRunning;

	private Animation animationJumping;

	public BunnyAnimation() throws SlickException
	{
		animationRunning = new Animation(GameUtils.createImageArrayFromDirectory("res/bunny/running"), (int) (GameUtils.FPS * 1.25));
		animationJumping = new Animation(GameUtils.createImageArrayFromDirectory("res/bunny/jumping"), (int) (GameUtils.FPS * 1.25));
		animation = animationRunning;
	}

	void setRightAnimation(boolean isOnTopOfAnObject)
	{
		if (isOnTopOfAnObject)
		{
			animation = animationRunning;
		}
		else
		{
			animation = animationJumping;
		}
	}

	void draw(float x, float y, float height, float width)
	{
		animation.draw(x, y, width, height);
	}

}
