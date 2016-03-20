package com.bhop.game.sound;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.bhop.game.util.UserInfoProvider;

public class SoundInfoManager
{
	
	private static final String SOUND_INFO_PATH = UserInfoProvider.INFO_TEMP_DIR +  "/sound_info.ser";
	
	private SoundInfo soundInfo;
	
	SoundInfoManager()
	{
		loadSoundInfo();
	}
	
	private void loadSoundInfo()
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
		InputStream inputStream = new FileInputStream(SOUND_INFO_PATH);
		
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
		soundInfo = (SoundInfo) objectInputStream.readObject();
		
		objectInputStream.close();
	}

	void writeSoundInformation(boolean soundEnabled)
	{
		try
		{
			soundInfo.setSoundEnabled(soundEnabled);
			
			writeToFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void writeToFile() throws Exception
	{
		OutputStream outputStream = new FileOutputStream(SOUND_INFO_PATH);
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(soundInfo);
		objectOutputStream.close();
	}
	
	boolean isSoundEnabled()
	{
		return soundInfo.isSoundEnabled();
	}
	
	public static class SoundInfo implements Serializable
	{

		private static final long serialVersionUID = 3L;
		
		private boolean soundEnabled;

		private boolean isSoundEnabled()
		{
			return soundEnabled;
		}

		private void setSoundEnabled(boolean soundEnabled)
		{
			this.soundEnabled = soundEnabled;
		}
		
	}

}
