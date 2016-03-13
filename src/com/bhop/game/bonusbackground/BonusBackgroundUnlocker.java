package com.bhop.game.bonusbackground;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bhop.game.gameobjects.gameinformation.BonusBackgroundInfoProvider;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BonusBackgroundUnlocker implements Singleton
{
	private static final int MAX_BACKGROUNDS_TO_UNLOCK = 5;
	
	private BonusBackgroundLock bonusLock;
	
	private BonusBackgroundUnlocker()
	{
		loadBonusLock();
	}
	
	private void loadBonusLock()
	{
		try
		{
			readFromFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void readFromFile() throws Exception
	{
		FileInputStream inputStream = new FileInputStream("res/info/bonus_background.ser");
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		bonusLock = (BonusBackgroundLock) objectInputStream.readObject();
		
		objectInputStream.close();
	}
	
	public void alertBunnyHasPickedUpCarrot()
	{
		bonusLock.incrementTotalCarrots();
		
		if (bonusLock.getTotalCarrots() == 500 && bonusLock.getNumberOfSpecialBackgroundsUnlock() < MAX_BACKGROUNDS_TO_UNLOCK)
		{
			bonusLock.unlockedNewSpecialBackgrounds();
			
			BonusBackgroundInfoProvider.alertBonusBackroundIsUnlocked();
		}
		
		writeSafelyToFile();
	}

	private void writeSafelyToFile()
	{
		try
		{
			writeToFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void writeToFile() throws Exception
	{
		FileOutputStream fos = new FileOutputStream("res/info/bonus_background.ser");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(bonusLock);
		oos.close();
	}
	
	public int getNumberOfUnlockedBonusBackgrounds()
	{
		return bonusLock.getNumberOfSpecialBackgroundsUnlock();
	}

}
