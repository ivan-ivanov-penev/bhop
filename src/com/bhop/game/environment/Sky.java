package com.bhop.game.environment;

import java.util.Calendar;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sky
{
	
	private final Image image;
	
	public Sky() throws SlickException
	{
		image = getSkyImagePathAccordingToTimeOfDay();
	}
	
	private static Image getSkyImagePathAccordingToTimeOfDay() throws SlickException
	{
		int hoursOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
		if (hoursOfDay > 5 && hoursOfDay < 12)
		{
			return new Image("res/backgrounds/dawn.png");
		}
		
		if (hoursOfDay < 20)
		{
			return new Image("res/backgrounds/day.png");
		}
		
		return new Image("res/backgrounds/night.png");
	}
	
	public void draw()
	{
		image.draw();
	}

}
