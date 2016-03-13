package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.carrot.CarrotManager;
import com.bhop.game.util.singleton.SingletonManager;

public abstract class BasicPopup extends BasicGameObject
{
	
	protected final CarrotManager carrotManager;
	
	protected final TrueTypeFont font;
	
	protected final String message;
	
	protected boolean movesLeft;
	
	protected BasicPopup() throws SlickException
	{
		super(SPRITE_DIR + "game_information/popupicon.png");
		
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		message = setMessage();
		font = createFont(20);
		y = WINDOW_HEIGHT / 4.5f;
		x = -image.getWidth();
	}
	
	protected abstract String setMessage();
	
	protected void popup()
	{
		if (movesLeft)
		{
			if (x > (WINDOW_WIDTH - image.getWidth()) * 0.5f)
			{
				x -= 3;
			}
		}
		else if (x < WINDOW_WIDTH - image.getWidth())
		{
			x += 10;
		}
		else
		{
			movesLeft = true;
		}
	}

	protected void hide()
    {
		if (!movesLeft)
		{
			if (x > -image.getWidth())
			{
				x -= 10;
			}
		}
		else if (x < WINDOW_WIDTH - image.getWidth())
		{
			x += 3;
		}
		else
		{
			movesLeft = false;
		}
    }

	@Override
    public void render() throws SlickException
    {
		if (x > -image.getWidth())
		{
			image.draw(x, y);
			
			font.drawString(x + (image.getWidth() - font.getWidth(message)) * 0.5f, y + (image.getHeight() - font.getHeight()) * 0.52f, message, COLOR);
		}
    }

}
