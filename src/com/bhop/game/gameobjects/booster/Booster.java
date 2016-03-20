package com.bhop.game.gameobjects.booster;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.ImageUtils.createImage;

import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.PixelLocation;
import com.bhop.game.gameobjects.bunny.Collidable;
import com.bhop.game.gameobjects.carrot.CarrotManager;
import com.bhop.game.gameobjects.pauseicon.PauseIcon;
import com.bhop.game.gameobjects.timecounter.GameEndWatcher;
import com.bhop.game.util.ImageUtils;
import com.bhop.game.util.SoundUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
public class Booster extends BasicGameObject implements Singleton, Collidable
{
	
	private static final int SPAWNING_INTERVAL_IN_SECONDS = 2;
	
	private static volatile boolean boosterAcquired;
	
	private final Set<PixelLocation> pixelLocations;
	
	private final CarrotManager carrotManager;
	
	private final BoosterExpireWatcher boosterExpireWatcher;
	
	private final BoosterAnimation animation;
	
	private boolean generateBooster;
	
	private int frameCounter;
	
	private Booster() throws SlickException
	{
		super("booster/booster_lettuce_and_border.png");
		
		y = WINDOW_HEIGHT * 0.35f;
		
		pixelLocations = ImageUtils.getPixelsLocations(createImage("booster/booster_lettuce_border.png"));
		
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		
		boosterExpireWatcher = new BoosterExpireWatcher();
		
		animation = new BoosterAnimation();
	}
	
	public static boolean isBoosterAcquired()
	{
		return boosterAcquired;
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		boosterExpireWatcher.update();
		
		incrementFrameCounter();
		
		attemptToRemoveBooster();
		
		attemptUpdateBooster(input);
	}

	private void attemptUpdateBooster(Input input) throws SlickException
	{
		if (generateBooster)
		{
			super.update(input);
		}
		else if (carrotManager.gameHasBegan())
		{
			attemptGenerateBooster();
		}
	}
	
	private void attemptGenerateBooster()
	{
		if (frameCounter == FPS * SPAWNING_INTERVAL_IN_SECONDS && RANDOM.nextInt(30) == 0)
		{
			frameCounter = 0;
			
			generateBooster = true;
			
			x = WINDOW_WIDTH * 2;
		}
	}
	
	private void attemptToRemoveBooster()
	{
		if (x < -WINDOW_WIDTH)
		{
			generateBooster = false;
		}
	}
	
	private void incrementFrameCounter()
	{
		frameCounter += 1;
		
		if (frameCounter > FPS * SPAWNING_INTERVAL_IN_SECONDS)
		{
			frameCounter = 0;
		}
	}

	@Override
	public void render() throws SlickException
	{
		if (generateBooster && !boosterAcquired)
		{
			animation.render();
		}
	}

	@Override
	public Set<PixelLocation> getImagePixelLocations()
	{
		return pixelLocations;
	}
	
	public void alertBunnyTookBooster()
	{
		SoundUtils.playSound("res/sound/booster_collect.wav");
		
		boosterAcquired = true;
	}
	
	private class BoosterExpireWatcher
	{
		
		private static final int BOOSTER_DURATION_IN_SECONDS = 10;
		
		private int frameCounter;
		
		private void update()
		{
			if (boosterAcquired)
			{
				incrementFrameCounter();
			}
		}
		
		private void incrementFrameCounter()
		{
			frameCounter += 1;
			
			if (frameCounter > FPS * BOOSTER_DURATION_IN_SECONDS || GameEndWatcher.isGameEnd())
			{
				frameCounter = 0;
				
				boosterAcquired = false;
				
				generateBooster = false;
			}
		}
		
	}
	
	private class BoosterAnimation
	{
		
		private float scale;
		
		private boolean incrementImageSize;
		
		private BoosterAnimation()
        {
			scale = 1.0f;
			incrementImageSize = true;;
        }
		
		private void render()
		{
			if (!PauseIcon.isGamePaused() && !GameEndWatcher.isGameEnd())
			{
				changeScale();
				
				image.rotate(1);
			}

			image.setCenterOfRotation(image.getWidth() * scale / 2, image.getHeight() * scale / 2);
			image.draw(x, y, scale);
		}

		private void changeScale()
        {
			scale += incrementImageSize ? 0.004f : -0.004f;
			
			if (scale > 1.2f)
			{
				incrementImageSize = false;
			}
			else if (scale < 1.0f)
			{
				incrementImageSize = true;
			}
        }
		
	}

}
