package com.bhop.game.bonuscolor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bhop.game.util.singleton.Singleton;
import com.bhop.game.util.singleton.SingletonClass;

@SingletonClass
public class BonusColorUnlocker implements Singleton
{
	
	private BonusLock bonusLock;
	
	private BonusColorUnlocker()
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
		FileInputStream inputStream = new FileInputStream("info/bonus.ser");
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
		FileOutputStream fos = new FileOutputStream("info/bonus.ser");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(bonusLock);
		oos.close();
	}

}
