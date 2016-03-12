package com.bhop.game.gameobjects.timecounter;

import java.awt.Font;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.gameobjects.bunny.CameraMovement.*;
import static com.bhop.game.util.FontUtils.*;

@SingletonClass
public class TimeCounter implements GameObject, Singleton
{
	
	private final TrueTypeFont fontType;
	
	private final Image sign;
	
	private int secondsLeft;
	
	private int fpsCounter;
	
	private TimeCounter() throws SlickException
    {
		sign = new Image(SPRITE_DIR + "signs/counter.png");
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
	
	public int getSecondsLeft()
    {
	    return secondsLeft;
    }

	@Override
	public void render() throws SlickException
    {
		String timeLeft = String.valueOf(secondsLeft < 0 ? 0 : secondsLeft);
		
		float x = (WINDOW_WIDTH - sign.getWidth()) * 0.5f;
		
		sign.draw(x, -26);
		fontType.drawString(x + (sign.getWidth() - fontType.getWidth(timeLeft)) / 2, WINDOW_HEIGHT * 0.05f,  timeLeft, COLOR_BLACKISH);
    }
	
	private void checkForTimeExpiration()
	{
		if (secondsLeft < 0)
		{
			GameEndWatcher.alertGameHasEnded();
		}
	}
	
	public void setTimeLeft(float x)
	{
		secondsLeft += (int) (x / (CAMERA_SPEED * FPS * (((MAX_SPEED_FACTOR - MIN_SPEED_FACTOR) * 0.35f) + MIN_SPEED_FACTOR)));
		
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
