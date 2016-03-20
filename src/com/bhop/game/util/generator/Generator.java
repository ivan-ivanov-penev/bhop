package com.bhop.game.util.generator;

import static com.bhop.game.util.GameUtils.WINDOW_WIDTH;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.bunny.CameraMovement;
import com.bhop.game.util.singleton.SingletonManager;

/**
 * 
 * @author Ivan Penev
 *
 */
public abstract class Generator implements GameObject
{
	
	protected final List<GeneratedObject> generatedObjects;
	
	protected final CameraMovement movement;
	
	protected Generator() throws SlickException
	{
		generatedObjects = new ArrayList<GeneratedObject>();
		movement = SingletonManager.getSingleton(CameraMovement.class);
		
		fillGeneratedObjects();
	}
	
	protected void fillGeneratedObjects() throws SlickException
	{
		for (int i = 0; i < 6; i++)
		{
			generatedObjects.add(createGeneratedObject(i * (WINDOW_WIDTH - 1)));
		}
	}

	protected abstract GeneratedObject createGeneratedObject(float x) throws SlickException;

	@Override
	public void update(Input input) throws SlickException
	{
		manageGeneratedObjects();
		
		for (GeneratedObject object : generatedObjects)
		{
			object.update(movement.getCameraSpeed());
		}
	}

	@Override
	public void render() throws SlickException
	{
		for (GeneratedObject object : generatedObjects)
		{
			object.render();
		}
	}
	
	protected void manageGeneratedObjects() throws SlickException
	{
		if (generatedObjects.get(0).getX() <= -WINDOW_WIDTH * 3)
		{
			generatedObjects.remove(0);
			generatedObjects.add(createGeneratedObject(generatedObjects.get(generatedObjects.size() - 1).getX() + (WINDOW_WIDTH - 1)));
		}
	}

}
