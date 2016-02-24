package com.bhop.game.gameobjects.coloroptions;

import static com.bhop.game.gameobjects.coloroptions.ColorOption.IMAGE_WIDTH;
import static com.bhop.game.util.GameUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.bonuscolor.BonusColorUnlocker;
import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.coloroptions.ColorOption.BunnyColor;
import com.bhop.game.states.Menu;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonManager;

public class ColorOptionManager implements GameObject, Singleton
{
	
	private final BonusColorUnlocker colorUnlocker;
	
	private List<ColorOption> colorBloks;
	
	private ColorOptionManager() throws SlickException
	{
		colorUnlocker = SingletonManager.getSingleton(BonusColorUnlocker.class);
		colorBloks = new ArrayList<ColorOption>();
		
		fillColorBlocks();
	}
	
	public List<ColorOption> getColorBloks()
    {
	    return colorBloks;
    }

	protected void fillColorBlocks() throws SlickException
	{
		int numberOfBlocks = colorUnlocker.playerHasUnlockedBonus() ? BunnyColor.values().length : BunnyColor.values().length - 1;
		
		float firstX = (WINDOW_WIDTH - (numberOfBlocks * (IMAGE_WIDTH + IMAGE_WIDTH / 2))) / 2;
		
		for (BunnyColor bunnyColor : BunnyColor.values())
		{
			putColorBlockPrecisely(firstX + IMAGE_WIDTH / 4, bunnyColor);
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
			colorBloks.add(new ColorOption(colorBloks.get(colorBloks.size() - 1).getX() + colorBloks.get(colorBloks.size() - 1).getImageWidth() + colorBloks.get(0).getImageWidth() / 2, bunnyColor));
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
	}

}
