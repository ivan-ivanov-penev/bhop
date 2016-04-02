package com.bhop.game.bonusbackground;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.bhop.game.gameobjects.gameinformation.BonusBackgroundInfoProvider;
import com.bhop.game.util.UserInfoProvider;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BonusBackgroundUnlocker implements Singleton
{
	private static final String BACKGROUND_INFO_PATH = UserInfoProvider.INFO_TEMP_DIR + "/bonus_background.ser";

	public static final int MAX_BACKGROUNDS_TO_UNLOCK = 2;
	
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
		InputStream inputStream = new FileInputStream(BACKGROUND_INFO_PATH);
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		bonusLock = (BonusBackgroundLock) objectInputStream.readObject();
		
		objectInputStream.close();
	}
	
	public void alertBunnyHasPickedUpCarrot()
	{
		bonusLock.incrementTotalCarrots();
		
		if (bonusLock.getTotalCarrots() == 300 && bonusLock.getNumberOfSpecialBackgroundsUnlock() < MAX_BACKGROUNDS_TO_UNLOCK)
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
		OutputStream fos = new FileOutputStream(BACKGROUND_INFO_PATH);
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(bonusLock);
		oos.close();
	}
	
	public int getNumberOfUnlockedBonusBackgrounds()
	{
		return bonusLock.getNumberOfSpecialBackgroundsUnlock();
	}

}
