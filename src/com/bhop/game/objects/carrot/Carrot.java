package com.bhop.game.objects.carrot;

import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import java.util.Set;

import org.newdawn.slick.SlickException;

import com.bhop.game.objects.BasicGameObject;
import com.bhop.game.objects.PixelLocation;
import com.bhop.game.objects.bunny.Collidable;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.ImageUtils;

public class Carrot extends BasicGameObject implements Collidable
{
	
	private final Set<PixelLocation> pixelLocations;
	
	Carrot(float x) throws SlickException
    {
	    super("res/carrot/carrot.png");

	    this.x = x;
		this.y = GameUtils.WINDOW_HEIGHT - 200;
		this.pixelLocations = ImageUtils.getPixelsLocations(image);
    }

	@Override
    public Set<PixelLocation> getImagePixelLocations()
    {
	    return pixelLocations;
    }
	
	void setX(float spawnX)
	{
		if (spawnX < WINDOW_WIDTH)
		{
			x = WINDOW_WIDTH;
		}
		else
		{
			x = spawnX;
		}
	}
	
}
