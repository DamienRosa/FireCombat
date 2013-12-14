package commander;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanCapability;
import jadex.bdiv3.annotation.PlanReason;
import jadex.bdiv3.runtime.IPlan;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.math.IVector2;

@Plan
public class FindFirePlan {

	/** The fire property. */
	public static final String	PROPERTY_FIRE = "fire";
	
	/** The intensity property. */
	public static final String	PROPERTY_INTENSITY = "intensity";
	
	/** The position property. */
	public static final String	PROPERTY_POSITION = "position";
	
	
	@PlanCapability
	private CommanderBDI agent;
	
	@PlanAPI
	private IPlan plan;
	
	@PlanBody
	public void body(){
		System.out.println("I get to the find fire plan");
		ISpaceObject[] fire = agent.getSpace().getSpaceObjectsByType(PROPERTY_FIRE);
		
		for (int i = 0; i < fire.length; i++) {
			if ((int)fire[i].getProperty(PROPERTY_INTENSITY) == 1){
				AchieveSendSoldiers goal = new AchieveSendSoldiers((IVector2) fire[i].getProperty(PROPERTY_POSITION));
				plan.dispatchSubgoal(goal).get();
			}
		}
	}
}
