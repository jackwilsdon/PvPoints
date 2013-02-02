package com.jackwilsdon.PvPoints;

import java.util.Comparator;

public class PvPointsLeaderboard implements Comparator<PvPointsPlayer> {

			@Override
			public int compare(PvPointsPlayer arg0, PvPointsPlayer arg1) {
				if (arg0.points > arg1.points) return -1;
				if (arg0.points < arg1.points) return 1;
				return 0;
			}

	
}
