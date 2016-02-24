package com.bhop.game.gameobjects.coloroptions;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.util.GameUtils;

public class ColorOption extends BasicGameObject
{
	
	static final int IMAGE_WIDTH = 120;
	
	private final BunnyColor bunnyColor;
	
	ColorOption(float x, BunnyColor bunnyColor) throws SlickException
	{
		super("res/color_options/card.png");
		
		this.x = x;
		this.y = GameUtils.WINDOW_HEIGHT / 4;
		this.bunnyColor = bunnyColor;
	}
	
	public BunnyColor getBunnyColor()
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
