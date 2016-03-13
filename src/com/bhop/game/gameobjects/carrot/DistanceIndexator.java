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
	
	private static final float LINE_X = (WINDOW_WIDTH - 155) * 0.5f;
	
	private final Image compassLine;
	
	private final Image line;
	
	private final Image carrot;
	
	private final Image bunnyIcon;
	
	private float distanceToNextCarrot;
	
	private float carrotX;
	
	private float renderX;
	
	private DistanceIndexator() throws SlickException
    {
		compassLine = new Image(SPRITE_DIR + "distance_indexator/compass_line.png");
		line = new Image(SPRITE_DIR + "distance_indexator/line1.png");
		carrot = new Image(SPRITE_DIR + "distance_indexator/carrot1.png");
		bunnyIcon = new Image(SPRITE_DIR + "distance_indexator/bunny_icon1.png");
    }

	@Override
    public void update(Input input) throws SlickException
    {
		renderX = LINE_X + line.getWidth() - (line.getWidth() / ((distanceToNextCarrot - BunnyAnimation.IMAGE_WIDTH) / (carrotX - BunnyAnimation.IMAGE_WIDTH)));
		renderX = renderX > LINE_X + line.getWidth() - compassLine.getWidth() ? LINE_X + line.getWidth() - compassLine.getWidth() : renderX;
		renderX = renderX < LINE_X ? LINE_X : renderX;
    }

	@Override
    public void render() throws SlickException
    {
		int y = WINDOW_HEIGHT - line.getHeight() * 5;
		
//		frame.draw((LINE_X + frame.getWidth() - line.getWidth() - carrot.getWidth() * 1.5f) * 0.5f, y - frame.getHeight() * 0.65f);
		line.draw(LINE_X, y);
		carrot.draw(LINE_X + line.getWidth() + carrot.getWidth() * 0.5f, y + line.getHeight() * 0.5f - carrot.getHeight() * 0.5f);
		compassLine.draw(renderX, y);
		bunnyIcon.draw(renderX - bunnyIcon.getWidth() * 0.5f + compassLine.getWidth() * 0.65f, y - bunnyIcon.getHeight() * 1.0f);
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
