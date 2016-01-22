package com.bhop.game.objects.bunny;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameUtils;

class BunnyAnimation
{
	private static final int DURATION = (int) (GameUtils.FPS * 1.25);

	private Animation animation;

	private Animation animationRunning;

//	private Animation animationJumping;

	public BunnyAnimation() throws SlickException
	{
		animationRunning = new Animation(GameUtils.createImageArrayFromDirectory("res/bunny/test"), DURATION);
//		animationJumping = new Animation(GameUtils.createImageArrayFromDirectory("res/bunny/jumping"), DURATION);
		animation = animationRunning;
	}

	void setAnimationToRunning()
	{
		animation = animationRunning;
	}

	void draw(float x, float y, float height, float width)
	{
		animation.draw(x, y, width, height);
	}

	void setJumpingAnimation(float gravityForce) throws SlickException
	{
		int frameNumber = 0;

		if (gravityForce < -8)
		{
			frameNumber = 0;
		}
		else if (gravityForce < 0)
		{
			frameNumber = 1;
		}
		else if (gravityForce < 2)
		{
			frameNumber = 2;
		}
		else if (gravityForce < 8)
		{
			frameNumber = 3;
		}
		else
		{
			frameNumber = 4;
		}

		animation = new Animation(new Image[] { new Image("res/bunny/jumping/sBunnyBlue_Jump_" + frameNumber + ".png") }, DURATION);
	}

}
