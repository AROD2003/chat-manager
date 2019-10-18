package arod.chatmanager.utility;

import net.md_5.bungee.api.ChatColor;

public class Helper
{

	public static String format(String message)
	{
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
