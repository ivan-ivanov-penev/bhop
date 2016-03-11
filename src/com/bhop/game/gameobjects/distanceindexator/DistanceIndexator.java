package com.bhop.game.gameobjects.distanceindexator;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.carrot.CarrotManager;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
public class DistanceIndexator implements GameObject, Singleton
{
	
	private final CarrotManager carrotManager;
	
	private final Image bunnyImage;
	
	private float x;
	
	private float y;
	
	private DistanceIndexator() throws SlickException
    {
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		bunnyImage = new Image(SPRITE_DIR + "distance_indexator/bunny_head.png");
		
		y = WINDOW_HEIGHT - bunnyImage.getHeight() * 1.5f;
    }

	@Override
    public void update(Input input) throws SlickException
    {
		x = carrotManager.getCarrot().getX();
    }

	@Override
    public void render() throws SlickException
    {
		bunnyImage.draw(x, y);
    }

}
