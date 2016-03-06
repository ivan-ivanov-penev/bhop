package com.bhop.game.gameobjects.gameinformation;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.gameobjects.timecounter.GameEndWatcher.*;
import static com.bhop.game.util.FontUtils.*;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.states.Play;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;
import com.bhop.game.util.singleton.SingletonManager;

@SingletonClass
public class AgainButton implements GameObject, Singleton
{
	
	private final Image image;
	
	private final float x;
	
	private final float y;
	
	private final TrueTypeFont fontType;

	private AgainButton() throws SlickException
	{
		image = new Image(SPRITE_DIR + "again_button/sign.png");
		
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 20), true);
		
		GameInformation gameInformation = SingletonManager.getSingleton(GameInformation.class);
		
		x = (WINDOW_WIDTH - image.getWidth()) / 2;
		y = gameInformation.getY() + gameInformation.getImageWidth() * 0.63f;
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		if (isGameEnd() && mouseIsOverImage(input, image, x, y - 40) && input.isMousePressed(0))
		{
			Play.alertPlayerWantsToRestart();
		}
	}
	
	@Override
	public void render() throws SlickException
	{
		if (isGameEnd())
		{
			String message = "Again?";
			
			image.draw(x, y - 40);
			fontType.drawString(x + (image.getWidth() - fontType.getWidth(message)) / 2, y + (80 - fontType.getHeight()) / 2, message, Color.red);
		}
	}

}
