package soldier;

import jadex.bdiv3.annotation.Goal;
import jadex.extension.envsupport.math.IVector2;

@Goal
public class AchieveMoveToLocation {
	
	/** The destination. */
	protected IVector2 destination;

	/**
	 *  Create a new Move. 
	 */
	public AchieveMoveToLocation(IVector2 destination){
		this.destination = destination;
	}

	/**
	 *  Get the destination.
	 *  @return The destination.
	 */
	public IVector2 getDestination(){
		return destination;
	}
}
