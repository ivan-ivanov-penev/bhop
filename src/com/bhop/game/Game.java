package com.bhop.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.states.Menu;
import com.bhop.game.states.Play;
import com.bhop.game.util.FontUtils;

import static com.bhop.game.util.GameUtils.*;

public class Game extends StateBasedGame
{
	
	public Game()
	{
		super(GAME_NAME);
		
		addState(new Menu());
		addState(new Play());
	}

	public static void main(String[] args)
	{
		try
		{
			runGame();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void runGame() throws Exception
	{
        AppGameContainer appGameContainer = new AppGameContainer(new Game());
		appGameContainer.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		appGameContainer.setIcon(SPRITE_DIR + "game_icon.png");
		appGameContainer.setShowFPS(false);
		appGameContainer.setVSync(true);
		appGameContainer.setTargetFrameRate(FPS);
		appGameContainer.setMaximumLogicUpdateInterval(10);
		appGameContainer.setMinimumLogicUpdateInterval(0);
		appGameContainer.start();
		
		FontUtils.registerGameFont();
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException
	{
		getState(Menu.ID).init(gameContainer, this);
		enterState(Menu.ID);
	}

}
