package com.bhop.game.gameobjects.bunny.dummy;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.gameobjects.GameObject;
import com.bhop.game.gameobjects.coloroptions.ColorOption;
import com.bhop.game.gameobjects.coloroptions.ColorOptionManager;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonManager;

public class DummyBunnyManager implements GameObject, Singleton
{
	
	private final List<DummyBunny> dummyBunnies;
	
	private DummyBunnyManager() throws SlickException
    {
		dummyBunnies = new ArrayList<DummyBunny>();
		
		for (ColorOption colorOption : SingletonManager.getSingleton(ColorOptionManager.class).getColorBloks())
		{
			dummyBunnies.add(new DummyBunny(colorOption));
		}
    }

	@Override
    public void update(Input input) throws SlickException
    {
		for (DummyBunny dummyBunny : dummyBunnies)
        {
	        dummyBunny.update(input);
        }
    }

	@Override
    public void render() throws SlickException
    {
		for (DummyBunny dummyBunny : dummyBunnies)
        {
	        dummyBunny.render();
        }
    }

}
