package com.bhop.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.environment.Sky;
import com.bhop.game.objects.Ground;
import com.bhop.game.objects.bunny.Bunny;

public class Play extends BasicGameState
{
	public static final int ID = 1;
	
	private Sky sky;
	
	private Ground ground;
	
	private Bunny bunny;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		sky = new Sky();
		ground = new Ground();
		bunny = new Bunny(ground);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		sky.draw();
		ground.draw();
		bunny.draw();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		checkForExitGame(container);
		ground.move();
		bunny.move(container.getInput());
	}

	@Override
	public int getID()
	{
		return ID;
	}
	
	private void checkForExitGame(GameContainer container)
	{
		if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
		{
			container.exit();
		}
	}
}
