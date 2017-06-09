package de.ativelox.leaguestats.util;

import java.util.HashMap;
import java.util.Map;

import de.ativelox.leaguestats.constants.ERankedQueue;

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class RankedQueueIDMapper {

	public static Map<Integer, ERankedQueue> QUEUE;

	/**
	 * 
	 */
	public RankedQueueIDMapper() {
		// TODO Auto-generated constructor stub
	}

	public static void init() {
		QUEUE = new HashMap<>();
		QUEUE.put(Integer.valueOf(4), ERankedQueue.RANKED_SOLO_5x5);
		QUEUE.put(Integer.valueOf(6), ERankedQueue.RANKED_SOLO_5x5);
		QUEUE.put(Integer.valueOf(9), ERankedQueue.RANKED_TEAM_3x3);
		QUEUE.put(Integer.valueOf(41), ERankedQueue.RANKED_TEAM_5x5);
		QUEUE.put(Integer.valueOf(410), ERankedQueue.RANKED_SOLO_5x5);
		QUEUE.put(Integer.valueOf(420), ERankedQueue.RANKED_SOLO_5x5);
		QUEUE.put(Integer.valueOf(440), ERankedQueue.RANKED_FLEX_SR);

	}

}
