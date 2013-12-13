package soldier;

import jadex.bridge.service.types.clock.IClockService;
import jadex.extension.envsupport.environment.AbstractTask;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;

public class MoveTask extends AbstractTask {
	
	/** The task name. */
	public static final String	PROPERTY_TYPENAME = "move";
	
	/** The destination property. */
	public static final String	PROPERTY_DESTINATION = "destination";
	
	/** The speed property. */
	public static final String	PROPERTY_SPEED = "speed";
		
	//-------- IObjectTask methods --------
	
	/**
	 *  Executes the task.
	 *  @param space	The environment in which the task is executing.
	 *  @param obj	The object that is executing the task.
	 *  @param progress	The time that has passed according to the environment executor.
	 */
	public void execute(IEnvironmentSpace space, ISpaceObject obj, long progress, IClockService clock)
	{
		IVector2 destination = (IVector2)getProperty(PROPERTY_DESTINATION);
		IVector2 location = (IVector2)obj.getProperty(Space2D.PROPERTY_POSITION);
		double	speed	= ((Number)obj.getProperty("speed")).doubleValue();
		IVector2 direction	= destination.copy().subtract(location).normalize();
		double	distance	= ((Space2D)space).getDistance(location, destination).getAsDouble();
		double	maxdist	= progress*speed*0.001;
//		double	maxdist	= progress*0.001;
		IVector2	newloc	= distance<=maxdist ? destination : direction.multiply(maxdist).add(location);
		((Space2D)space).setPosition(obj.getId(), newloc);		
		if(newloc==destination)
			setFinished(space, obj, true);
	}
}
