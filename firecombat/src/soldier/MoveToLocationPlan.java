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
public class MoveToLocationPlan {
	
	@PlanCapability
	private SoldierBDI agent;
	
	@PlanAPI
	private IPlan plan;
	
	@PlanReason
	private AchieveMoveToLocation goal;
	
	@PlanBody
	public void body(){
		ISpaceObject myself = agent.getMyself();
		IVector2 destination = goal.getDestination();
		
		System.out.println("I'm the move to location plan!");
		
//		if(!((String)myself.getProperty("state")).equals("moving_to_hospital") && dest.equals(home))
//			myself.setProperty("state", "moving_home");
		
		myself.setProperty("state", "moving_to_location");
		
		// Create a move task
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(MoveTask.PROPERTY_DESTINATION, destination);
		properties.put(AbstractTask.PROPERTY_CONDITION, new PlanFinishedTaskCondition(plan));
		IEnvironmentSpace space = agent.getSpace();
		
		Future<Void> future = new Future<Void>();
		DelegationResultListener<Void> listener = new DelegationResultListener<Void>(future, true);
		Object mtaskid = space.createObjectTask(MoveTask.PROPERTY_TYPENAME, properties, myself.getId());
		space.addTaskListener(mtaskid, agent.getMyself().getId(), listener);
		future.get();
	}
}
