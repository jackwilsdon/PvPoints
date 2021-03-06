package com.jackwilsdon.PvPoints;
 
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

import com.jackwilsdon.PvPoints.Metrics.Graph;

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
		 * Start metrics
		 */
		try {
			Metrics metrics = new Metrics(this);
			
			// Construct a graph, which can be immediately used and considered as valid
		    Graph graph = metrics.createGraph("Average points");

		    // Add the points
			graph.addPlotter(new Metrics.Plotter("Points") {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public int getValue() {
					Map<String, PvPointsPlayer> players = PvPointsPlayerManager.getAllPlayers();
					Iterator it = players.entrySet().iterator();
					int count = players.size();
					int total = 0;
					while (it.hasNext())
					{
						Entry<String, PvPointsPlayer> lol = (Entry<String, PvPointsPlayer>) it.next();
						total += lol.getValue().points;
					}
					total = total/count;
					return total;
				}
			});
			
			metrics.start();
		} catch (IOException e) {
			getServer().getLogger().log(Level.SEVERE, "Unable to send statistics :(");
		}
		
		/*
		 * Send the player points to the server
		 * Disabled in v1.2 - To be released later on
		 * PvPointsSubmitter.submitAll(this);
		 */
		
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