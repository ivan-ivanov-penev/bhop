package com.bhop.game.gameobjects.carrot;

import java.awt.Font;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.bonuscolor.BonusColorUnlocker;
import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.timecounter.TimeCounter;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

import static com.bhop.game.util.GameUtils.*;

@SingletonClass
public class CarrotManager implements GameObject, Singleton
{
	
	private final TimeCounter timeCounter;
	
	private final BonusColorUnlocker bonusUnlocker;
	
	private final TrueTypeFont fontType;
	
	private final DistanceIncrementFactor distanceIncrementFactor;
	
	private final Image carrotImage;
	
	private Carrot carrot;
	
	private int carrotCounter;
	
	private CarrotManager() throws SlickException
	{
		timeCounter = SingletonManager.getSingleton(TimeCounter.class);
		bonusUnlocker = SingletonManager.getSingleton(BonusColorUnlocker.class);
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 30), true);
		carrotImage = new Image("res/carrot/carrot_icon.png");
		carrot = new Carrot(WINDOW_WIDTH * 1.8f);
		distanceIncrementFactor = new DistanceIncrementFactor(carrot.getX());
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
		carrotCounter ++;

		spawnNewCarrot();
		checkBonusColorUnclock();
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
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		checkIfCarrotIsMissed();
		
		carrot.update(input);
	}
	
	private void checkIfCarrotIsMissed() throws SlickException
	{
		if (carrot.getX() + carrot.getImageWidth() < -WINDOW_WIDTH / 5)
		{
			carrot.setX(gameHasBegan() ? timeCounter.getDistanceBasedOnTimeLeft() : carrot.getX());
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
		float x = WINDOW_WIDTH - WINDOW_WIDTH / 3.5f;
		float y = WINDOW_HEIGHT - WINDOW_HEIGHT / 5;
		
		carrotImage.draw(x, y);
		fontType.drawString(x + carrotImage.getWidth() - 32, y + 32, " x " + carrotCounter);
	}
	
	public boolean gameHasBegan()
	{
		return carrotCounter > 0;
	}
	
	public boolean playerJustUnlockedBonus()
	{
		return carrotCounter == 3;
	}
	
}
