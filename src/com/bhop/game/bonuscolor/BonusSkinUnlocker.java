package com.bhop.game.bonuscolor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.bhop.game.util.UserInfoProvider;
import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BonusSkinUnlocker implements Singleton
{
	
	private static final String SKIN_INFO_PATH = UserInfoProvider.INFO_TEMP_DIR + "/bonus.ser";
	
	private BonusLock bonusLock;
	
	private BonusSkinUnlocker()
	{
		loadBonusLock();
	}
	
	public boolean playerHasUnlockedBonus()
	{
		return bonusLock.isBonusUnlocked();
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
		InputStream inputStream = new FileInputStream(SKIN_INFO_PATH);
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		bonusLock = (BonusLock) objectInputStream.readObject();
		
		objectInputStream.close();
	}
	
	public void unlockBonus()
	{
		bonusLock.unlockBonus();
		
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
		OutputStream fos = new FileOutputStream(SKIN_INFO_PATH);
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(bonusLock);
		oos.close();
	}

}
