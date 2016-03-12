package com.bhop.game.gameobjects.loadingscreen;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.FontUtils.*;

import java.awt.Font;

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
	
	private final Image loadingScreen;
	
	private final TrueTypeFont font;
	
	private String renderMessage;
	
	private int counter;
	
	private LoadingScreenManager() throws SlickException
    {
		loadingScreen = new Image(SPRITE_DIR + "loading_screen/loading_screen.png");
		font = new TrueTypeFont(new Font(FONT_TYPE, Font.BOLD, 30), true);
		renderMessage = "";
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
		font.drawString((WINDOW_WIDTH - font.getWidth(renderMessage)) * 0.5f, 20, renderMessage, Color.black);
    }

}
