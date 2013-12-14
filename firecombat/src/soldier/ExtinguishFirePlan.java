package soldier;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanCapability;
import jadex.bdiv3.annotation.PlanReason;
import jadex.bdiv3.runtime.IPlan;
import jadex.bdiv3.runtime.PlanFinishedTaskCondition;
import jadex.commons.future.DelegationResultListener;
import jadex.commons.future.Future;
import jadex.extension.envsupport.environment.AbstractTask;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.math.IVector2;

@Plan
public class ExtinguishFirePlan {
	
	@PlanCapability
	private SoldierBDI agent;
	
	@PlanAPI
	private IPlan plan;
	
	@PlanReason
	private AchieveExtinguishFire goal;
	
	@PlanBody
	public void body(){
		ISpaceObject myself = agent.getMyself();
		IVector2 position = goal.getCombatPosition();
		
		/** Move to Location */
		AchieveMoveToLocation move = new AchieveMoveToLocation(position);
		plan.dispatchSubgoal(move);
		
		/** Extinguish Fire */
		myself.setProperty("state", "extinguishing_fire");
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(ExtinguishTask.PROPERTY_POSITION, position);
		properties.put(AbstractTask.PROPERTY_CONDITION, new PlanFinishedTaskCondition(plan));
		IEnvironmentSpace space = agent.getSpace();
		
		Future<Void> future = new Future<Void>();
		DelegationResultListener<Void> listener = new DelegationResultListener<Void>(future, true);
		Object mtaskid = space.createObjectTask(ExtinguishTask.PROPERTY_TYPENAME, properties, myself.getId());
		space.addTaskListener(mtaskid, agent.getMyself().getId(), listener);
		future.get();
	}
}
