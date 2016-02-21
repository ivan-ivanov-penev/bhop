package com.bhop.game.objects.coloroptions;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.BasicGameObject;
import com.bhop.game.util.GameUtils;

public class ColorOption extends BasicGameObject
{
	
	static final int IMAGE_WIDTH = 96;
	
	private final BunnyColor bunnyColor;
	
	ColorOption(float x, BunnyColor bunnyColor) throws SlickException
	{
		super("res/color_blocks/" + bunnyColor.getColorName() + ".png");
		
		this.x = x;
		this.y = GameUtils.WINDOW_HEIGHT / 3;
		this.bunnyColor = bunnyColor;
	}
	
	BunnyColor getBunnyColor()
	{
		return bunnyColor;
	}

	@Override
	public void update(Input input) throws SlickException {}
	
	public static enum BunnyColor
	{
		BONUS("bonus"),
		BLUE("blue"),
		PINK("pink");
		
		private final String fileName;
		
		private BunnyColor(String fileName)
		{
			this.fileName = fileName;
		}
		
		public String getColorName()
		{
			return fileName;
		}
	}

}
