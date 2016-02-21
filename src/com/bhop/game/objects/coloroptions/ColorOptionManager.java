package com.bhop.game.objects.coloroptions;

import static com.bhop.game.objects.coloroptions.ColorOption.IMAGE_WIDTH;
import static com.bhop.game.util.GameUtils.*;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.bonuscolor.BonusColorUnlocker;
import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.coloroptions.ColorOption.BunnyColor;
import com.bhop.game.states.Menu;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonManager;

public class ColorOptionManager implements GameObject, Singleton
{
	
	private final BonusColorUnlocker colorUnlocker;
	
	private final TrueTypeFont fontType;
	
	private List<ColorOption> colorBloks;
	
	private ColorOptionManager() throws SlickException
	{
		colorUnlocker = SingletonManager.getSingleton(BonusColorUnlocker.class);
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 30), true);
		colorBloks = new ArrayList<ColorOption>();
		
		fillColorBlocks();
	}

	protected void fillColorBlocks() throws SlickException
	{
		int numberOfBlocks = colorUnlocker.playerHasUnlockedBonus() ? BunnyColor.values().length : BunnyColor.values().length - 1;
		
		float firstX =  (WINDOW_WIDTH - (numberOfBlocks * (IMAGE_WIDTH + IMAGE_WIDTH / 3))) / 2;
		
		for (BunnyColor bunnyColor : BunnyColor.values())
		{
			putColorBlockPrecisely(firstX, bunnyColor);
		}
	}

	protected void putColorBlockPrecisely(float firstX, BunnyColor bunnyColor) throws SlickException
	{
		if (bunnyColor.equals(BunnyColor.BONUS) && !colorUnlocker.playerHasUnlockedBonus())
		{
			return;
		}
		else if (colorBloks.isEmpty())
		{
			colorBloks.add(new ColorOption(firstX, bunnyColor));
		}
		else
		{
			colorBloks.add(new ColorOption(colorBloks.get(colorBloks.size() - 1).getX() + colorBloks.get(colorBloks.size() - 1).getImageWidth() + colorBloks.get(0).getImageWidth() / 3, bunnyColor));
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
		renderText();
		
		for (ColorOption colorBlock : colorBloks)
		{
			colorBlock.render();
		}
	}
	
	private void renderText()
	{
		fontType.drawString(WINDOW_WIDTH / 4, WINDOW_HEIGHT / 5, "Choose bunny color:", BLACK);
	}

}
