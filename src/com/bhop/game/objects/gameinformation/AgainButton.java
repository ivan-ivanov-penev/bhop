package com.bhop.game.objects.gameinformation;

import static com.bhop.game.util.GameUtils.*;

import java.awt.Font;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.timecounter.GameEndWatcher;
import com.bhop.game.states.Play;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class AgainButton extends GameEndWatcher implements GameObject, Singleton
{
	
	private final Image image;
	
	private final float x;
	
	private final float y;
	
	private final TrueTypeFont fontType;

	private AgainButton() throws SlickException
	{
		image = new Image("res/again_button/blue.png");
		
		fontType = new TrueTypeFont(new Font(FONT_TYPE, STYLE, 20), true);
		x = (WINDOW_WIDTH - image.getWidth()) / 2;
		y = WINDOW_HEIGHT / 2;
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		if (gameEnded && mouseIsOverImage(input, image, x, y) && input.isMousePressed(0))
		{
			Play.alertPlayerWantsToRestart();
		}
	}
	
	@Override
	public void render() throws SlickException
	{
		if (gameEnded)
		{
			image.draw(x, y);
			fontType.drawString(x + image.getWidth() / 7, y + image.getHeight() / 5, "Again?", BLACK);
		}
	}

}
