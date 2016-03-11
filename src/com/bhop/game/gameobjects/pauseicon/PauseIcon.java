package com.bhop.game.gameobjects.pauseicon;

import static com.bhop.game.util.GameUtils.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.InputUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class PauseIcon implements GameObject, Singleton
{
	
	private static boolean gamePaused;
	
	private final Image pauseIcon;
	
	private final Image playIcon;
	
	private Image renderIcon;
	
	private float x;
	
	private float y;
	
	private PauseIcon() throws SlickException
    {
		pauseIcon = new Image(SPRITE_DIR + "pause_icon/pause.png");
		playIcon = new Image(SPRITE_DIR + "pause_icon/play2.png");
		renderIcon = pauseIcon;

		x = 8 + renderIcon.getWidth() * 1.5f;
		y = WINDOW_HEIGHT - renderIcon.getHeight() - 7;
    }

	public static boolean isGamePaused()
	{
		return gamePaused;
	}

	@Override
    public void update(Input input) throws SlickException
    {
		if (mouseIsOverImage(input, renderIcon, x, y) && InputUtils.isLeftMouseButtonPressed())
		{
			changePauseState();
		}
    }

	private void changePauseState()
    {
	    boolean isPauseIcon = renderIcon == pauseIcon;
	    
	    renderIcon = isPauseIcon ? playIcon : pauseIcon;
	    
	    x += isPauseIcon ? 2 : -2;
	    y += isPauseIcon ? 2 : -2;
	    
	    gamePaused = !gamePaused;
    }

	@Override
    public void render() throws SlickException
    {
		renderIcon.draw(x, y);
    }

}
