package com.bhop.game.objects.timecounter;

import java.awt.Font;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.objects.GameObject;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.objects.bunny.CameraMovement.*;

@SingletonClass
public class TimeCounter implements GameObject, Singleton
{
	
	private final TrueTypeFont fontType;
	
	private int secondsLeft;
	
	private int fpsCounter;
	
	private TimeCounter()
    {
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 30), true);
		secondsLeft = 10;
    }

	@Override
    public void update(Input input) throws SlickException
    {
		updateFpsCounter();
		checkForTimeExpiration();
    }

	private void updateFpsCounter()
    {
	    fpsCounter++;
		
		if (fpsCounter == FPS)
		{
			fpsCounter = 0;
			
			secondsLeft -= 1;
		}
    }

	@Override
	public void render() throws SlickException
    {
		fontType.drawString(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 10,  String.valueOf(secondsLeft < 0 ? 0 : secondsLeft), BLACK);
    }
	
	private void checkForTimeExpiration()
	{
		if (secondsLeft < 0)
		{
			GameEndWatcher.alertWatchersGameHasEnded();
		}
	}
	
	public void setTimeLeft(float x)
	{
		secondsLeft = (int) (x / (CAMERA_SPEED * FPS * (((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 2) + MIN_SPEED_FACTOR)));
		
		if (secondsLeft < 5)
		{
			secondsLeft = 5;
		}
	}
	
	public float getDistanceBasedOnTimeLeft()
	{
		return secondsLeft * CAMERA_SPEED * FPS * (((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) / 2) + MIN_SPEED_FACTOR);
	}
}
