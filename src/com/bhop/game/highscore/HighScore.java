package com.bhop.game.highscore;

import java.io.Serializable;

public class HighScore implements Serializable
{

	private static final long serialVersionUID = 2L;
	
	private int highScore;
	
	int getHighScore()
	{
		return highScore;
	}
	
	void setHighScore(int highScore)
	{
		this.highScore = highScore;
	}

}
