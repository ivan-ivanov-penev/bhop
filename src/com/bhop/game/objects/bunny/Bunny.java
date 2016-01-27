package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.bunny.BunnyPhysics.BunnyJump;
import com.bhop.game.objects.bunny.CameraMovement.RunSpeedBoost;

// TODO: more refactoring after game is finished
public class Bunny implements GameObject
{

	private float x;

	private float y;

	private final CameraMovement movement;

	private final BunnyPhysics physics;

	private final BunnyJump jump;

	private final BunnyAnimation animation;

	private boolean hasToJump;

	private RunSpeedBoost runSpeedBoost;

	public Bunny() throws SlickException
	{
		movement = CameraMovement.getInstance();
		physics = new BunnyPhysics();
		animation = new BunnyAnimation();
		jump = new BunnyJump();
		x = WINDOW_WIDTH / 6;
//		y = WINDOW_HEIGHT / 3;
		y = WINDOW_HEIGHT - 215;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	@Override
	public void render()
	{
		animation.draw(x, y, 96, 96);
	}

	private void updateHeightPosition()
	{
		y += physics.getGravityForce();
	}

	private boolean isOnTopOfAnObject()
	{
		if (y > WINDOW_HEIGHT - 215)
		{
			y = WINDOW_HEIGHT - 215;
		}

		return y == WINDOW_HEIGHT - 215;
	}

	@Override
	public void update(Input input) throws SlickException
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

		animation.update(physics.getGravityForce(), y, movement.getSpeedFactor(), isOnTopOfAnObject());
	}

	private void attemptJump() throws SlickException
	{
		if (animation.isNotInTheAir())
		{
			jump.increaseNextJumpHeight();
			
			physics.setGravityToJumping(jump.getJumpHeight());

			updateHeightPosition();
			
			movement.increaseSpeedFactor(runSpeedBoost);

			hasToJump = false;
		}
	}

	private void attemptRun(boolean buttonIsPressed) throws SlickException
	{
		runSpeedBoost = animation.getSpeedBoost();

		if (runSpeedBoost == null)
		{
			runSpeedBoost = RunSpeedBoost.MIN;
		}

		if (buttonIsPressed /* && runSpeedBoost != null */)
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

		movement.decreaseSpeedFactor();
	}

	private void fall()
	{
		physics.increaseGravityPullingForce();

		updateHeightPosition();
	}

}
