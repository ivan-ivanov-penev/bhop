package com.bhop.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.states.Play;

import static com.bhop.game.util.GameUtils.*;

public class Game extends StateBasedGame
{
	
	public Game()
	{
		super(GAME_NAME);
		
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
	
	private static void runGame() throws Exception
	{
        AppGameContainer appGameContainer = new AppGameContainer(new Game());
		appGameContainer.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
//		appGameContainer.setIcon("res/grass.png");
		appGameContainer.setTargetFrameRate(FPS);
//		appGameContainer.setVSync(true);
		appGameContainer.start();
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException
	{
		getState(Play.ID).init(gameContainer, this);
		
		enterState(Play.ID);
	}

}
