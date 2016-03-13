package com.bhop.game.gameobjects.environment.background;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.SlickException;

import com.bhop.game.bonusbackground.BonusBackgroundUnlocker;
import com.bhop.game.util.generator.GeneratedObject;
import com.bhop.game.util.singleton.SingletonManager;

public class SpecialBackground extends GeneratedObject
{
	
	static int randomMaxNumber = generateRandomNumber();
	
	public SpecialBackground(float x) throws SlickException
	{
		super(x, SPRITE_DIR + "backgrounds/special" + randomMaxNumber + "/");
	}
	
	@Override
	protected void update(float cameraSpeed)
	{
		x -= cameraSpeed / 15;
	}
	
	private static int generateRandomNumber()
	{
		int unlocked = SingletonManager.getSingleton(BonusBackgroundUnlocker.class).getNumberOfUnlockedBonusBackgrounds();
		
		return unlocked == 0 ? 1 : RANDOM.nextInt(unlocked) + 1;
	}

}
