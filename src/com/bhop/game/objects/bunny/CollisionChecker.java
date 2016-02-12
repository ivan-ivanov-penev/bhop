package com.bhop.game.objects.bunny;

import static com.bhop.game.objects.bunny.BunnyAnimation.IMAGE_HEIGHT;
import static com.bhop.game.objects.bunny.BunnyAnimation.IMAGE_WIDTH;

import com.bhop.game.objects.PixelLocation;
import com.bhop.game.objects.log.Log;
import com.bhop.game.objects.log.LogGenerator;
import com.bhop.game.util.singleton.SingletonManager;
import com.bhop.game.util.singleton.SingletonManager.Singleton;

public class CollisionChecker extends Singleton
{
	
	private final LogGenerator logGenerator;
	
	private CollisionChecker()
	{
		super(CollisionChecker.class);
		
		logGenerator = SingletonManager.getSingleton(LogGenerator.class);
	}

	public boolean collisionCheck(float bunnyX, float bunnyY, BunnyAnimation animation)
	{
		for (Log log : logGenerator.getAllLogs())
		{
			if (checkForCollision(log, bunnyX, bunnyY, animation))
			{
				return true;
			}
		}
		
		return false;
	}

	private boolean checkForCollision(Log log, float bunnyX, float bunnyY, BunnyAnimation animation)
	{
		if (checkForImageCollision(log, bunnyX, bunnyY, animation))
		{
			return checkForPixelCollision(log, bunnyX, bunnyY, animation);
		}
		
		return false;
	}

	private boolean checkForImageCollision(Log log, float bunnyX, float bunnyY, BunnyAnimation animation)
	{
		return bunnyX + IMAGE_WIDTH >= log.getX() && bunnyX <= log.getX() + Log.IMAGE_WIDTH && bunnyY <= log.getY() + Log.IMAGE_HEIGHT && bunnyY + IMAGE_HEIGHT >= log.getY();
	}
	
	private boolean checkForPixelCollision(Log log, float bunnyX, float bunnyY, BunnyAnimation animation)
	{
		for (PixelLocation location : animation.getCurrentFramePixelLocations())
		{
			int x = (int) (bunnyX + location.getX() - log.getX());
			int y = (int) (bunnyY + location.getY() - log.getY());
			
			if (log.getImagePixelLocations().contains(new PixelLocation(x + 1, y)))
			{
				return true;
			}
		}
		
		return false;
	}

}
