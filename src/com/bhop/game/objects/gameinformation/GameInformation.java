package com.bhop.game.objects.gameinformation;

import java.awt.Font;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.highscore.HighScoreManager;
import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.carrot.CarrotManager;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

import static com.bhop.game.objects.timecounter.GameEndWatcher.isGameEnd;
import static com.bhop.game.util.GameUtils.*;

@SingletonClass
public class GameInformation implements GameObject, Singleton
{

	private final CarrotManager carrotManager;
	
	private final HighScoreManager highScoreManager;
	
	private final TrueTypeFont fontType;
	
	private final String gameInformationFirstLine;

	private final String gameInformationSecondLine;
	
	private int frameCounter;
	
	private GameInformation()
	{
		carrotManager = SingletonManager.getSingleton(CarrotManager.class);
		highScoreManager = SingletonManager.getSingleton(HighScoreManager.class);
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 20), true);
		gameInformationFirstLine = "Tap on the screen to make bunny jump and go faster!";
		gameInformationSecondLine = "Get to the next carrot before the time runs out!";
	}

	@Override
	public void update(Input input) throws SlickException {}

	@Override
	public void render() throws SlickException
	{
		if (!carrotManager.gameHasBegan() && !isGameEnd())
		{
			fontType.drawString(WINDOW_WIDTH / 10, WINDOW_HEIGHT / 5, gameInformationFirstLine, RED);
			fontType.drawString(WINDOW_WIDTH / 10 + 20, WINDOW_HEIGHT / 3, gameInformationSecondLine, RED);
		}
		
		if (carrotManager.playerJustUnlockedBonus() && frameCounter < FPS * 5)
		{
			frameCounter++;
			
			fontType.drawString(WINDOW_WIDTH / 7, WINDOW_HEIGHT / 5, "You've just unlocked bonus color for bunny!", RED);
		}
		
		if (isGameEnd())
		{
			fontType.drawString(WINDOW_WIDTH / 3, WINDOW_HEIGHT / 5, "Your current score: " + carrotManager.getCarrotCounter(), RED);
			fontType.drawString(WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3, "Your highest score: " + highScoreManager.getHighScore(), RED);
			
			highScoreManager.rewriteHighScoreIfGreater(carrotManager.getCarrotCounter());
		}
	}

}
