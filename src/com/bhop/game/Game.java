package com.bhop.game;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bhop.game.states.LoadingScreen;
import com.bhop.game.states.Menu;
import com.bhop.game.states.Play;
import com.bhop.game.util.FontUtils;
import com.bhop.game.util.SoundUtils;

import static com.bhop.game.util.GameUtils.*;

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
			setNativesLibrary();
			
			runGame();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void setNativesLibrary()
	{
		System.setProperty("org.lwjgl.librarypath", new File("lib/natives/all").getAbsolutePath());

		/*
		String pathname = "/lib/natives/all/";
		System.out.println(">>>Start");
		NativeUtils.loadLibraryFromJar(pathname + "OpenAL32.dll");
		System.out.println("OpenAL32.dll");
		NativeUtils.loadLibraryFromJar(pathname + "OpenAL64.dll");
		System.out.println("OpenAL64.dll");
		NativeUtils.loadLibraryFromJar(pathname + "jinput-dx8_64.dll");
		System.out.println("jinput-dx8_64.dll");
		NativeUtils.loadLibraryFromJar(pathname + "jinput-dx8.dll");
		System.out.println("jinput-dx8.dll");
		NativeUtils.loadLibraryFromJar(pathname + "jinput-raw_64.dll");
		System.out.println("jinput-raw_64.dll");
		NativeUtils.loadLibraryFromJar(pathname + "jinput-raw.dll");
		System.out.println("jinput-raw.dll");
		NativeUtils.loadLibraryFromJar(pathname + "libjinput-linux.so");
		System.out.println("libjinput-linux.so");
		NativeUtils.loadLibraryFromJar(pathname + "libjinput-linux64.so");
		System.out.println("libjinput-linux64.so");
		NativeUtils.loadLibraryFromJar(pathname + "libjinput-osx.dylib");
		System.out.println("libjinput-osx.dylib");
		NativeUtils.loadLibraryFromJar(pathname + "liblwjgl.dylib");
		System.out.println("liblwjgl.dylib");
		NativeUtils.loadLibraryFromJar(pathname + "liblwjgl.so");
		System.out.println("liblwjgl.so");
		NativeUtils.loadLibraryFromJar(pathname + "liblwjgl64.so");
		System.out.println("liblwjgl64.so");
		NativeUtils.loadLibraryFromJar(pathname + "libopenal.so");
		System.out.println("libopenal.so");
		NativeUtils.loadLibraryFromJar(pathname + "libopenal64.so");
		System.out.println("libopenal64.so");
		NativeUtils.loadLibraryFromJar(pathname + "lwjgl.dll");
		System.out.println("lwjgl.dll");
		NativeUtils.loadLibraryFromJar(pathname + "lwjgl64.dll");
		System.out.println("lwjgl64.dll");
		NativeUtils.loadLibraryFromJar(pathname + "openal.dylib");
		System.out.println("openal.dylib");
		
		System.setProperty("org.lwjgl.librarypath", System.getProperty("java.io.tmpdir") + NativeUtils.TEMP_DIR);
		*/
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
