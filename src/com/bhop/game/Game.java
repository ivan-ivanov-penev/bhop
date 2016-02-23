package com.bhop.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.states.Menu;

import static com.bhop.game.util.GameUtils.*;

public class Game extends StateBasedGame
{
	
	public Game()
	{
		super(GAME_NAME);
		
		addState(new Menu());
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
		appGameContainer.setIcon("res/icon.png");
		appGameContainer.setTargetFrameRate(FPS);
//		appGameContainer.setFullscreen(true);
//		appGameContainer.setVSync(true);
		appGameContainer.start();
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException
	{
		getState(Menu.ID).init(gameContainer, this);
		enterState(Menu.ID);
	}

}
