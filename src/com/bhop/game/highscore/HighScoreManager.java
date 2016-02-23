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
		FileInputStream inputStream = new FileInputStream("res/info/highscore.ser");
		
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
		FileOutputStream outputStream = new FileOutputStream("res/info/highscore.ser");
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(highScore);
		objectOutputStream.close();
	}
	
	// TODO: Delete this method
	public static void main(String[] args) throws Exception
    {
		FileOutputStream outputStream = new FileOutputStream("res/info/highscore.ser");
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		HighScore obj = new HighScore();
		obj.setHighScore(2);
		objectOutputStream.writeObject(obj);
		objectOutputStream.close();
    }

}
