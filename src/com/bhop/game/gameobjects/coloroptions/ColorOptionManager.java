package com.bhop.game.gameobjects.coloroptions;

import static com.bhop.game.gameobjects.coloroptions.ColorOption.IMAGE_WIDTH;
import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.bonuscolor.BonusColorUnlocker;
import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.coloroptions.ColorOption.BunnyColor;
import com.bhop.game.states.Menu;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonManager;

public class ColorOptionManager implements GameObject, Singleton
{
	
	private final BonusColorUnlocker colorUnlocker;
	
	private final TrueTypeFont font;
	
	private final Image mainSign;
	
	private final float x;
	
	private final float y;
	
	private List<ColorOption> colorBloks;
	
	private ColorOptionManager() throws SlickException
	{
		font = createFont(25);
		mainSign = new Image(SPRITE_DIR + "color_options/main_sign.png");
		x = (WINDOW_WIDTH - mainSign.getWidth()) *0.5f;
		y = mainSign.getHeight() * -0.15f;
		colorUnlocker = SingletonManager.getSingleton(BonusColorUnlocker.class);
		colorBloks = new ArrayList<ColorOption>();
		
		fillColorBlocks();
	}
	
	public List<ColorOption> getColorBloks()
    {
	    return colorBloks;
    }

	private void fillColorBlocks() throws SlickException
	{
		int numberOfBlocks = colorUnlocker.playerHasUnlockedBonus() ? BunnyColor.values().length : BunnyColor.values().length - 1;
		
		float firstX =(WINDOW_WIDTH - IMAGE_WIDTH * numberOfBlocks - IMAGE_WIDTH * 0.5f * (numberOfBlocks - 1)) / 2;
		
		for (BunnyColor bunnyColor : BunnyColor.values())
		{
			if (!(bunnyColor.equals(BunnyColor.BONUS) && !colorUnlocker.playerHasUnlockedBonus()))
			{
				colorBloks.add(new ColorOption(firstX, bunnyColor));
				
				firstX += IMAGE_WIDTH * 1.5f;
			}
		}
	}

	@Override
	public void update(Input input) throws SlickException
	{
		for (ColorOption colorBlock : colorBloks)
		{
			if (mouseIsOverImage(input, colorBlock) && input.isMousePressed(0))
			{
				Menu.informPlayerHasPickedColor(colorBlock.getBunnyColor());
			}
		}
	}

	@Override
	public void render() throws SlickException
	{
		for (ColorOption colorBlock : colorBloks)
		{
			colorBlock.render();
		}
		
		mainSign.draw(x, y);
		
		renderChooseColorText();
	}

	private void renderChooseColorText()
    {
	    String message = "CHOOSE BUNNY COLOR:";
		
		float x = this.x + (mainSign.getWidth() - font.getWidth(message)) * 0.5f;
		float y = (mainSign.getHeight() - font.getHeight()) * 0.55f;
		
		font.drawString(x, y, message, Color.black);
    }

}
