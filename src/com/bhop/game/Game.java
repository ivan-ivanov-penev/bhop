package com.bhop.game;

import static com.bhop.game.util.GameUtils.*;
import static com.bhop.game.util.ImageUtils.*;
import static com.bhop.game.util.UserInfoProvider.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.states.LoadingScreen;
import com.bhop.game.states.Menu;
import com.bhop.game.states.Play;
import com.bhop.game.util.FontUtils;
import com.bhop.game.util.NativeUtils;
import com.bhop.game.util.SoundUtils;

/**
 * 
 * @author Ivan Penev
 *
 */
public class Game extends StateBasedGame
{
	
	public Game()
	{
		super(GAME_NAME);
		
		addState(new LoadingScreen());
		addState(new Menu());
		addState(new Play());
	}

	public static void main(String[] args)
	{
		try
		{
			createInfoSerFilesIfNonExist();
			
			setNativesLibrary();
			
			runGame();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void setNativesLibrary() throws Exception
	{
		try
		{
			String pathname = "/lib/natives/all/";
			
			NativeUtils.loadLibraryFromJar(pathname + "OpenAL32.dll");
			NativeUtils.loadLibraryFromJar(pathname + "OpenAL64.dll");
			NativeUtils.loadLibraryFromJar(pathname + "jinput-dx8_64.dll");
			NativeUtils.loadLibraryFromJar(pathname + "jinput-dx8.dll");
			NativeUtils.loadLibraryFromJar(pathname + "jinput-raw_64.dll");
			NativeUtils.loadLibraryFromJar(pathname + "jinput-raw.dll");
			NativeUtils.loadLibraryFromJar(pathname + "libjinput-linux.so");
			NativeUtils.loadLibraryFromJar(pathname + "libjinput-linux64.so");
			NativeUtils.loadLibraryFromJar(pathname + "libjinput-osx.dylib");
			NativeUtils.loadLibraryFromJar(pathname + "liblwjgl.dylib");
			NativeUtils.loadLibraryFromJar(pathname + "liblwjgl.so");
			NativeUtils.loadLibraryFromJar(pathname + "liblwjgl64.so");
			NativeUtils.loadLibraryFromJar(pathname + "libopenal.so");
			NativeUtils.loadLibraryFromJar(pathname + "libopenal64.so");
			NativeUtils.loadLibraryFromJar(pathname + "lwjgl.dll");
			NativeUtils.loadLibraryFromJar(pathname + "lwjgl64.dll");
			NativeUtils.loadLibraryFromJar(pathname + "openal.dylib");
		
			System.setProperty("org.lwjgl.librarypath", System.getProperty("java.io.tmpdir") + NativeUtils.TEMP_DIR_NAME);
		}
		catch (Exception e)
		{
			System.setProperty("org.lwjgl.librarypath", "E:/Game_Projects/Java/BHop/lib/natives/all");
		}
	}
	
	public static void runGame() throws Exception
	{
		FontUtils.registerGameFont();
		
        AppGameContainer appGameContainer = new AppGameContainer(new Game());
		appGameContainer.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		appGameContainer.setIcon(SPRITE_DIR + "game_icon.png");
		appGameContainer.setShowFPS(false);
		appGameContainer.setVSync(true);
		appGameContainer.setTargetFrameRate(FPS);
		appGameContainer.setMaximumLogicUpdateInterval(10);
		appGameContainer.setMinimumLogicUpdateInterval(0);
		appGameContainer.start();
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException
	{
		SoundUtils.loadSounds();
		
//		gameContainer.setMouseCursor(SPRITE_DIR + "mouse_cursor/mouse2.png", 0, 0);
		getState(LoadingScreen.ID).init(gameContainer, this);
		enterState(LoadingScreen.ID);
	}

}
