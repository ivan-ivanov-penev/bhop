package com.bhop.game.gameobjects.coloroptions;

import static com.bhop.game.util.ImageUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.util.GameUtils;

public class ColorOption extends BasicGameObject
{
	
	static final int IMAGE_WIDTH = 105;
	
	private final BunnyColor bunnyColor;
	
	private final Sign sign;
	
	ColorOption(float x, BunnyColor bunnyColor) throws SlickException
	{
		super("color_options/" + bunnyColor.getColorName() + ".png");
		
		this.x = x;
		this.y = GameUtils.WINDOW_HEIGHT * 0.25f;
		this.bunnyColor = bunnyColor;
		this.sign = new Sign();
	}
	
	public BunnyColor getBunnyColor()
	{
		return bunnyColor;
	}

	@Override
	public void update(Input input) throws SlickException {}
	
	@Override
	public void render() throws SlickException
	{
		sign.render(x, y);
		image.draw(x + (sign.image.getWidth() - image.getWidth()) * 0.5f, y + (sign.image.getHeight() - image.getHeight()) * 0.71f);
	}
	
	@Override
	public Image getImage()
	{
	    return sign.image;
	}
	
	private static class Sign
	{
		
		private final Image image;
		
		public Sign() throws SlickException
		{
			image = createImage("color_options/sign.png");
		}
		
		public void render(float x , float y)
		{
			image.draw(x, y);
		}
		
	}
	
	public static enum BunnyColor
	{
		BONUS("bonus"),
		BLUE("blue"),
		PINK("pink");
		
		private final String colorName;
		
		private BunnyColor(String colorName)
		{
			this.colorName = colorName;
		}
		
		public String getColorName()
		{
			return colorName;
		}
	}

}
