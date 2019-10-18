package arod.chatmanager.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import arod.chatmanager.Core;
import arod.chatmanager.utility.Helper;

public class CommandAutoBroadcast implements CommandExecutor
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
		
		if (command.getName().equalsIgnoreCase("autobroadcast"))
		{
			if (args.length == 0)
			{
				player.sendMessage(Helper.format("&7/autobroadcast add"));
				player.sendMessage(Helper.format("&7/autobroadcast list"));
				player.sendMessage(Helper.format("&7/autobroadcast help"));
				return true;
			}
			else if (args.length == 2)
			{
				if (args[0].equalsIgnoreCase("add"))
				{
					List<String> broadcasts = Core.getInstance().getConfig().getStringList("AutobroadcastMessages");
					broadcasts.add(args[1]);
					Core.getInstance().getConfig().set("AutobroadcastMessages", broadcasts);
					player.sendMessage(Helper.format(Core.getInstance().getConfig().getString(("Prefix") + "You have added this message to the autobroadcast queue: " + args[1])));
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * int interval = Core.getInstance().getConfig().getInt("AutobroadcastInterval");
					
	 * Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable(){
						int line = 0;
						@Override
			            public void run() {
			            	if (line > broadcasts.size() - 1) {
			                    line = 0;
			                }
			            	Bukkit.broadcastMessage(broadcasts.get(line));
			            }
			        }, 0L, ((long)interval));
	 */

}
