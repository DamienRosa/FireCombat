package commander;

import soldier.AchieveMoveToLocation;
import soldier.SoldierBDI;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanCapability;
import jadex.bdiv3.annotation.PlanReason;
import jadex.bdiv3.runtime.IPlan;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.math.IVector2;

@Plan
public class SendSoldiersPlan {

	/** The fire property. */
	public static final String	PROPERTY_FIRE = "fire";
	
	/** The intensity property. */
	public static final String	PROPERTY_INTENSITY = "intensity";
	
	
	@PlanCapability
	private CommanderBDI agent;
	
	@PlanAPI
	private IPlan plan;
	
	@PlanReason
	private AchieveSendSoldiers goal;
	
	@PlanBody
	public void body(){
		System.out.println("I get to the send soldiers plan!");
		agent.setCombatPosition(goal.getPosition());
	}
}
