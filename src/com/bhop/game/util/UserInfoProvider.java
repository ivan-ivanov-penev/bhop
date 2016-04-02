package com.bhop.game.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.bhop.game.bonusbackground.BonusBackgroundLock;
import com.bhop.game.bonuscolor.BonusLock;
import com.bhop.game.highscore.HighScore;
import com.bhop.game.sound.SoundInfoManager.SoundInfo;

/**
 * 
 * @author Ivan Penev
 *
 */
public final class UserInfoProvider
{
	
	public static final String INFO_TEMP_DIR = System.getProperty("java.io.tmpdir") + NativeUtils.TEMP_DIR_NAME;

	private UserInfoProvider() {}
	
	public static void createInfoSerFilesIfNonExist() throws Exception
	{
		File[] infoCandidates = new File(INFO_TEMP_DIR).listFiles();
		
		if (!checkIfInfoFilesExist(infoCandidates))
		{
			createInfoSerFiles();
		}
	}

	private static boolean checkIfInfoFilesExist(File[] infoCandidates)
	{
		Set<String> expectedFileNames = createExpectedInfoFilenames();
		
		int numberOfSerFiles = 0;
		
		for (File candidate : infoCandidates)
		{
			if (candidate.getName().endsWith(".ser"))
			{
				numberOfSerFiles += 1;
				
				if (!isCandidateExisting(candidate, expectedFileNames))
				{
					return false;
				}
			}
		}
		
		return expectedFileNames.size() == numberOfSerFiles;
	}

	private static Set<String> createExpectedInfoFilenames()
	{
		Set<String> expectedFileNames = new HashSet<String>();
		expectedFileNames.add("highscore.ser");
		expectedFileNames.add("bonus_background.ser");
		expectedFileNames.add("bonus.ser");
		expectedFileNames.add("sound_info.ser");
		
		return expectedFileNames;
	}
	
	private static boolean isCandidateExisting(File candidate, Set<String> expectedFileNames)
	{
		for (String expectedFileName : expectedFileNames)
		{
			if (candidate.getName().contains(expectedFileName))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private static void createInfoSerFiles()
	{
		try
		{
			write(createSerializablesMap());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static Map<String, Serializable> createSerializablesMap()
	{
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("highscore.ser", new HighScore());
		map.put("bonus_background.ser", new BonusBackgroundLock());
		map.put("bonus.ser", new BonusLock());
		map.put("sound_info.ser", new SoundInfo());
		
		return map;
	}
	
	private static void write(Map<String, Serializable> map) throws Exception
	{
		for (String fileName : map.keySet())
		{
			OutputStream outputStream = new FileOutputStream(INFO_TEMP_DIR + "/" + fileName);
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(map.get(fileName));
			objectOutputStream.close();
		}
	}

}
