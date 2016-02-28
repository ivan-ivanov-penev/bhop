package com.bhop.game.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public final class FontUtils
{
	
	public static final String FONT_TYPE = "Snap ITC";

    public static final int STYLE = Font.PLAIN;
	
	public static void registerGameFont()
	{
		try
		{
			File fontFile = new File("res/font/SNAP____.TTF");
			
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
