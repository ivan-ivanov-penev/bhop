package com.bhop.game.gameobjects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.bunny.BunnyPhysics.BunnyJump;
import com.bhop.game.gameobjects.bunny.CameraMovement.RunSpeedBoost;
import com.bhop.game.gameobjects.bunny.animation.BunnyAnimation;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
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
		
		updateMovement((input.isMousePressed(0) || input.isKeyPressed(Input.KEY_SPACE)));
		
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

		if (hasToJump || buttonIsPressed /* && runSpeedBoost != null */)
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

	private void collisionCheck() throws SlickException
	{
		if (collisionChecker.checkForCollision(x, y, animation))
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
	    	isHit = true;
	    	
			BunnyIsHitEventWatcher.alertWatchersBunnyIsHit();
	    	
	    	jump();
	    }
	}

}
