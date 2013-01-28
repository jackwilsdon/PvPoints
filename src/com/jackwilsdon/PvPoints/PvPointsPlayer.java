package com.jackwilsdon.PvPoints;

/*
 * PvPlayer
 * A player object used in the getInfo() method
 */
public class PvPointsPlayer {
	
	/*
	 * Player information
	 */
	public String username = "Player";
	public int kills = 0;
	public int deaths = 0;
	public int points = 0;
	public boolean real = false;
	
	/*
	 * PvPlayer()
	 * Constructor
	 */
	PvPointsPlayer(int kills, int deaths)
	{
		this.kills = kills;
		this.deaths = deaths;
		if (this.kills != -1 && this.deaths != -1)
		{
			real = true;
		}
	}
	
	/*
	 * PvPlayer()
	 * Constructor
	 */
	PvPointsPlayer(int kills, int deaths, int points)
	{
		this(kills, deaths);
		this.points = points;
	}
	
	/*
	 * PvPlayer()
	 * Constructor
	 */
	PvPointsPlayer(int kills, int deaths, int points, String username)
	{
		this(kills, deaths);
		this.points = points;
		this.username = username;
	}
	
	/*
	 * PvPlayer()
	 * Constructor
	 */
	PvPointsPlayer(int kills, int deaths, String username)
	{
		this(kills, deaths);
		this.username = username;
	}
	
	/*
	 * toString()
	 * Called when a PvPlayer object is outputted
	 */
	public String toString()
	{
		return "PvPlayer: Kills:"+this.kills+" Deaths:"+this.deaths+" Points:"+this.points;
	}
}
