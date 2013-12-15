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
	public static final String	PROPERTY_INTENSITY = "intensity";
	
	
	//-------- IObjectTask methods --------
	
	/**
	 *  Executes the task.
	 *  @param space	The environment in which the task is executing.
	 *  @param obj	The object that is executing the task.
	 *  @param progress	The time that has passed according to the environment executor.
	 */
	public void execute(IEnvironmentSpace space, ISpaceObject obj, long progress, IClockService clock){
		
		IVector2 combat_position = (IVector2)getProperty(PROPERTY_POSITION);
//		IVector2 combat_position = (IVector2)obj.getProperty(PROPERTY_POSITION); acho que pode ser assim também (testar)
		
		Fire fire = new Fire();
		ArrayList<ISpaceObject> flames = fire.getNeighbors(combat_position, (Grid2D) space);
		
		for (int i = 0; i < flames.size(); i++) {
			System.out.println("I got here!");
			flames.get(i).setProperty(PROPERTY_INTENSITY, new Integer(0));
//			space.destroySpaceObject(flames.get(i).getId());
		}
		
		setFinished(space, obj, true);
		
		// Update disaster object based on time progress.
//		int	cnt	= 0;
//		double	extinguished	= ((Number)obj.getProperty(PROPERTY_EXTINGUISHED)).doubleValue();
//		extinguished	+= progress*0.0002;	// 1 fire per 5 seconds.
//		while(extinguished>1)
//		{
//			cnt++;
//			extinguished	-= 1;
//		}
//		int fire	= ((Number)disaster.getProperty("fire")).intValue();
//		fire	= Math.max(fire-cnt, 0);
//		disaster.setProperty("fire", new Integer(fire));
		
		// Remove disaster object when everything is now fine.
//		if(fire==0 && ((Number)disaster.getProperty("chemicals")).intValue()==0 && ((Number)disaster.getProperty("victims")).intValue()==0)
//		{
//			if(space2d.getSpaceObject0(disaster.getId())!=null)
//				space.destroySpaceObject(disaster.getId());
//		}

		// If not finished but least one fire was extinguished
		// use random to determine if need to move to another position for next fire.
//		if(fire==0 || cnt>0 && Math.random()>0.5)
//		{
//			obj.setProperty(PROPERTY_EXTINGUISHED, new Double(0));
//			setFinished(space, obj, true);
//		}
//		else
//		{
//			obj.setProperty(PROPERTY_EXTINGUISHED, new Double(extinguished));			
//		}
	}
}
