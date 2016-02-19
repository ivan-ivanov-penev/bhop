package com.bhop.game.objects.bunny;

import java.util.Set;

import com.bhop.game.objects.PixelLocation;

public interface Collidable
{
	
	public Set<PixelLocation> getImagePixelLocations();
	
	public float getX();
	
	public float getY();
	
	public int getImageWidth();

	public int getImageHeight();

}
