package com.bhop.game.gameobjects.carrot;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;
import static com.bhop.game.util.ImageUtils.*;

import java.awt.Font;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.bonusbackground.BonusBackgroundUnlocker;
import com.bhop.game.bonuscolor.BonusSkinUnlocker;
import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.timecounter.TimeCounter;
import com.bhop.game.sound.SoundPlayer;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
public class CarrotManager implements GameObject, Singleton
{
	
	private final TimeCounter timeCounter;
	
	private final BonusSkinUnlocker bonusUnlocker;
	
	private final BonusBackgroundUnlocker backgroundUnlocker;
	
	private final TrueTypeFont fontType;
	
	private final DistanceIncrementFactor distanceIncrementFactor;
	
	private final DistanceIndexator distanceIndexator;
	
	private final CarrotIcon carrotIcon;
	
	private final SoundPlayer soundPlayer;
	
	private Carrot carrot;
	
	private int carrotCounter;
	
	private CarrotManager() throws SlickException
	{
		timeCounter = SingletonManager.getSingleton(TimeCounter.class);
		bonusUnlocker = SingletonManager.getSingleton(BonusSkinUnlocker.class);
		backgroundUnlocker = SingletonManager.getSingleton(BonusBackgroundUnlocker.class);
		distanceIndexator = SingletonManager.getSingleton(DistanceIndexator.class);
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 30), true);
		carrotIcon = new CarrotIcon();
		carrot = new Carrot(WINDOW_WIDTH * 1.8f);
		distanceIncrementFactor = new DistanceIncrementFactor(carrot.getX());
		distanceIndexator.setDistanceToNextCarrot(carrot.getX());
		soundPlayer = new SoundPlayer("res/sound/carrot_collect.wav");
	}
	
	public Carrot getCarrot()
    {
	    return carrot;
    }
	
	public int getCarrotCounter()
	{
		return carrotCounter;
	}
	
	public void alertBunnyTookCarrot() throws SlickException
	{
		carrotIcon.alertBunnyPickedCarrot();
		carrotCounter ++;
		backgroundUnlocker.alertBunnyHasPickedUpCarrot();

		spawnNewCarrot();
		checkBonusColorUnclock();
		
		distanceIndexator.setDistanceToNextCarrot(carrot.getX());
		soundPlayer.playSoundOnce();
	}
	
	private void checkBonusColorUnclock()
	{
		if (playerJustUnlockedBonus())
		{
			bonusUnlocker.unlockBonus();
		}
	}
	
	private void spawnNewCarrot() throws SlickException
	{
		float x = distanceIncrementFactor.getNextCarrotSpawnDistance();
		
		carrot = new Carrot(x);
		timeCounter.setTimeLeft(x);
		
		soundPlayer.alertSoundHasToBePlayed();
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		checkIfCarrotIsMissed();
		
		carrot.update(input);
		distanceIndexator.setCarrotX(carrot.getX());
	}
	
	private void checkIfCarrotIsMissed() throws SlickException
	{
		if (carrot.getX() + carrot.getImageWidth() < -WINDOW_WIDTH / 5)
		{
			carrot.setX(gameHasBegan() ? timeCounter.getDistanceBasedOnTimeLeft() : carrot.getX());
			distanceIndexator.setDistanceToNextCarrot(carrot.getX());
			
			soundPlayer.alertSoundHasToBePlayed();
		}
	}

	@Override
	public void render() throws SlickException
	{
		renderCarrotCounter();
		
		carrot.render();
	}
	
	private void renderCarrotCounter() throws SlickException
	{
		float x = WINDOW_WIDTH - WINDOW_WIDTH / 3.5f + 10;
		float y = WINDOW_HEIGHT - WINDOW_HEIGHT / 5 + 10;
		
		carrotIcon.render(x, y);
		fontType.drawString(x + carrotIcon.getWidth() - 32, y + 32, " x " + carrotCounter);
	}
	
	public boolean gameHasBegan()
	{
		return carrotCounter > 0;
	}
	
	public boolean playerJustUnlockedBonus()
	{
		return carrotCounter == 30;
	}
	
	private class CarrotIcon
	{
		
		private final Image carrotImage;
		
		private boolean hasToScale;
		
		private float scale;
		
		private CarrotIcon() throws SlickException
		{
			carrotImage = createImage("carrot/carrot_icon2.png");
			scale = 1f;
		}
		
		private void render(float x, float y)
		{
			scaleImage();
			adjustRotation();
			
			x -= (carrotImage.getWidth() * scale - carrotImage.getWidth()) * 0.5f;
			y -= (carrotImage.getHeight() * scale - carrotImage.getHeight()) * 0.5f;
			
			carrotImage.draw(x, y, scale);
		}
		
		private void scaleImage()
		{
			incrementScale(0.035f);
			
			if (scale > 1.2f)
			{
				hasToScale = false;
			}
			else if (scale < 1)
			{
				scale = 1;
			}
		}

		private void incrementScale(final float scaleValue)
		{
			if (hasToScale)
			{
				scale += scaleValue;
			}
			else
			{
				scale -= scaleValue;
			}
		}
		
		private void adjustRotation()
		{
			carrotImage.setCenterOfRotation(carrotImage.getWidth() * scale * 0.5f, carrotImage.getHeight() * scale * 0.5f);
			
			final int angle = 7;
			
			if (hasToScale)
			{
				if (scale < 1.12f)
				{
					carrotImage.rotate(angle);
				}
				else
				{
					carrotImage.rotate(-angle);
				}
			}
			else if (scale != 1f)
			{
				if (scale > 1.08f)
				{
					carrotImage.rotate(-angle);
				}
				else
				{
					carrotImage.rotate(angle);
				}
			}
			else if (carrotImage.getRotation() != 0)
			{
				carrotImage.rotate(angle);
			}
		}

		private void alertBunnyPickedCarrot()
		{
			hasToScale = true;
		}
		
		private float getWidth()
		{
			return carrotImage.getWidth();
		}
		
	}
	
}
