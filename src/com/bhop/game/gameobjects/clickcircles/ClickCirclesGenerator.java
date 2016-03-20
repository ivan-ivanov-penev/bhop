package com.bhop.game.gameobjects.clickcircles;

import static com.bhop.game.util.ImageUtils.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.InputUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class ClickCirclesGenerator implements GameObject, Singleton
{
	
	private final Image circle;
	
	private boolean drawCircle;
	
	private float x;
	
	private float y;
	
	private float scale;
	
	private float transperenceyFactor;
	
	private ClickCirclesGenerator() throws SlickException
    {
		circle = createImage("click_circle/0.png");
		
		scale = 0.5f;
		transperenceyFactor = 1f;
    }

	@Override
    public void update(Input input) throws SlickException
    {
		if (!drawCircle && InputUtils.isLeftMouseButtonPressed())
		{
			drawCircle = true;
		}
		
		if (drawCircle)
		{
			x = input.getMouseX();
			y = input.getMouseY();
		}
    }

	@Override
    public void render() throws SlickException
    {
		attemptCircleDraw();
		
		attemptScaleReset();
    }

	private void attemptCircleDraw()
    {
	    if (drawCircle)
		{
			circle.draw(x - circle.getWidth() * scale * 0.5f, y - circle.getHeight() * scale * 0.5f, scale,new Color(1, 1, 1, transperenceyFactor));
			
			scale *= 1.09f;
			
			transperenceyFactor -= 0.12f;
		}
    }

	private void attemptScaleReset()
    {
	    if (scale >= 1)
		{
			scale = 0.5f;
			transperenceyFactor = 1f;
			
			drawCircle = false;
		}
    }

}
