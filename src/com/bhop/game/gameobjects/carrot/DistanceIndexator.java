package com.bhop.game.gameobjects.carrot;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.bunny.animation.BunnyAnimation;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class DistanceIndexator implements GameObject, Singleton
{
	
	private static final float LINE_X = WINDOW_WIDTH * 0.4f;
	
	private final Image bunnyImage;
	
	private final Image line;
	
	private float distanceToNextCarrot;
	
	private float carrotX;
	
	private float renderX;
	
	private DistanceIndexator() throws SlickException
    {
		bunnyImage = new Image(SPRITE_DIR + "distance_indexator/bunny_head3.png");
		line = new Image(SPRITE_DIR + "distance_indexator/line.png");
    }

	@Override
    public void update(Input input) throws SlickException
    {
		renderX = LINE_X + line.getWidth() - (line.getWidth() / ((distanceToNextCarrot - BunnyAnimation.IMAGE_WIDTH) / (carrotX - BunnyAnimation.IMAGE_WIDTH)));
		renderX = renderX > LINE_X + line.getWidth() ? LINE_X + line.getWidth() : renderX;
		renderX = renderX < LINE_X ? LINE_X : renderX;
    }

	@Override
    public void render() throws SlickException
    {
		int y = WINDOW_HEIGHT - line.getHeight() * 5;
		
		line.draw(LINE_X, y);
		bunnyImage.draw(renderX, y);
    }
	
	void setCarrotX(float carrotX)
	{
		this.carrotX = carrotX;
	}
	
	void setDistanceToNextCarrot(float distanceToNextCarrot)
    {
	    this.distanceToNextCarrot = distanceToNextCarrot;
    }

}
