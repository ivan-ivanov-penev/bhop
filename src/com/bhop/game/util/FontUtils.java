package com.bhop.game.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 * 
 * @author Ivan Penev
 *
 */
public final class FontUtils
{
	
	public static final Color COLOR = new Color(75, 51, 28);

	public static final Color COLOR_BLACKISH = new Color(40, 30, 20);
	
	public static final String FONT_TYPE = "Snap ITC";
	
//	public static final String FONT_TYPE = "Showcard Gothic";

    public static final int STYLE = Font.PLAIN;
	
	public static void registerGameFont()
	{
		try
		{
			InputStream is =FontUtils.class.getResourceAsStream("/res/font/SNAP____.TTF");
			
			if (is != null)
			{
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
			}
			else
			{
				File fontFile = new File("res/font/SNAP____.TTF");
//				File fontFile = new File("res/font/SHOWG.TTF");
				
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static TrueTypeFont createFont(int size)
	{
		return new TrueTypeFont(new Font(FONT_TYPE, STYLE, size), true);
	}

}
