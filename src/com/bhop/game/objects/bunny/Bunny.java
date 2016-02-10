package com.bhop.game.objects.bunny;

import static com.bhop.game.util.GameUtils.WINDOW_HEIGHT;
import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;
import static com.bhop.game.objects.bunny.BunnyAnimation.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.PixelLocation;
import com.bhop.game.objects.bunny.BunnyPhysics.BunnyJump;
import com.bhop.game.objects.bunny.CameraMovement.RunSpeedBoost;
import com.bhop.game.objects.log.Log;
import com.bhop.game.objects.log.LogGenerator;

// TODO: more refactoring after game is finished
public class Bunny implements GameObject
{

	private float x;

	private float y;

	private final CameraMovement movement;

	private final BunnyPhysics physics;

	private final BunnyJump jump;

	private final BunnyAnimation animation;

	private RunSpeedBoost runSpeedBoost;
	
	private CollisionChecker collisionChecker;

	private boolean hasToJump;
	
	private boolean isHit;

	public Bunny() throws SlickException
	{
		movement = CameraMovement.getInstance();
		physics = new BunnyPhysics();
		animation = new BunnyAnimation();
		jump = new BunnyJump();
		collisionChecker = new CollisionChecker();
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
		animation.draw(x, y);
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
		collisionCheck();

		if (hasToJump)
		{
			attemptJump();
		}
		else if (isOnTopOfAnObject())
		{
			animation.bunnyHasRecovered();
			movement.bunnyRecoveredFromHit();
			
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
			
			movement.increaseSpeedFactor(runSpeedBoost);
			
			jump();
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

	private void collisionCheck()
	{
//		for (Log log : LogGenerator.getInstance().getAllLogs())
//		{
//			checkForCollision(log);
//		}
		
		if (collisionChecker.collisionCheck(x, y, animation))
		{
			collide();
		}
		else
		{
			isHit = false;
		}
	}

	private void checkForCollision(Log log)
	{
		if (checkForImageCollision(log))
		{
			checkForPixelCollision(log);
		}
	}

	private boolean checkForImageCollision(Log log)
	{
		return x + IMAGE_WIDTH >= log.getX() && x <= log.getX() + Log.IMAGE_WIDTH && y <= log.getY() + Log.IMAGE_HEIGHT && y + IMAGE_HEIGHT >= log.getY();
	}
	
	private void checkForPixelCollision(Log log)
	{
		for (PixelLocation location : animation.getCurrentFramePixelLocations())
		{
			int x = (int) (this.x + location.getX() - log.getX());
			int y = (int) (this.y + location.getY() - log.getY());
			
			if (log.getImagePixelLocations().contains(new PixelLocation(x + 1, y)))
			{
				collide();
				
				return;
			}
		}
		
		isHit = false;
	}

	private void collide()
	{
	    animation.alertBunnyIsHit();
		movement.alertBunnyIsHit();
		jump.alertBunnyIsHit();

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
			System.out.println(collisionChecker);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
