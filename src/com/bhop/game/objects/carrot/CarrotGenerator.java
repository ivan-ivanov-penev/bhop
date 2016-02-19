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
	
	private final DistanceIncrementFactorManager distanceIncrementFactorManager;
	
	private final Image carrotImage;
	
	private Carrot carrot;
	
	private int carrotCounter;
	
	private CarrotGenerator() throws SlickException
	{
		timeCounter = new TimeCounter();
		distanceIncrementFactorManager = new DistanceIncrementFactorManager();
		carrotImage = new Image("res/carrot/carrot_icon.png");
		carrot = new Carrot(WINDOW_WIDTH - carrotImage.getWidth());
	}
	
	public void alertBunnyTookCarrot() throws SlickException
	{
		carrotCounter ++;

		spawnNewCarrot();
	}
	
	private void spawnNewCarrot() throws SlickException
	{
//		float x = 10000;
		float x = carrot.getSpawnX() * distanceIncrementFactorManager.getSpawDistanceIncrementFactor();
		
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
	
	public Carrot getCarrot()
    {
	    return carrot;
    }
	
	private static class DistanceIncrementFactorManager
	{

		private float numerator;

		private float denominator;
		
		public DistanceIncrementFactorManager()
        {
			numerator = 1f;
			denominator = 0f;
        }
		
		public float getSpawDistanceIncrementFactor()
        {
			numerator += 1;
			denominator += 1;
			
	        return numerator / denominator;
        }
		
	}
	 // TODO: delete this method
	public static void main(String[] args)
    {
		DistanceIncrementFactorManager d = new DistanceIncrementFactorManager();
		
		float x = WINDOW_WIDTH;
		
	    for (int i = 0; i < 999; i++)
        {
	        x = d.getSpawDistanceIncrementFactor() * x;
	        
			System.out.println(x);
        }
    }

}
