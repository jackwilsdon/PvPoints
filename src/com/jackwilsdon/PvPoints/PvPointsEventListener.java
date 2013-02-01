package com.jackwilsdon.PvPoints;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
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
		/*
		 * Set leave details
		 */
		String username = pLeave.getPlayer().getName();
		
		/*
		 * Leave message
		 */
		boolean customMessageEnabled = plugin.getConfig().getBoolean("PvPoints.chat-messages.leave.enabled");
		if (customMessageEnabled)
		{
			/*
			 * Get custom message
			 */
			String customMessage = plugin.getConfig().getString("PvPoints.chat-messages.leave.message");
			
			/*
			 * Parse messages
			 */
			customMessage = PvPointsText.parse(customMessage);
			customMessage = customMessage.replaceAll("%PLAYER%", username);
			customMessage = customMessage.replaceAll("%PLAYERPOINTS%", PvPointsText.normalPoints(username));
			
			/*
			 * Set message
			 */
			pLeave.setQuitMessage(customMessage);
		}
	}
	
	/*
	 * onDeath()
	 * Called on player death
	 */
	@EventHandler
	public void onDeath(PlayerDeathEvent pDeath)
	{
		/*
		 * Check if the player was killed by another player, or via other means
		 */
		if (pDeath.getEntity().getKiller() != null)
		{
			/*
			 * Get player and killer names
			 */
			String victim = pDeath.getEntity().getName();
			String killer = pDeath.getEntity().getKiller().getName();
			
			/*
			 * Death message
			 */
			boolean customMessageEnabled = plugin.getConfig().getBoolean("PvPoints.chat-messages.kill.enabled");
			if (customMessageEnabled)
			{
				/*
				 * Get custom message
				 */
				String customMessage = plugin.getConfig().getString("PvPoints.chat-messages.kill.message");
				
				/*
				 * Parse messages
				 */
				customMessage = PvPointsText.parse(customMessage);
				customMessage = customMessage.replaceAll("%VICTIM%", victim);
				customMessage = customMessage.replaceAll("%VICTIMPOINTS%", PvPointsText.deathPoints(victim));
				
				customMessage = customMessage.replaceAll("%KILLER%", killer);
				customMessage = customMessage.replaceAll("%KILLERPOINTS%", PvPointsText.killPoints(killer));
				
				/*
				 * Set message
				 */
				pDeath.setDeathMessage(customMessage);
			}
			
			/*
			 * Add deaths and kills
			 */
			PvPointsPlayerManager.addDeath(victim);
			PvPointsPlayerManager.addKill(killer);
		} else {
			/*
			 * The player was killed by some other means
			 * Display current points
			 */
			
			/*
			 * Get player name and message
			 */
			String victim = pDeath.getEntity().getName();
			String message = pDeath.getDeathMessage();
			
			/*
			 * Edit message
			 */
			boolean add = plugin.getConfig().getBoolean("PvPoints.subtract-on-death.non-pvp");
			boolean showPoints = plugin.getConfig().getBoolean("PvPoints.chat-messages.death.display-points");
			if (showPoints)
			{
				/*
				 * Parse message
				 */
				if (add)
				{
					message = message.replaceAll(victim, victim+PvPointsText.deathPoints(victim));
				} else {
					message = message.replaceAll(victim, victim+PvPointsText.normalPoints(victim));
				}
				
				/*
				 * Set message
				 */
				pDeath.setDeathMessage(message);
			}
			
			/*
			 * Add death
			 */
			if (add)
			{
				PvPointsPlayerManager.addDeath(victim);
			}
		}
	}
	
}