package com.bhop.game.objects.carrot;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.util.singleton.Singleton;

import static com.bhop.game.util.GameUtils.*;

public class CarrotGenerator implements GameObject, Singleton
{
	
	private final TimeCounter timeCounter;
	
	private final DistanceIncrementFactor distanceIncrementFactor;
	
	private final Image carrotImage;
	
	private Carrot carrot;
	
	private int carrotCounter;
	
	private CarrotGenerator() throws SlickException
	{
		timeCounter = new TimeCounter();
		distanceIncrementFactor = new DistanceIncrementFactor(WINDOW_WIDTH * 1.5f);
		carrotImage = new Image("res/carrot/carrot_icon.png");
		carrot = new Carrot(WINDOW_WIDTH - carrotImage.getWidth());
	}
	
	public Carrot getCarrot()
    {
	    return carrot;
    }
	
	public void alertBunnyTookCarrot() throws SlickException
	{
		carrotCounter ++;

		spawnNewCarrot();
	}
	
	private void spawnNewCarrot() throws SlickException
	{
//		float x = 10000;
		float x = distanceIncrementFactor.getNextCarrotSpawnDistance();
		
		carrot = new Carrot(x);
		timeCounter.setTimeLeft(x);
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		checkIfCarrotIsMissed();
		
		carrot.update(input);
		timeCounter.update(input);
	}
	
	private void checkIfCarrotIsMissed() throws SlickException
	{
		if (carrot.getX() + carrot.getImageWidth() < 0)
		{
			carrot.resetX();
		}
	}

	@Override
	public void render() throws SlickException
	{
		renderCarrotCounter();
		
		carrot.render();
		timeCounter.render();
	}
	
	private void renderCarrotCounter() throws SlickException
	{
		float x = WINDOW_WIDTH - WINDOW_WIDTH / 3.5f;
		float y = WINDOW_HEIGHT - WINDOW_HEIGHT / 5;
		
		carrotImage.draw(x, y);
		FONT_TYPE.drawString(x + carrotImage.getWidth() - 32, y + 32, " x " + carrotCounter);
	}
	
	 // TODO: delete this method
	public static void main(String[] args)
    {
		DistanceIncrementFactor d = new DistanceIncrementFactor(WINDOW_WIDTH * 1.5f);
		
	    for (int i = 0; i < 999; i++)
        {
			System.out.println(d.getNextCarrotSpawnDistance());
        }
    }

}
