package com.bhop.game.gameobjects.carrot;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.ImageUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.PixelLocation;
import com.bhop.game.gameobjects.bunny.CameraMovement;
import com.bhop.game.gameobjects.bunny.Collidable;
import com.bhop.game.gameobjects.pauseicon.PauseIcon;
import com.bhop.game.gameobjects.timecounter.GameEndWatcher;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.ImageUtils;
import com.bhop.game.util.singleton.SingletonManager;

public class Carrot implements GameObject, Collidable
{
	
	private float x;
	
	private final float y;
	
	private final CameraMovement movement;
	
	private final Animation animation;
	
	private final Map<Integer, Set<PixelLocation>> pixelLocations;
	
	Carrot(float x) throws SlickException
    {
	    this.x = x;
		this.y = GameUtils.WINDOW_HEIGHT - 245;
		this.movement = SingletonManager.getSingleton(CameraMovement.class);
		this.pixelLocations = new HashMap<Integer, Set<PixelLocation>>();
		this.animation = createAnimation("carrot/animation");
		this.animation.setPingPong(true);
		this.animation.setSpeed(1.2f);
		
		fillPixelLocations();
    }
	
	private void fillPixelLocations() throws SlickException
	{
		for (int i = 0; i < animation.getFrameCount(); i++)
		{
			pixelLocations.put(i, ImageUtils.getPixelsLocations(createImage("carrot/collision/" + i + "_carrot_border.png")));
		}
	}

	@Override
    public Set<PixelLocation> getImagePixelLocations()
    {
	    return pixelLocations.get(animation.getFrame());
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

	@Override
	public float getX()
	{
		return x;
	}

	@Override
	public float getY()
	{
		return y;
	}

	@Override
	public int getImageWidth()
	{
		return animation.getCurrentFrame().getWidth();
	}

	@Override
	public int getImageHeight()
	{
		return animation.getCurrentFrame().getHeight();
	}

	@Override
	public void update(Input input) throws SlickException
	{
		x -= movement.getMovementSpeed();
	}

	@Override
	public void render() throws SlickException
	{
		checkForGamePause();
		
		if (GameEndWatcher.isGameEnd() && !animation.isStopped())
		{
			animation.stop();
		}
		
		animation.draw(x, y);
	}

	private void checkForGamePause()
    {
		if (PauseIcon.isGamePaused())
		{
			animation.stop();
		}
		else if (animation.isStopped())
		{
			animation.start();
		}
    }
	
}
