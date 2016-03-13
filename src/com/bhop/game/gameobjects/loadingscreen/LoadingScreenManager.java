package com.bhop.game.gameobjects.loadingscreen;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class LoadingScreenManager implements GameObject, Singleton
{
	
	private static final Map<String, Animation> HINTS = new HashMap<String, Animation>();
	
	private final Image loadingScreen;
	
	private final TrueTypeFont font;
	
	private final TrueTypeFont info;
	
	private final float boxX;
	
	private final float boxY;
	
	private final String randomHint;
	
	private final String[] randomHintLines;
	
	private String renderMessage;
	
	private int counter;
	
	private LoadingScreenManager() throws SlickException
    {
		fillHints();
		
		loadingScreen = new Image(SPRITE_DIR + "loading_screen/loading_screen.png");
		font = new TrueTypeFont(new Font(FONT_TYPE, Font.BOLD, 30), true);
		info = createFont(20);
		randomHint = getRandomElement(new ArrayList<String>(HINTS.keySet()).toArray(new String[HINTS.size()]));
		randomHintLines = separateHintToLines();
		renderMessage = "";
		
		boxX = 235;
		boxY = 180;
    }
	
	private void fillHints() throws SlickException
	{
		HINTS.put("IF YOU CLICK ON THE SCRREN EXACTLY WHEN BUNNY HAS LANDED YOU WILL GET AN EXTRA SPEED BONUS!", createAnimation("speed_bonus"));
		HINTS.put("THE INDEXATOR ON TOP RIGHT CORNER OF THE SCREEN SHOWS IF YOU WILL GET THE CARROT ON TIME", createAnimation("indexator"));
//		HINTS.put("", createAnimation(""));
	}
	
	private String[] separateHintToLines()
	{
		
		int width = info.getWidth(randomHint);
		int numberOfLines = (int) (width / (WINDOW_WIDTH * 0.8f)) + 1;
		
		String[] randomHintLines = new String[numberOfLines];
		String[] array = randomHint.split(" ");
		
		String line = "";
		
		int j = 0;
		
		for (int i = 0; i < array.length; i++)
		{
			if (info.getWidth(line + array[i] + " ") > (int) (WINDOW_WIDTH * 0.8f) || i == array.length - 1)
			{
				randomHintLines[j] = line + array[i] + " ";
				
				j++;
				
				line = "";
			}
			else
			{
				line += array[i] + " ";
			}
		}
		
		return randomHintLines;
	}
	
	private Animation createAnimation(String directoryName) throws SlickException
	{
		Animation animation = new Animation(createImageArrayFromDirectory(SPRITE_DIR + "loading_screen/animations/" + directoryName), FPS, true);
		animation.setSpeed(0.1f);
		
		return animation;
	}

	@Override
    public void update(Input input) throws SlickException
    {
		counter += 1;
		
		if (counter > FPS * 0.25f)
		{
			counter = 0;
			
			updateRenderMessage();
		}
    }

	private void updateRenderMessage()
    {
		String message = "LOADING...";
		
	    if (renderMessage.length() == message.length())
	    {
	    	renderMessage = "";
	    }
	    
	    renderMessage += message.charAt(renderMessage.length());
    }

	@Override
    public void render() throws SlickException
    {
		String hints = "HINTS";
		Animation animation = HINTS.get(randomHint);
		
		loadingScreen.draw();
		animation.draw(boxX + (182 - animation.getWidth()) * 0.5f, boxY + (152 - animation.getHeight()) * 0.5f);
		font.drawString((WINDOW_WIDTH - font.getWidth(renderMessage)) * 0.5f, 17, renderMessage, Color.black);
		info.drawString((WINDOW_WIDTH - info.getWidth(hints)) * 0.5f, WINDOW_HEIGHT * 0.2f, hints, Color.black);
		
		int y = 350;
		
		for (int i = 0; i < randomHintLines.length; i++)
		{
			info.drawString((WINDOW_WIDTH - info.getWidth(randomHintLines[i])) * 0.5f, y, randomHintLines[i], Color.black);
			
			y += info.getHeight() * 0.9f;
		}
    }

}
