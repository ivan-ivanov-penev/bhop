package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.bunny.BunnyPhysics.BunnyJump;
import com.bhop.game.objects.bunny.CameraMovement.RunSpeedBoost;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonManager;

public class Bunny implements GameObject, Singleton
{
	
	private float x;

	private float y;

	private boolean hasToJump;
	
	private boolean isHit;

	private final CameraMovement movement;

	private final BunnyPhysics physics;

	private final BunnyJump jump;

	private final BunnyAnimation animation;

	private final CollisionChecker collisionChecker;

	private Bunny() throws SlickException
	{
		movement = SingletonManager.getSingleton(CameraMovement.class);
		collisionChecker = new CollisionChecker();
		physics = new BunnyPhysics();
		animation = new BunnyAnimation();
		jump = new BunnyJump();
		x = WINDOW_WIDTH / 6;
		y = WINDOW_HEIGHT - 215;
	}

	@Override
	public void render()
	{
		animation.draw(x, y);
	}

	private void updateHeightPosition()
	{
		y += physics.getGravityForce();

		if (y > WINDOW_HEIGHT - 215)
		{
			y = WINDOW_HEIGHT - 215;
		}
	}

	private boolean isOnTopOfAnObject()
	{
		return y == WINDOW_HEIGHT - 215;
	}

	@Override
	public void update(Input input) throws SlickException
	{
		collisionCheck();
		
		updateMovement(input.isMousePressed(0) || input.isKeyPressed(Input.KEY_SPACE));
		
		animation.update(physics.getGravityForce(), y, movement.getSpeedFactor(), isOnTopOfAnObject());
	}

	private void updateMovement(boolean buttonIsPressed) throws SlickException
    {
	    if (isOnTopOfAnObject())
		{
			BunnyIsHitEventWatcher.alertWatchersBunnyHasRecovered();
			
			attemptRun(buttonIsPressed);
		}
		else
		{
			fall();
		}
    }

	private void attemptRun(boolean buttonIsPressed) throws SlickException
	{
		RunSpeedBoost runSpeedBoost = animation.getSpeedBoost() == null ? RunSpeedBoost.MIN : animation.getSpeedBoost();

		if (hasToJump || buttonIsPressed /* XXX && runSpeedBoost != null */)
		{
			hasToJump = true;

			attemptJump(runSpeedBoost);
		}
		else
		{
			run();
		}
	}

	private void attemptJump(RunSpeedBoost runSpeedBoost) throws SlickException
	{
		if (animation.isNotInTheAir())
		{
			movement.increaseSpeedFactor(runSpeedBoost);
			
			jump();
			
			jump.increaseNextJumpHeight();
		}
	}

	private void jump()
    {
	    hasToJump = false;
	    
	    physics.setGravityToJumping(jump.getJumpHeight());
	    
	    updateHeightPosition();
    }

	private void run()
	{
		physics.resetGravityFallingBaseForce();
		
		jump.decreaseNextJumpHeight();

		movement.decreaseSpeedFactor();
	}

	private void fall()
	{
		physics.increaseGravityPullingForce();

		updateHeightPosition();
	}

	private void collisionCheck()
	{
		if (collisionChecker.collisionCheck(x, y, animation))
		{
			collide();
		}
		else
		{
			isHit = false;
		}
	}

	private void collide()
	{
		if (!isHit)
	    {
			sleep();
			
	    	isHit = true;
	    	
			BunnyIsHitEventWatcher.alertWatchersBunnyIsHit();
	    	
	    	jump();
	    }
	}

	void sleep()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
