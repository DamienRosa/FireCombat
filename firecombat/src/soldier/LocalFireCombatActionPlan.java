package soldier;

import world.Soldier;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanCapability;
import jadex.bdiv3.annotation.PlanReason;
import jadex.bdiv3.runtime.IPlan;
import jadex.commons.future.IFuture;

public class LocalFireCombatActionPlan {
	
	@PlanCapability
	protected Soldier soldier;
	
	@PlanAPI
	protected IPlan rplan;
	
	@PlanReason
	protected FireCombatAction goal;
	
	@PlanBody
	public IFuture<Void> body(){
		
		return null;
	}
}
