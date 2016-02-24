package com.bhop.game.gameobjects;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public interface GameObject
{
	
	public void update(Input input)  throws SlickException;
	
	public void render() throws SlickException;

}
