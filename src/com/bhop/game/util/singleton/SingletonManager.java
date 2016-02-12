package com.bhop.game.util.singleton;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public final class SingletonManager
{
	
	private static final Map<Class<?>, Singleton> SINGLETONS = new HashMap<Class<?>, Singleton>();
	
	private SingletonManager() {}
	
	@SuppressWarnings("unchecked")
    public static <T extends Singleton>T createSingleotnIfMissingInMap(Class<T> clazz) throws Exception
	{
		if ((T) SINGLETONS.get(clazz) == null)
		{
			Constructor<T> constructor =  clazz.getDeclaredConstructor();
			
			constructor.setAccessible(true);
			
			SINGLETONS.put(clazz, constructor.newInstance());
		}
		
		return (T) SINGLETONS.get(clazz);
	}
	
	public static <T extends Singleton>T getSingleton(Class<T> clazz)
	{
		try
        {
	        return createSingleotnIfMissingInMap(clazz);
        }
        catch (Exception e)
        {
	        e.printStackTrace();
	        
	        return null;
        }
	}
	
	public abstract static class Singleton
	{
		
		protected Singleton(Class<?> clazz)
		{
			SINGLETONS.put(clazz, this);
		}
		
	}
}
