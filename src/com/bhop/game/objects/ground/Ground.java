package com.bhop.game.objects.ground;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.bunny.BunnyAnimation.RunSpeedBoost;
import com.bhop.game.util.GameUtils;

public class Ground
{
	
	private class GroundPiece extends GameObject
	{
		
		private static final int IMAGE_WIDTH = 256;
		
		public GroundPiece() throws SlickException
		{
			super("res/grass2.png");
			
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
			x -= GameUtils.CAMERA_SPEED * physics.getSpeedFactor();
		}
		
	}
	
	private final GroundPhysics physics;

	private final List<GroundPiece> groundPieces;
	
	public Ground() throws SlickException
	{
		physics = new GroundPhysics();
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
	
	public float getSpeedFactor()
	{
		return physics.getSpeedFactor();
	}

	public void increaseSpeed(RunSpeedBoost runSpeedBoost)
	{
//		System.out.println(GameUtils.CAMERA_SPEED * physics.getSpeedFactor());
		physics.increaseSpeedFactor(runSpeedBoost);
	}
	
	public void decreaseSpeed()
	{
//		System.out.println(GameUtils.CAMERA_SPEED * physics.getSpeedFactor());
		physics.decreaseSpeedFactor();
	}

}
