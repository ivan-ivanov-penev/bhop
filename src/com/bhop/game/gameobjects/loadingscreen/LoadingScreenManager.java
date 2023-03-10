package com.bhop.game.gameobjects.loadingscreen;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;
import static com.bhop.game.util.ImageUtils.*;

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
	
	private final SpecaialAnimation specaialAnimation;
	
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
		
		loadingScreen = createImage("loading_screen/loading_screen.png");
		font = new TrueTypeFont(new Font(FONT_TYPE, Font.BOLD, 30), true);
		info = createFont(20);
		randomHint = getRandomElement(new ArrayList<String>(HINTS.keySet()).toArray(new String[HINTS.size()]));
		randomHintLines = separateHintToLines();
		renderMessage = "";
		boxX = 230;
		boxY = 165;
		specaialAnimation = new SpecaialAnimation();
    }
	
	private void fillHints() throws SlickException
	{
//		HINTS.put("THIS HINT IS NOT VERY HELPFUL. PROBLEM? :)", createAnimationWithArguments("troll", 1f, false));
		HINTS.put("IF YOU CLICK ON THE SCRREN EXACTLY WHEN BUNNY HAS LANDED YOU WILL GET AN EXTRA SPEED BONUS!", createAnimationWithArguments("speed_bonus", 0.15f, false));
		HINTS.put("THE TIME INDEXATOR ON TOP RIGHT CORNER OF THE SCREEN SHOWS IF YOU WILL REACH THE CARROT ON TIME", createAnimationWithArguments("indexator", 0.2f, false));
		HINTS.put("IF YOU MISSED THE CARROT DON'T WORRY - A NEW ONE WILL APPEAR BASED ON THE TIME YOU GOT LEFT", createAnimationWithArguments("carrot", 1.2f, true));
		HINTS.put("THE BOOSTER WILL GRANT YOU CONSTANT TOP SPEED FOR THE NEXT 10 SECONDS", createAnimationWithArguments("booster", 1f, false));
		HINTS.put("COLLECT 30 CARROTS IN A ROW AND YOU WILL UNLOCK BONUS SKIN FOR THE BUNNY!", createAnimationWithArguments("bonus_bunny", 0.75f, false));
		HINTS.put("THE DISTANCE INDEXATOR AT THE BOTTOM OF THE SCREEN SHOWS HOW FAR ARE YOU FROM THE CARROT", createAnimationWithArguments("distance_indexator", 1f, false));
		HINTS.put("EACH TIME YOU COLLECT 300 CARROTS A NEW SPECIAL BACKGROUND WILL BE UNLOCKED", createAnimationWithArguments("special_background_" + getTimePeriod(), 1f, false));
		HINTS.put("IF YOU GET LUCKY A SPECIAL BACKGROUND WILL APPEAR", createAnimationWithArguments("special_background_" + getTimePeriod(), 1f, false));
		HINTS.put("RUN SLOWLY TO JUMP HIGHER", createAnimationWithArguments("bunny_jump_run", 1.5f, false));
		HINTS.put("THE MORE YOU JUMP THE FASTER YOU RUN", createAnimationWithArguments("jump_bunny", 1.0f, false));
//		HINTS.put("", createAnimationWithArguments("", 1f, false));
	}
	
	private Animation createAnimationWithArguments(String directoryName, float animationSpeed, boolean pingPong) throws SlickException
	{
		Animation animation = createAnimation("loading_screen/animations/" + directoryName);
		animation.setSpeed(animationSpeed);
		animation.setPingPong(pingPong);
		
		return animation;
	}
	
	private String[] separateHintToLines()
	{
		int width = info.getWidth(randomHint);
		int numberOfLines = (int) (width / (WINDOW_WIDTH * 0.8f)) + 1;
		
		String[] randomHintLines = new String[numberOfLines];
		populateArrayAccordingToScreenWidth(randomHintLines, randomHint.split(" "));
		
		return randomHintLines;
	}

	private void populateArrayAccordingToScreenWidth(String[] randomHintLines, String[] array)
    {
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
		loadingScreen.draw();
		
		Animation animation = HINTS.get(randomHint);
		animation.draw(boxX + (182 - animation.getWidth()) * 0.5f, boxY + (152 - animation.getHeight()) * 0.5f);
		
		if (randomHint.equals("THE MORE YOU JUMP THE FASTER YOU RUN") && animation.getFrame() == animation.getFrameCount() - 1 && animation.getSpeed() < 2)
		{
			animation.setSpeed(animation.getSpeed() + 0.06f);
		}
		
		String hints = "HINTS";

		info.drawString((WINDOW_WIDTH - info.getWidth(hints)) * 0.5f, WINDOW_HEIGHT * 0.2f, hints, Color.black);
		font.drawString((WINDOW_WIDTH - font.getWidth(renderMessage)) * 0.5f, 17, renderMessage, Color.black);
		
		drawText();
		specaialAnimation.attemptToRenderHead();
    }
	
	private void drawText()
    {
	    int y = 350;
		
		for (int i = 0; i < randomHintLines.length; i++)
		{
			if (randomHintLines[i] != null)
			{
				info.drawString((WINDOW_WIDTH - info.getWidth(randomHintLines[i])) * 0.5f, y, randomHintLines[i], Color.black);
			
				y += info.getHeight() * 0.9f;
			}
		}
    }
	
	private class SpecaialAnimation
	{
		private final boolean animationIsDistanceIndexator;
		
		private final boolean animationIsBooster;
		
		private final Image bunnyIcon;
		
		private float x;
		
		public SpecaialAnimation() throws SlickException
        {
			animationIsDistanceIndexator = randomHint.equals("THE DISTANCE INDEXATOR AT THE BOTTOM OF THE SCREEN SHOWS HOW FAR ARE YOU FROM THE CARROT");
			animationIsBooster = randomHint.equals("THE BOOSTER WILL GRANT YOU CONSTANT TOP SPEED FOR THE NEXT 10 SECONDS");
			bunnyIcon = createImage("distance_indexator/bunny_icon1.png");
			x = boxX + 20;
        }
		
		private void attemptToRenderHead()
		{
			if (animationIsDistanceIndexator)
			{
				bunnyIcon.draw(x, boxY + 61, 0.9f);
				
				incrementX();
			}
			
			if (animationIsBooster)
			{
				for (int i = 0; i < HINTS.get(randomHint).getFrameCount(); i++)
				{
					HINTS.get(randomHint).getImage(i).rotate(1);
				}
			}
		}

		private void incrementX()
        {
	        x += 0.5f;
	        
	        if (x > boxX + 120)
	        {
	        	x = boxX + 20;
	        }
        }
	}

}
