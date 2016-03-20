package com.bhop.game.gameobjects.ground;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.BasicGameObject;
import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

import static com.bhop.game.util.GameUtils.*;

@SingletonClass
public class Ground implements GameObject, Singleton
{
	
	private class GroundPiece extends BasicGameObject
	{

		public GroundPiece(float x) throws SlickException
		{
			super("ground/" + getTimePeriod() + ".png");
			
			this.x = x;
			this.y = GameUtils.WINDOW_HEIGHT - image.getHeight();
		}
		
		public GroundPiece() throws SlickException
		{
			super("ground/" + getTimePeriod() + ".png");
			
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
		
	}
	
	private final List<GroundPiece> groundPieces;
	
	private final int imageWidth;
	
	private Ground() throws SlickException
	{
		groundPieces = new ArrayList<>();
		imageWidth = new GroundPiece().getImageWidth();
		
		for (int i = 0; i < GameUtils.WINDOW_WIDTH / imageWidth + 5; i++)
		{
			groundPieces.add(new GroundPiece());
		}
	}
	
	@Override
	public void render() throws SlickException
	{
		for (GroundPiece groundPiece : groundPieces)
		{
			groundPiece.render();
		}
	}

	@Override
	public void update(Input input) throws SlickException
	{
		manageGroundPieces();
		
		for (GroundPiece groundPiece : groundPieces)
		{
			groundPiece.update(input);
		}
	}
	
	private void manageGroundPieces() throws SlickException
	{
		attemptToAddPieceIfPlayerIsHit();
		
		if (groundPieces.get(groundPieces.size() - 1).getX() <= GameUtils.WINDOW_WIDTH - imageWidth / 2)
		{
			groundPieces.remove(0);
			groundPieces.add(new GroundPiece());
			
		}
	}
	
	private void attemptToAddPieceIfPlayerIsHit() throws SlickException
	{
		if (groundPieces.get(0).getX() >= 0)
		{
			groundPieces.remove(groundPieces.size() - 1);
			groundPieces.add(0, new GroundPiece(-imageWidth + 2));
		}
	}

}
