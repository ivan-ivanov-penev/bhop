package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.FontUtils.*;
import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.carrot.CarrotManager;
import com.bhop.game.gameobjects.timecounter.GameEndWatcher;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
public class BasicInfoProvider extends BasicGameObject implements Singleton
{
	
	private final CarrotManager carrotManager;
	
	private final String message;
	
	private final TrueTypeFont font;
	
	private boolean movesLeft;
	
	private BasicInfoProvider() throws SlickException
    {
		super(SPRITE_DIR + "game_information/popupicon.png");
		
		message = "Get the carrot before the time runs out!";
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		font = createFont(20);
		y = WINDOW_HEIGHT / 5;
		x = -image.getWidth();
    }

	@Override
    public void update(Input input) throws SlickException
    {
		if (!carrotManager.gameHasBegan())
		{
			popup();
		}
		else
		{
			hide();
		}
    }
	
	private void popup()
	{
		if (movesLeft)
		{
			if (x > 50)
			{
				x -= 7;
			}
		}
		else if (x < 100)
		{
			x += 10;
		}
		else
		{
			movesLeft = true;
		}
	}

	private void hide()
    {
    }

	@Override
    public void render() throws SlickException
    {
		if (!carrotManager.gameHasBegan() && !GameEndWatcher.isGameEnd())
		{
			super.render();
			
			font.drawString(x + (image.getWidth() - font.getWidth(message)) * 0.5f, y + (image.getHeight() - font.getHeight()) * 0.5f, message, COLOR);
		}
    }

}
