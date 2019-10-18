package arod.chatmanager.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import arod.chatmanager.Core;
import arod.chatmanager.command.CommandStaffChat;
import arod.chatmanager.utility.Helper;

public class ChatListener implements Listener
{

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		if (Core.getInstance().chat == false)
		{
			if (event.getPlayer().hasPermission("chatmanager.chat.bypassmute"))
			{
				event.setCancelled(false);
			} 
			else if (!event.getPlayer().hasPermission("chatmanager.chat.bypassmute"))
			{
				event.setCancelled(true);
				event.getPlayer().sendMessage(Helper.format(Core.getInstance().getConfig().getString("Messages.ChatIsMuted")));
			}
			else
			{
				return;
			}
		}
		else if (Core.getInstance().chat == true)
		{
			event.setCancelled(false);
			return;
		}
	}
	
	@EventHandler
	public void onChatStaff(AsyncPlayerChatEvent event)
	{
		if (CommandStaffChat.staff.contains(event.getPlayer().getUniqueId()))
		{
			event.setCancelled(true);
			if (event.getPlayer().hasPermission("chatmanager.staffmanager"))
			{
				for (Player player : Bukkit.getOnlinePlayers())
				{
					player.sendMessage(Helper.format(Core.getInstance().getConfig().getString("Messages.StaffChatFormat").replace("%player%", player.getName()).replace("%message%", event.getMessage())));
				}
			} 
			else 
			{
				return;
			}
		}
	}
	
}
