package com.jackwilsdon.PvPoints;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * PvPointsCommandExecutor
 * Command Executor for PvPoints
 */
public class PvPointsCommandExecutor implements CommandExecutor {
	/*
	 * Variable for plugin
	 */
	private static PvPointsPlugin plugin;
	
	/*
	 * Message return prefix
	 */
	String prefix = "["+ChatColor.GREEN+"PvPoints"+ChatColor.WHITE+"] ";
	
	/*
	 * PvPointsPlayerManager
	 * Constructor
	 */
	PvPointsCommandExecutor(PvPointsPlugin pl)
	{
		/*
		 * Set plugin variable
		 */
		plugin = pl;
	}
	
	/*
	 * issue()
	 * Displays a problem to the reciever
	 */
	public void issue(String issue, CommandSender reciever)
	{
		switch (issue)
		{
		case "syntax":
			reciever.sendMessage(prefix+ChatColor.YELLOW+"Invalid syntax!");
			reciever.sendMessage(prefix+ChatColor.YELLOW+"Valid commands are "+ChatColor.GREEN+"reset");
			reciever.sendMessage(prefix+ChatColor.YELLOW+"Optional arguments are denoted as "+ChatColor.GREEN+"[option]");
			break;
		case "reset-syntax":
			reciever.sendMessage(prefix+ChatColor.YELLOW+"Invalid syntax for "+ChatColor.GREEN+"reset"+ChatColor.YELLOW+"!");
			reciever.sendMessage(prefix+ChatColor.YELLOW+"Valid syntax: /pvpoints reset [username]");
			reciever.sendMessage(prefix+ChatColor.YELLOW+"Optional arguments are denoted as "+ChatColor.GREEN+"[option]");
			break;
		case "reset-sender":
			reciever.sendMessage(prefix+ChatColor.GREEN+"reset"+ChatColor.RED+" without parameters can only be run by a player!");
			break;
		case "missing-player":
			reciever.sendMessage(prefix+ChatColor.RED+"That is not a valid player!");
		default:
			reciever.sendMessage(prefix+ChatColor.RED+"Something went wrong!");
			break;
		}
	}
	
	/*
	 * onCommand()
	 * Called when a command is run
	 */
	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] arguments)
	{
		/*
		 * Check argument length is correct
		 */
		if (arguments.length == 0)
		{
			issue("syntax", cmdSender);
			return true;
		}
		
		/*
		 * Reload the configuration
		 */
		plugin.reloadConfig();
		
		/*
		 * Retrieve command and remove it from the arguments
		 */
		String command = arguments[0];
		arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
		
		/*
		 * Manage the command
		 */
		switch(command)
		{
		/*
		 * Reset score command
		 */
		case "reset":
			if (arguments.length == 0)
			{
				if (!(cmdSender instanceof Player))
				{
					issue("reset-sender", cmdSender);
					return true;
				}
				PvPointsPlayerManager.reset(cmdSender.getName());
				cmdSender.sendMessage(prefix+ChatColor.YELLOW+"Your kills/deaths have been reset!");
			} else if (arguments.length == 1) {
				if (!PvPointsPlayerManager.playerExists(arguments[0]))
				{
					issue("missing-player", cmdSender);
					return true;
				}
				PvPointsPlayerManager.reset(arguments[0]);
				cmdSender.sendMessage(prefix+ChatColor.YELLOW+"The player "+ChatColor.GREEN+arguments[0]+ChatColor.YELLOW+" has been reset!");
				plugin.getServer().getPlayer(arguments[0]).sendMessage(prefix+ChatColor.YELLOW+"Your kills/deaths have been reset!");
			} else {
				issue("reset-syntax", cmdSender);
				return true;
			}
			break;
			
		/*
		 * Default, unknown command
		 */
		default:
			issue("syntax", cmdSender);
			break;
		}
		
		/*
		 * Save any configuration changes
		 */
		plugin.saveConfig();
		
		/*
		 * Return true to prevent default help from appearing
		 */
		return true;
	}

}