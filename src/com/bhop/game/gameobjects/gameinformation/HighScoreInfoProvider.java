package com.bhop.game.gameobjects.gameinformation;

import java.awt.Font;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.carrot.CarrotManager;
import com.bhop.game.highscore.HighScoreManager;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

import static com.bhop.game.gameobjects.timecounter.GameEndWatcher.isGameEnd;
import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;

@SingletonClass
public class HighScoreInfoProvider extends BasicGameObject implements Singleton
{

	private final CarrotManager carrotManager;
	
	private final HighScoreManager highScoreManager;
	
	private final TrueTypeFont fontType;
	
	private HighScoreInfoProvider() throws SlickException
	{
		super("signs/highscore2.png");
		
		x = (WINDOW_WIDTH - image.getWidth()) / 2;
		y = image.getHeight() - image.getHeight() / 10;
		
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		highScoreManager = SingletonManager.getSingleton(HighScoreManager.class);
		
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 20), true);
	}

	@Override
	public void update(Input input) throws SlickException {}

	@Override
	public void render() throws SlickException
	{
		if (isGameEnd())
		{
			image.draw(x, y);
			image.draw(x, 0);
			
			int currentScore = carrotManager.getCarrotCounter();
			
			String[] messages = getMessages(currentScore, highScoreManager.getHighScore());
			
			fontType.drawString(x + (image.getWidth() - fontType.getWidth(messages[0])) / 2, 0 + image.getHeight() / 2, messages[0], COLOR);
			fontType.drawString(x + (image.getWidth() - fontType.getWidth(messages[1])) / 2, y + image.getHeight() / 2, messages[1], COLOR);
			
			highScoreManager.rewriteHighScoreIfGreater(currentScore);
		}
	}

	private String[] getMessages(int currentScore, int highScore)
	{
	    String currentScoreMessage = "Current score: ";
    	String highScoreMessage = "High score: ";
		
		if (currentScore > highScore)
	    {
		    currentScoreMessage = "New high score: ";
	    	highScoreMessage = "Old high score: ";
	    }
		
		return new String[] { currentScoreMessage + currentScore, highScoreMessage + highScore};
	}

}
