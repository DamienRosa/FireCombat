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

@Plan
public class SendSoldiersPlan {

	@PlanCapability
	private CommanderBDI agent;
	
	@PlanAPI
	private IPlan plan;
	
	@PlanReason
	private AchieveSendSoldiers goal;
	
	@PlanBody
	public void body(){
		ISpaceObject[] fire = agent.getSpace().getSpaceObjectsByType("fire");
		
		for (ISpaceObject flame : fire) {
			
		}
	}
}
