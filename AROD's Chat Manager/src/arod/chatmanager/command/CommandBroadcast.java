package arod.chatmanager.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import arod.chatmanager.Core;
import arod.chatmanager.utility.Helper;

public class CommandBroadcast implements CommandExecutor
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
		
		if (command.getName().equalsIgnoreCase("broadcast"))
		{
			if (args.length == 1)
			{
				if (player.hasPermission("chatmanager.broadcast"))
				{
					Bukkit.broadcastMessage(Helper.format(Core.getInstance().getConfig().getString("BroadcastPrefix") + args[0]));
					return true;
				}
				else
				{
					player.sendMessage(Helper.format(Core.getInstance().getConfig().getString("NoPermission")));
					return true;
				}
			}
			else
			{
				player.sendMessage(Helper.format(Core.getInstance().getConfig().getString("Prefix") + "/broadcast <message>"));
				return true;
			}
		}
		return false;
	}

}
