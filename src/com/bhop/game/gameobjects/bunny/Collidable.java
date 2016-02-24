package com.bhop.game.gameobjects.bunny;

import java.util.Set;

import com.bhop.game.gameobjects.PixelLocation;

public interface Collidable
{
	
	public Set<PixelLocation> getImagePixelLocations();
	
	public float getX();
	
	public float getY();
	
	public int getImageWidth();

	public int getImageHeight();

}
