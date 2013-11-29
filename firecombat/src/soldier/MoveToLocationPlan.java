package soldier;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanCapability;
import jadex.bdiv3.annotation.PlanReason;
import jadex.bdiv3.runtime.IPlan;

@Plan
public class MoveToLocationPlan {

	@PlanCapability
	protected SoldierBDI agent;
	
	@PlanAPI
	protected IPlan plan;
	
	@PlanReason
	protected AchieveMoveToLocation goal;
	
	@PlanBody
	public void body(){
		
	}
}
