package arod.chatmanager.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import arod.chatmanager.Core;
import arod.chatmanager.utility.Helper;

public class CommandMuteChat implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("You must be a player to execute this command");
			return true;
		}

		Player player = (Player) sender;

		if (command.getName().equalsIgnoreCase("mutechat"))
		{
			if (player.hasPermission("chatmanager.mutechat"))
			{
				if (Core.getInstance().chat)
				{
					Core.getInstance().chat = false;
					if (Core.getInstance().getConfig().getBoolean("BroadcastMessage"))
					{
						Bukkit.broadcastMessage(Helper.format(Core.getInstance().getConfig().getString("Messages.ChatMuted").replace("%player%", player.getName())));
						return true;
					}
					return true;
				}
				else if (Core.getInstance().chat == false)
				{
					Core.getInstance().chat = true;
					Bukkit.broadcastMessage(Helper.format(Core.getInstance().getConfig().getString("Messages.ChatUnmuted").replace("%player%", player.getName())));
					return true;
				} 
				else
				{
					return true;
				}
			}
			else
			{
				player.sendMessage(Helper.format(Core.getInstance().getConfig().getString("NoPermission")));
				return true;
			}
		}
		return false;
	}

}
