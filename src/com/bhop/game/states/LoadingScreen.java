package com.bhop.game.states;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingScreen extends BasicGameState
{
	
	public static final int ID = 0;
	
	private int counter;

	@Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
    }

	@Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
		g.drawString("LOADING", WINDOW_WIDTH * 0.45f, WINDOW_HEIGHT * 0.45f);
    }

	@Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
		if (counter == FPS * 3)
		{
			game.getState(Menu.ID).init(game.getContainer(), game);
			game.enterState(Menu.ID);
		}
		else
		{
			counter += 1;
		}
    }

	@Override
    public int getID()
    {
	    return 0;
    }

}
