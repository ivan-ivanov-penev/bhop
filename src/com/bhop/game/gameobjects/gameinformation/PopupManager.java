package com.bhop.game.gameobjects.gameinformation;

import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.SlickException;

public class PopupManager
{
	
	private final Queue<BasicPopup> popups;
	
	private BasicPopup popupToRender;
	
	PopupManager()
    {
		popups = new LinkedList<BasicPopup>();
    }

    void update() throws SlickException
    {
		if (popupToRender == null || popupToRender.isFinished())
		{
			popupToRender = popups.poll();
		}
		
		if (popupToRender != null)
		{
			popupToRender.attemptPopup();
		}
    }
    
    void addToQueue(BasicPopup popup)
    {
    	if (!popups.contains(popup))
    	{
    		popups.add(popup);
    	}
    }

}
