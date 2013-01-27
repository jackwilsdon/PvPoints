package com.jackwilsdon.PvPoints;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/*
 * PvPointsEventListener
 * Event listener for PvPoints
 */
public class PvPointsEventListener implements Listener {

	/*
	 * Variables for plugin
	 */
	PvPointsPlugin plugin;
	
	/*
	 * PvPointsEventListener()
	 * Constructor
	 */
	PvPointsEventListener(PvPointsPlugin pl)
	{
		plugin = pl;
	}
	
	/*
	 * onJoin()
	 * Called when a player joins
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent pJoin)
	{
		/*
		 * Set join details
		 */
		String username = pJoin.getPlayer().getName();
		
		/*
		 * Check if a player exists in the config
		 */
		if (!PvPointsPlayerManager.playerExists(username))
		{
			/*
			 * Initialise the player if they are not already configured
			 */
			PvPointsPlayerManager.reset(username);
		}
		
		/*
		 * Join message
		 */
		boolean customMessageEnabled = plugin.getConfig().getBoolean("PvPoints.chat-messages.join.enabled");
		if (customMessageEnabled)
		{
			/*
			 * Get custom message
			 */
			String customMessage = plugin.getConfig().getString("PvPoints.chat-messages.join.message");
			
			/*
			 * Parse messages
			 */
			customMessage = PvPointsText.parse(customMessage);
			customMessage = customMessage.replaceAll("%PLAYER%", username);
			customMessage = customMessage.replaceAll("%PLAYERPOINTS%", PvPointsText.normalPoints(username));
			
			/*
			 * Set message
			 */
			pJoin.setJoinMessage(customMessage);
		}
	}
	
	/*
	 * onLeave()
	 * Called when a player quits
	 */
	@EventHandler
	public void onLeave(PlayerQuitEvent pLeave)
	{
		
	}
	
}