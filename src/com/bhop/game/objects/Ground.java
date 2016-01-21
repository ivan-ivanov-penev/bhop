package com.bhop.game.objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import com.bhop.game.util.GameObject;
import com.bhop.game.util.GameUtils;

public class Ground
{
	
	private class GroundPiece extends GameObject
	{
		
		private static final int IMAGE_WIDTH = 256;
		
		public GroundPiece() throws SlickException
		{
			super("res/grass.png");
			
			x = getLastGroundPieceX() + image.getWidth();
			y = GameUtils.WINDOW_HEIGHT - image.getHeight();
		}
		
		private float getLastGroundPieceX()
		{
			if (groundPieces.isEmpty())
			{
				return -image.getWidth();
			}
			
			return groundPieces.get(groundPieces.size() - 1).getX();
		}
		
		@Override
		public void move()
		{
			if (speedFactor < 1)
			{
				speedFactor = 1;
			}
			
			x -= GameUtils.CAMERA_SPEED * speedFactor;
		}
		
	}
	
	private final List<GroundPiece> groundPieces;
	
	private float speedFactor;
	
	public Ground() throws SlickException
	{
		speedFactor = 1;
		groundPieces = new ArrayList<>();
		
		for (int i = 0; i < GameUtils.WINDOW_WIDTH / GroundPiece.IMAGE_WIDTH + 2; i++)
		{
			groundPieces.add(new GroundPiece());
		}
	}
	
	public void draw()
	{
		for (GroundPiece groundPiece : groundPieces)
		{
			groundPiece.draw();
		}
	}
	
	public void move() throws SlickException
	{
		manageGroundPieces();
		
		for (GroundPiece groundPiece : groundPieces)
		{
			groundPiece.move();
		}
	}
	
	private void manageGroundPieces() throws SlickException
	{
		if (groundPieces.get(groundPieces.size() - 1).getX() <= GameUtils.WINDOW_WIDTH - GroundPiece.IMAGE_WIDTH / 2)
		{
			groundPieces.remove(0);
			groundPieces.add(new GroundPiece());
		}
	}
	
	public void increaseSpeedFactor()
	{
		speedFactor += speedFactor >= 6 ? 0 : 0.4;
	}
	
	public void decreaseSpeedFactor()
	{
		speedFactor -= 0.05;
	}

}
