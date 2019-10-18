package arod.chatmanager.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import arod.chatmanager.Core;
import arod.chatmanager.utility.Helper;

public class CommandStaffChat implements CommandExecutor
{

	public static List<UUID> staff = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("You must be a player to execute this command");
			return true;
		}

		Player player = (Player) sender;

		if (command.getName().equalsIgnoreCase("staffchat"))
		{
			if (player.hasPermission("chatmanager.staffchat"))
			{
				if (staff.contains(player.getUniqueId()))
				{
					staff.remove(player.getUniqueId());
					player.sendMessage(Helper.format(Core.getInstance().getConfig().getString("Messages.StaffChatDisabled")));
					return true;
				} 
				else
				{
					staff.add(player.getUniqueId());
					player.sendMessage(Helper.format(Core.getInstance().getConfig().getString("Messages.StaffChatEnabled")));
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
