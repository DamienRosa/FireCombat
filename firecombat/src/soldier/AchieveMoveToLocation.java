package soldier;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalTargetCondition;
import world.Location;

@Goal
public class AchieveMoveToLocation extends SoldierBDI {
	
	protected Location location;
	
	/**
	 *  Create a new goal.
	 *  @param location The location.
	 */
	public AchieveMoveToLocation(Location location)
	{
//		System.out.println("created: "+location);
		this.location = location;
	}
	
	/**
	 *  The goal is achieved when the position
	 *  of the soldier is near to the target position.
	 */
	@GoalTargetCondition(beliefs="my_location")
	public boolean checkTarget()
	{
//		return my_location.isNear(location);
		return my_location.equals(location);
	}

	/**
	 *  Get the location.
	 *  @return The location.
	 */
	public Location getLocation()
	{
		return location;
	}
}
