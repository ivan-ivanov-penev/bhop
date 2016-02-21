package com.bhop.game.highscore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class HighScoreManager implements Singleton
{
	
	private HighScore highScore;
	
	private HighScoreManager()
	{
		loadHighScore();
	}
	
	public int getHighScore()
	{
		return highScore.getHighScore();
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
		FileInputStream inputStream = new FileInputStream("info/highscore.ser");
		
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
		highScore = (HighScore) objectInputStream.readObject();
		
		objectInputStream.close();
	}
	
	public void rewriteHighScoreIfGreater(int currentHighScore)
	{
		if (getHighScore() < currentHighScore)
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
		FileOutputStream outputStream = new FileOutputStream("info/highscore.ser");
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(highScore);
		objectOutputStream.close();
	}

}
