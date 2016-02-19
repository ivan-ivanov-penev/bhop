package com.bhop.game.objects.carrot;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.objects.bunny.CameraMovement.*;

public class TimeCounter implements GameObject
{
	
	private int secondsLeft;
	
	private int fpsCounter;
	
	TimeCounter()
    {
		secondsLeft = 3;
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
		FONT_TYPE.drawString(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 10,  String.valueOf(secondsLeft), new Color(0, 0, 0));
    }
	
	private void checkForTimeExpiration()
	{
		if (secondsLeft < 0)
		{
//			System.exit(0);
		}
	}
	
	void setTimeLeft(float x)
	{
		secondsLeft = (int) (x / (CAMERA_SPEED * FPS * 1.8f));
	}

}
