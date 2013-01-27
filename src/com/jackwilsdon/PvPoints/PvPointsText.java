package com.jackwilsdon.PvPoints;

import org.bukkit.ChatColor;

/*
 * PvPointsText
 * Parser for messages in config.yml
 */
public class PvPointsText {

	/*
	 * Variable for plugin
	 */
	private static PvPointsPlugin plugin;
	
	/*
	 * PvPointsText()
	 * Constructor
	 */
	PvPointsText(PvPointsPlugin pl)
	{
		plugin = pl;
	}
	
	/*
	 * parse()
	 * Parses colors for messages
	 */
	public static String parse(String message)
	{
		message = message.replaceAll("%AQUA%", ChatColor.AQUA.toString());
		message = message.replaceAll("%BLACK%", ChatColor.BLACK.toString());
		message = message.replaceAll("%BLUE%", ChatColor.BLUE.toString());
		message = message.replaceAll("%BOLD%", ChatColor.BOLD.toString());
		message = message.replaceAll("%DARK_AQUA%", ChatColor.DARK_AQUA.toString());
		message = message.replaceAll("%DARK_BLUE%", ChatColor.DARK_BLUE.toString());
		message = message.replaceAll("%DARK_GRAY%", ChatColor.DARK_GRAY.toString());
		message = message.replaceAll("%DARK_GREEN%", ChatColor.DARK_GREEN.toString());
		message = message.replaceAll("%DARK_PURPLE%", ChatColor.DARK_PURPLE.toString());
		message = message.replaceAll("%DARK_RED%", ChatColor.DARK_RED.toString());
		message = message.replaceAll("%GOLD%", ChatColor.GOLD.toString());
		message = message.replaceAll("%GRAY%", ChatColor.GRAY.toString());
		message = message.replaceAll("%GREEN%", ChatColor.GREEN.toString());
		message = message.replaceAll("%ITALIC%", ChatColor.ITALIC.toString());
		message = message.replaceAll("%LIGHT_PURPLE%", ChatColor.LIGHT_PURPLE.toString());
		message = message.replaceAll("%MAGIC%", ChatColor.MAGIC.toString());
		message = message.replaceAll("%RED%", ChatColor.RED.toString());
		message = message.replaceAll("%RESET%", ChatColor.RESET.toString());
		message = message.replaceAll("%STRIKETHROUGH%", ChatColor.STRIKETHROUGH.toString());
		message = message.replaceAll("%UNDERLINE%", ChatColor.UNDERLINE.toString());
		message = message.replaceAll("%WHITE%", ChatColor.WHITE.toString());
		message = message.replaceAll("%YELLOW%", ChatColor.YELLOW.toString());
		return message;
	}
	
	/*
	 * killPoints()
	 * Returns the message for kill points
	 */
	public static String killPoints(String username)
	{
		int points = PvPointsPlayerManager.getPoints(username);
		String increment = "+"+plugin.getConfig().getInt("PvPoints.add-on-kill.amount");
		if (!plugin.getConfig().getBoolean("PvPoints.add-on-kill.enabled")) increment = "";
		String pointString = "%WHITE%(%GREEN%"+points+increment+"%WHITE%)%YELLOW%";
		return parse(pointString);
	}
	
	/*
	 * deathPoints()
	 * Returns the message for death points
	 */
	public static String deathPoints(String username)
	{
		int points = PvPointsPlayerManager.getPoints(username);
		String increment = "-"+plugin.getConfig().getInt("PvPoints.subtract-on-death.amount");
		if (!plugin.getConfig().getBoolean("PvPoints.subtract-on-death.enabled")) increment = "";
		String pointString = "%WHITE%(%RED%"+points+increment+"%WHITE%)%YELLOW%";
		return parse(pointString);
	}
	
	/*
	 * normalPoints()
	 * Returns the message for normal points
	 */
	public static String normalPoints(String username)
	{
		int points = PvPointsPlayerManager.getPoints(username);
		String pointString = "%WHITE%(%GREEN%"+points+"%WHITE%)%YELLOW%";
		return parse(pointString);
	}
}
