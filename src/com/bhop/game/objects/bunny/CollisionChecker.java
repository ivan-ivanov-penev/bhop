package com.bhop.game.objects.bunny;

import static com.bhop.game.objects.bunny.animation.BunnyAnimation.IMAGE_HEIGHT;
import static com.bhop.game.objects.bunny.animation.BunnyAnimation.IMAGE_WIDTH;

import org.newdawn.slick.SlickException;

import com.bhop.game.objects.PixelLocation;
import com.bhop.game.objects.bunny.animation.BunnyAnimation;
import com.bhop.game.objects.carrot.CarrotManager;
import com.bhop.game.objects.log.LogGenerator;
import com.bhop.game.util.singleton.SingletonManager;

public class CollisionChecker
{
	
	private final LogGenerator logGenerator;
	
	private final CarrotManager carrotGenerator;
	
	CollisionChecker()
	{
		logGenerator = SingletonManager.getSingleton(LogGenerator.class);
		carrotGenerator = SingletonManager.getSingleton(CarrotManager.class);
	}

	public boolean checkForCollision(float bunnyX, float bunnyY, BunnyAnimation animation) throws SlickException
	{
		checkForCarrotCollision(bunnyX, bunnyY, animation);
		
		return checkForLogCollision(bunnyX, bunnyY, animation);
	}

	private void checkForCarrotCollision(float bunnyX, float bunnyY, BunnyAnimation animation) throws SlickException
    {
	    if (checkForCollision(carrotGenerator.getCarrot(), bunnyX, bunnyY, animation))
		{
			carrotGenerator.alertBunnyTookCarrot();
		}
    }

	private boolean checkForLogCollision(float bunnyX, float bunnyY, BunnyAnimation animation)
    {
	    for (Collidable collidable : logGenerator.getAllLogs())
		{
			if (checkForCollision(collidable, bunnyX, bunnyY, animation))
			{
				return true;
			}
		}
		
		return false;
    }

	private boolean checkForCollision(Collidable collidable, float bunnyX, float bunnyY, BunnyAnimation animation)
	{
		if (checkForImageCollision(collidable, bunnyX, bunnyY, animation))
		{
			return checkForPixelCollision(collidable, bunnyX, bunnyY, animation);
		}
		
		return false;
	}

	private boolean checkForImageCollision(Collidable collidable, float bunnyX, float bunnyY, BunnyAnimation animation)
	{
		return bunnyX + IMAGE_WIDTH >= collidable.getX() && bunnyX <= collidable.getX() + collidable.getImageWidth() && bunnyY <= collidable.getY() + collidable.getImageHeight() && bunnyY + IMAGE_HEIGHT >= collidable.getY();
	}
	
	private boolean checkForPixelCollision(Collidable collidable, float bunnyX, float bunnyY, BunnyAnimation animation)
	{
		for (PixelLocation location : animation.getCurrentFramePixelLocations())
		{
			int x = (int) (bunnyX + location.getX() - collidable.getX());
			int y = (int) (bunnyY + location.getY() - collidable.getY());
			
			if (collidable.getImagePixelLocations().contains(new PixelLocation(x, y)))
			{
				return true;
			}
		}
		
		return false;
	}

}
