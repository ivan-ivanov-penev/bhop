package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.carrot.CarrotManager;
import com.bhop.game.sound.SoundPlayer;
import com.bhop.game.util.singleton.SingletonManager;

public abstract class BasicPopup extends BasicGameObject
{
	
	private static PopupManager popupManager;
	
	protected final CarrotManager carrotManager;
	
	private final SoundPlayer soundPlayerPopUp;
	
	private final SoundPlayer soundPlayerHidePopUp;
	
	protected final TrueTypeFont font;
	
	protected final String message;
	
	protected boolean movesLeft;
	
	private boolean finished;
	
	protected BasicPopup() throws SlickException
	{
		super("game_information/popupicon.png");
		
		popupManager = new PopupManager();
		
		soundPlayerPopUp = new SoundPlayer("res/sound/pop_up.wav");
		
		soundPlayerHidePopUp = new SoundPlayer("res/sound/pop_up_hide.wav");
		
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		
		message = setMessage();
		
		font = createFont(20);
		
		y = WINDOW_HEIGHT / 5.2f;
		x = -image.getWidth();
	}
	
	protected abstract String setMessage();
	
	protected abstract boolean hasToPopup();
	
    protected abstract void attemptPopup();
	
	@Override
	public void update(Input input) throws SlickException
	{
		if (hasToPopup())
		{
			popupManager.addToQueue(this);
		}
	}
	
	protected void update() throws SlickException
	{
		popupManager.update();
	}
	
	protected void popup()
	{
		soundPlayerPopUp.playSoundOnce();
		
		finished = false;
		
		if (movesLeft)
		{
			centerInScreen();
		}
		else if (x < WINDOW_WIDTH - image.getWidth())
		{
			x += 12.5;
		}
		else
		{
			movesLeft = true;
		}
	}

	private void centerInScreen()
    {
	    if (x > (WINDOW_WIDTH - image.getWidth()) * 0.5f)
	    {
	    	x -= 4;
	    }
    }

	protected void hide()
    {
		if (!movesLeft)
		{
			hideFromView();
		}
		else if (x < WINDOW_WIDTH - image.getWidth())
		{
			x += 4;
		}
		else
		{
			movesLeft = false;
			
			soundPlayerHidePopUp.playSoundOnce();
		}
    }

	private void hideFromView()
    {
	    if (x > -image.getWidth())
	    {
	    	x -= 12.5;
	    }
	    else
	    {
	    	finished = true;
	    }
    }
	
	public boolean isFinished()
    {
	    return finished;
    }

	@Override
    public void render() throws SlickException
    {
		if (x > -image.getWidth())
		{
			image.draw(x, y);
			
			font.drawString(x + (image.getWidth() - font.getWidth(message)) * 0.5f, y + (image.getHeight() - font.getHeight()) * 0.52f, message, COLOR);
		}
    }

}
