package com.bhop.game.gameobjects.bunny.dummy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.coloroptions.ColorOption;

import static com.bhop.game.util.GameUtils.*;

public class DummyBunnyAnimation
{
	
	private Animation standIdle;
	
	private Animation scratch;
	
	private Animation renderAnimation;
	
	DummyBunnyAnimation(ColorOption colorOption) throws SlickException
    {
		createConfiguredStandIdleAnimation(colorOption);
		createConfiguredScratchAnimation(colorOption);
		
		renderAnimation = standIdle;
    }

	private void createConfiguredScratchAnimation(ColorOption colorOption) throws SlickException
    {
	    scratch = new Animation(createImageArrayFromDirectory("res/bunny/" + colorOption.getBunnyColor().getColorName() + "/scratch"), FPS, true);
//		scratch.setSpeed(0.75f);
		scratch.setPingPong(true);
		scratch.setLooping(false);
    }

	private void createConfiguredStandIdleAnimation(ColorOption colorOption) throws SlickException
    {
	    standIdle = new Animation(createImageArrayFromDirectory("res/bunny/" + colorOption.getBunnyColor().getColorName() + "/idle"), FPS, true);
//		standIdle.setSpeed(0.75f);
		standIdle.setLooping(false);
		standIdle.setCurrentFrame(RANDOM.nextInt(standIdle.getFrameCount()));
    }

    void attempChangeAnimation()
	{
    	if (renderAnimation.isStopped())
		{
			renderAnimation = Math.random() > 0.2 ? standIdle : scratch;
			renderAnimation.restart();
		}
	}

    void draw(float x, float y)
    {
		renderAnimation.draw(x, y);
    }

	public int getImageWidth()
    {
	    return standIdle.getCurrentFrame().getWidth();
    }

}
