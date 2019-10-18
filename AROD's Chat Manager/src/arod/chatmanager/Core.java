package arod.chatmanager;

import org.bukkit.plugin.java.JavaPlugin;

import arod.chatmanager.command.CommandAutoBroadcast;
import arod.chatmanager.command.CommandBroadcast;
import arod.chatmanager.command.CommandMuteChat;
import arod.chatmanager.command.CommandStaffChat;
import arod.chatmanager.listener.ChatListener;

public class Core extends JavaPlugin
{

	public boolean chat = true;
	
	private static Core instance;
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		if (!this.getDataFolder().exists())
		{
			this.getDataFolder().mkdir();
		}
		this.registerCommands();
		this.registerEvents();
		this.loadConfiguration();
	}
	
	@Override
	public void onDisable()
	{
	}
	
	private void loadConfiguration()
	{
		this.getConfig().addDefault("Prefix", "&8[&6ChatManager&8] &8» &7");
		this.getConfig().addDefault("MuteChat", chat);
		this.getConfig().addDefault("NoPermission", "&cInsufficient permission.");
		this.getConfig().addDefault("BroadcastPrefix", "&c&lAnnouncement &8» &7");
		this.getConfig().addDefault("BroadcastMessage", true);
		this.getConfig().addDefault("Messages.ChatUnmuted", getConfig().get("Prefix") + "Chat has been unmuted by %player%");
		this.getConfig().addDefault("Messages.ChatMuted", getConfig().get("Prefix") + "Chat has been muted by %player%");
		this.getConfig().addDefault("Messages.ChatIsMuted", getConfig().get("Prefix") + "&cChat is currently muted");
		this.getConfig().addDefault("Messages.StaffChatEnabled", getConfig().get("Prefix") + "You are now in StaffChat");
		this.getConfig().addDefault("Messages.StaffChatDisabled", getConfig().get("Prefix") + "You are no longer in StaffChat");
		this.getConfig().addDefault("Messages.StaffChatFormat", "&8[&aStaffChat&8] &b%player%: &e%message%");
		this.getConfig().addDefault("AutoBroadcast.Enabled", true);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	private void registerCommands()
	{
		this.getCommand("mutechat").setExecutor(new CommandMuteChat());
		this.getCommand("broadcast").setExecutor(new CommandBroadcast());
		this.getCommand("staffchat").setExecutor(new CommandStaffChat());
		this.getCommand("autobroadcast").setExecutor(new CommandAutoBroadcast());
	}
	
	private void registerEvents()
	{
		this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
	}

	public static Core getInstance()
	{
		return instance;
	}
	
}
