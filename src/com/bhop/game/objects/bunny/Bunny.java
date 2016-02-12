package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.bunny.BunnyPhysics.BunnyJump;
import com.bhop.game.objects.bunny.CameraMovement.RunSpeedBoost;
import com.bhop.game.util.singleton.SingletonManager;
import com.bhop.game.util.singleton.SingletonManager.Singleton;

// TODO: more refactoring after game is finished
public class Bunny extends Singleton implements GameObject
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

	private RunSpeedBoost runSpeedBoost;

	private Bunny() throws SlickException
	{
		super(Bunny.class);
		movement = CameraMovement.getInstance();
		physics = new BunnyPhysics();
		animation = new BunnyAnimation();
		jump = new BunnyJump();
		collisionChecker = SingletonManager.getSingleton(CollisionChecker.class);
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
			
			physics.resetGravityFallingBaseForce();
			
			attemptRun(buttonIsPressed);
		}
		else
		{
			fall();
		}
    }

	private void attemptJump() throws SlickException
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

	private void attemptRun(boolean buttonIsPressed) throws SlickException
	{
		runSpeedBoost = animation.getSpeedBoost() == null ? RunSpeedBoost.MIN : animation.getSpeedBoost();

		if (hasToJump || buttonIsPressed /* && runSpeedBoost != null */)
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
		BunnyIsHitEventWatcher.alertWatchersBunnyIsHit();

		if (!isHit)
	    {
	    	isHit = true;
	    	
	    	jump();
	    }
	}

	void sleep()
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
