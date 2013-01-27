package com.jackwilsdon.PvPoints;
 
import org.bukkit.plugin.java.JavaPlugin;

/*
 * PvPointsPlugin
 * Main class - Manages enabling and disabling
 */
public class PvPointsPlugin extends JavaPlugin {
	
	/*
	 * onEnable()
	 * Called on plugin enable
	 */
	public void onEnable()
	{
		/*
		 * Output enable message
		 */
		getLogger().info(getDescription().getFullName()+" enabled!");
		
		/*
		 * Save default config
		 */
		saveDefaultConfig();
		
		/*
		 * Configure player manager
		 */
		new PvPointsPlayerManager(this);
		
		/*
		 * Configure text manager
		 */
		new PvPointsText(this);
		
		/*
		 * Register event listener
		 */
		getServer().getPluginManager().registerEvents(new PvPointsEventListener(this), this);
		
		/*
		 * Register command executor
		 */
		getCommand("pvpoints").setExecutor(new PvPointsCommandExecutor(this));
	}
	
	/*
	 * onDisable()
	 * Called on plugin disable
	 */
	public void onDisable()
	{
		/*
		 * Output disable message
		 */
		getLogger().info(getDescription().getFullName()+" disabled!");
	}
}