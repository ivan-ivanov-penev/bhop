package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.Ground;
import com.bhop.game.objects.bunny.BunnyPhysics.BunnyJump;

// TODO: more refactoring after game is finished
public class Bunny
{

	private float x;

	private float y;

	private final Ground ground;

	private final BunnyPhysics physics;

	private final BunnyJump jump;

	private final BunnyAnimation animation;

	private boolean hasToJump;

	public Bunny(Ground ground) throws SlickException
	{
		this.ground = ground;
		physics = new BunnyPhysics();
		animation = new BunnyAnimation();
		jump = new BunnyJump();
		x = WINDOW_WIDTH / 6;
		y = WINDOW_HEIGHT / 3;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public void draw()
	{
		animation.draw(x, y, 96, 96);
	}

	private void updateHeightPosition()
	{
		y += physics.getGravityForce();
	}

	// XXX consider inlining this method
	private void normalizeHeight()
	{
		if (y > WINDOW_HEIGHT - 220)
		{
			y = WINDOW_HEIGHT - 220;
		}
	}

	private boolean isOnTopOfAnObject()
	{
		normalizeHeight();

		return y == WINDOW_HEIGHT - 220;
	}

	public void move(Input input) throws SlickException
	{
		if (hasToJump)
		{
			attemptJump();
		}
		else if (isOnTopOfAnObject())
		{
			attemptRun(input.isMousePressed(0) || input.isKeyPressed(Input.KEY_SPACE));
		}
		else
		{
			fall();
		}

		animation.update(physics.getGravityForce(), y);
	}

	private void attemptJump() throws SlickException
	{
		if (animation.isNotInTheAir())
		{
			jump.increaseNextJumpHeight();
			
			physics.setGravityToJumping(jump.getJumpHeight());

			updateHeightPosition();
			increaseSpeed();

			hasToJump = false;
		}
	}

	private void attemptRun(boolean buttonIsPressed) throws SlickException
	{
		if (buttonIsPressed)
		{
			hasToJump = true;

			attemptJump();
		}
		else
		{
			run();
		}
	}

	private void run()
	{
		// XXX this might cause a bug in the future - consider moving it ot top of attemptRun
		physics.resetGravityFallingBaseForce();
		
		jump.decreaseNextJumpHeight();

		decreaseSpeed();
	}

	private void fall()
	{
		physics.increaseGravityPullingForce();

		updateHeightPosition();
	}

	private void decreaseSpeed()
	{
		ground.decreaseSpeedFactor();
	}

	private void increaseSpeed()
	{
		ground.increaseSpeedFactor();
	}

}
