package com.bhop.game.objects.ground;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.BasicGameObject;
import com.bhop.game.objects.GameObject;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class Ground implements GameObject, Singleton
{
	
	private class GroundPiece extends BasicGameObject
	{
		
		private static final String IMAGE_PATH = "res/ground/night.png";
		
		public GroundPiece() throws SlickException
		{
			super(IMAGE_PATH);
			
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
		imageWidth = new Image(GroundPiece.IMAGE_PATH).getWidth();
		groundPieces = new ArrayList<>();
		
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
		if (groundPieces.get(groundPieces.size() - 1).getX() <= GameUtils.WINDOW_WIDTH - imageWidth / 2)
		{
			groundPieces.remove(0);
			groundPieces.add(new GroundPiece());
		}
	}

}
