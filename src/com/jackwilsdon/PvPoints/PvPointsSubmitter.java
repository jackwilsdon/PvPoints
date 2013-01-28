package com.jackwilsdon.PvPoints;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.*;

/*
 * PvPointsSubmitter
 * Submits the scores to the leaderboard
 */
public class PvPointsSubmitter {
	
	/*
	 * Request URL
	 */
	private static final String REQ_URL = "http://pvpoints.jackwilsdon.tk/";
	private static final String REQ_PATH = "add.php";
	
	/*
	 * Submit all players
	 */
	@SuppressWarnings("unchecked")
	public static void submitAll(PvPointsPlugin pl)
	{
		Map<String, PvPointsPlayer> players = PvPointsPlayerManager.getAllPlayers();
		Iterator<Entry<String, PvPointsPlayer>> it = players.entrySet().iterator();
		JSONArray output = new JSONArray();
		while (it.hasNext()) {
			Entry<String, PvPointsPlayer> entry = it.next();
			String username = entry.getKey();
			PvPointsPlayer player = entry.getValue();
			
			JSONObject json = new JSONObject();
			json.put("username", username);
			json.put("kills", player.kills);
			json.put("deaths", player.deaths);
			output.add(json);
		}
	}
}
