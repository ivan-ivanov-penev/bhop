package com.bhop.game.highscore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.bhop.game.util.UserInfoProvider;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class HighScoreManager implements Singleton
{
	
	private static final String HIGHSCORE_PATH = UserInfoProvider.INFO_TEMP_DIR +  "/highscore.ser";

	private HighScore highScore;
	
	private final int oldHighScore;
	
	private HighScoreManager()
	{
		loadHighScore();
		
		oldHighScore = highScore.getHighScore();
	}
	
	public int getHighScore()
    {
	    return oldHighScore;
    }
	
	private void loadHighScore()
	{
		try
		{
			readFromFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void readFromFile() throws Exception
	{
		InputStream inputStream = new FileInputStream(HIGHSCORE_PATH);
		
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
		highScore = (HighScore) objectInputStream.readObject();
		
		objectInputStream.close();
	}
	
	public void rewriteHighScoreIfGreater(int currentHighScore)
	{
		if (highScore.getHighScore() < currentHighScore)
		{
			highScore.setHighScore(currentHighScore);
			
			writeNewHighScore();
		}
	}

	private void writeNewHighScore()
	{
		try
		{
			writeToFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void writeToFile() throws Exception
	{
		OutputStream outputStream = new FileOutputStream(HIGHSCORE_PATH);
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(highScore);
		objectOutputStream.close();
	}

}
