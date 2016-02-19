package com.bhop.game.objects.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bhop.game.objects.GameObject;
import com.bhop.game.objects.carrot.CarrotGenerator;
import com.bhop.game.util.GameUtils;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonManager;

public class LogGenerator implements GameObject, Singleton
{

	private final List<Log> logs;

	private final Random random;
	
	private final CarrotGenerator carrotGenerator;

	private LogGenerator()
	{
		random = new Random();
		logs = new ArrayList<Log>();
		carrotGenerator = SingletonManager.getSingleton(CarrotGenerator.class);
	}

	public void manage() throws SlickException
	{
		attemptToCreateLog();
		clearLogsOutsideScrren();
	}

	private void attemptToCreateLog() throws SlickException
	{
		if (random.nextInt(40) == 1)
		{
			if (logs.isEmpty())
			{
				logs.add(new Log(carrotGenerator.getCarrot()));
			}
			else if (logs.get(logs.size() - 1).getX() < GameUtils.WINDOW_WIDTH - 400)
			{
				logs.add(new Log(carrotGenerator.getCarrot()));
			}
		}
	}

	private void clearLogsOutsideScrren()
	{
		if (!logs.isEmpty() && logs.get(0).getX() < -GameUtils.WINDOW_WIDTH * 2)
		{
			logs.remove(0);
		}
	}

	public List<Log> getAllLogs()
	{
		return logs;
	}

	@Override
	public void render() throws SlickException
	{
		for (Log log : logs)
		{
			log.render();
		}
	}
	
	@Override
	public void update(Input input) throws SlickException
	{
		manage();
		
		for (Log log : logs)
		{
			log.update(null);
		}
	}

}
