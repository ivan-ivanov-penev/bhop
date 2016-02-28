package com.bhop.game.gameobjects.indexator;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.bunny.CameraMovement;
import com.bhop.game.gameobjects.carrot.CarrotManager;
import com.bhop.game.gameobjects.timecounter.TimeCounter;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

import static com.bhop.game.util.GameUtils.*;

@SingletonClass
public class Indexator implements GameObject, Singleton
{
	
	private final TimeCounter timeCounter;
	
	private final CarrotManager carrotManager;
	
	private final CameraMovement cameraMovement;
	
	private final Image happy;
	
	private final Image angry;
	
	private Image renderImage;
	
	private int frameCounter;
	
	private Indexator() throws SlickException
    {
		timeCounter = SingletonManager.getSingleton(TimeCounter.class);
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		cameraMovement = SingletonManager.getSingleton(CameraMovement.class);
		
		happy = new Image(SPRITE_DIR + "indexator/indexator_happy.png");
		angry = new Image(SPRITE_DIR + "indexator/indexator_angry.png");
		renderImage = happy;
    }

	@Override
    public void update(Input input) throws SlickException
    {
		if (cameraMovement.getMovementSpeed() < 0)
		{
			renderImage = angry;
		}
		else if (frameCounter == FPS)
		{
			frameCounter = 0;
			
			renderAccordinglyToPlayerProgress(cameraMovement.getMovementSpeed() * timeCounter.getSecondsLeft() * FPS > carrotManager.getCarrot().getX());
		}
		else
		{
			frameCounter++;
		}
    }

	@Override
    public void render() throws SlickException
    {
		renderImage.draw(WINDOW_WIDTH / 1.75f, WINDOW_HEIGHT / 20);
    }

	private void renderAccordinglyToPlayerProgress(boolean isOnTime)
    {
	    if (isOnTime)
		{
			renderImage = happy;
		}
		else
		{
			renderImage = angry;
		}
    }

}
