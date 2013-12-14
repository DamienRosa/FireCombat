package soldier;

import java.util.ArrayList;

import world.Fire;

import jadex.bridge.service.types.clock.IClockService;
import jadex.extension.envsupport.environment.AbstractTask;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;

public class ExtinguishTask extends AbstractTask {
	
	/** The task name. */
	public static final String	PROPERTY_TYPENAME = "extinguish";
	
	/** The destination property. */
	public static final String	PROPERTY_POSITION = "position";
	
	/** The extinct property. */
	public static final String	PROPERTY_EXTINCT = "extinct";
	
	//-------- IObjectTask methods --------
	
	/**
	 *  Executes the task.
	 *  @param space	The environment in which the task is executing.
	 *  @param obj	The object that is executing the task.
	 *  @param progress	The time that has passed according to the environment executor.
	 */
	public void execute(IEnvironmentSpace space, ISpaceObject obj, long progress, IClockService clock){
		
		IVector2 location = (IVector2)obj.getProperty(Space2D.PROPERTY_POSITION);
		
		Fire fire = new Fire();
		ArrayList<ISpaceObject> flames = fire.getNeighbors(location, (Grid2D) space);
		
		for (int i = 0; i < flames.size(); i++) {
			flames.get(i).setProperty(PROPERTY_EXTINCT, true);
		}
		
//		setFinished(space, obj, true);
	}
}
