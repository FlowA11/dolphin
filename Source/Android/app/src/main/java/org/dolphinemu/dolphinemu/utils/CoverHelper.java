package org.dolphinemu.dolphinemu.utils;

import android.graphics.Bitmap;

import org.dolphinemu.dolphinemu.model.GameFile;
import org.dolphinemu.dolphinemu.ui.platform.Platform;

import java.io.FileOutputStream;

public final class CoverHelper
{
	private static String baseUrl = "https://art.gametdb.com/wii/cover/%s/%s.png";

	public static String buildGameTDBUrl(GameFile game, String region)
	{
		String gameId = game.getGameId();
		if(game.getPlatform() == 2) // WiiWare
			gameId = gameId.substring(0,4);
		return String.format(baseUrl, region, gameId);
	}

	public static String getRegion(GameFile game)
	{
		String region;
		switch(game.getRegion())
		{
			case 0: // NTSC_J
				region = "JA";
				break;
			case 1: // NTSC_U
				region = "US";
				break;
			case 4: // NTSC_K
				region = "KO";
				break;
			case 2: // PAL
				switch (game.getCountry())
				{
					case 2: // German
						region = "DE";
						break;
					case 3: // French
						region = "FR";
						break;
					case 4: // Spanish
						region = "ES";
						break;
					case 5: // Italian
						region = "IT";
						break;
					case 6: // Dutch
						region = "NL";
						break;
					case 1: // English
					default:
						region = "EN";
						break;
				}
				break;
			case 3: // Unknown
			default:
				region = "EN";
				break;
		}
		return region;
	}

	public static void saveCover(Bitmap cover, String path)
	{
		try
		{
			FileOutputStream out = new FileOutputStream(path);
			cover.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.close();
		}
		catch (Exception e)
		{
			// Do nothing
		}
	}
}