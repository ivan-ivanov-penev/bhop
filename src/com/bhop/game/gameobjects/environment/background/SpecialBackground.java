package com.bhop.game.gameobjects.environment.background;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.bonusbackground.BonusBackgroundUnlocker.MAX_BACKGROUNDS_TO_UNLOCK;

import org.newdawn.slick.SlickException;

import com.bhop.game.bonusbackground.BonusBackgroundUnlocker;
import com.bhop.game.util.generator.GeneratedObject;
import com.bhop.game.util.singleton.SingletonManager;

public class SpecialBackground extends GeneratedObject
{
	
	public SpecialBackground(float x) throws SlickException
	{
		super(x, SPRITE_DIR + "backgrounds/special" + generateRandomNumber() + "/");
	}
	
	@Override
	protected void update(float cameraSpeed)
	{
		x -= cameraSpeed / 15;
	}
	
	private static int generateRandomNumber()
	{
		int unlocked = SingletonManager.getSingleton(BonusBackgroundUnlocker.class).getNumberOfUnlockedBonusBackgrounds();
		
		return RANDOM.nextInt(unlocked > MAX_BACKGROUNDS_TO_UNLOCK ? MAX_BACKGROUNDS_TO_UNLOCK + 1: unlocked + 1);
	}

}
