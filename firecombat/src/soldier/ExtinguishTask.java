package soldier;

import java.util.ArrayList;

import jadex.bridge.service.types.clock.IClockService;
import jadex.extension.envsupport.environment.AbstractTask;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;

public class ExtinguishTask extends AbstractTask {
	
	/** The task name. */
	public static final String	PROPERTY_TYPENAME = "extinguish";
	
	/** The destination property. */
	public static final String	PROPERTY_POSITION = "position";
	
	/** The extinct property. */
	public static final String	PROPERTY_INTENSITY = "intensity";
	
	/** The extinct property. */
	public static final String	PROPERTY_FIRE = "fire";
	
	ArrayList<ISpaceObject> temp;
	
	
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
		
		ArrayList<ISpaceObject> flames = getNeighbors((Grid2D) space, combat_position);
		
		for (int i = 0; i < flames.size(); i++) {
			System.out.println("I got here! " + flames.size());
//			flames.get(i).setProperty(PROPERTY_INTENSITY, new Integer(0));
			space.destroySpaceObject(flames.get(i).getId());
		}
		
		setFinished(space, obj, true);
	}
	
	public ArrayList<ISpaceObject> getNeighbors(Grid2D space, IVector2 pos) {
		
		 temp = new ArrayList<ISpaceObject>();
		
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()+1, pos.getYAsDouble()), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()+1, pos.getYAsDouble()), PROPERTY_FIRE));
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()-1, pos.getYAsDouble()), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()-1, pos.getYAsDouble()), PROPERTY_FIRE));
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble(), pos.getYAsDouble()+1), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble(), pos.getYAsDouble()+1), PROPERTY_FIRE));
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble(), pos.getYAsDouble()-1), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble(), pos.getYAsDouble()-1), PROPERTY_FIRE));
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()+1, pos.getYAsDouble()-1), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()+1, pos.getYAsDouble()-1), PROPERTY_FIRE));
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()-1, pos.getYAsDouble()+1), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()-1, pos.getYAsDouble()+1), PROPERTY_FIRE));
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()+1, pos.getYAsDouble()+1), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()+1, pos.getYAsDouble()+1), PROPERTY_FIRE));
//		if (space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()-1, pos.getYAsDouble()-1), PROPERTY_FIRE) != null)
			temp.add((ISpaceObject) space.getSpaceObjectsByGridPosition(new Vector2Double(pos.getXAsDouble()-1, pos.getYAsDouble()-1), PROPERTY_FIRE));
		
		return temp;
	}
}
