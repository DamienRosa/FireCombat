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
import jadex.extension.envsupport.environment.AbstractTask;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.math.IVector2;

@Plan
public class MoveToLocationPlan {
//
////	@PlanCapability
////	protected SoldierBDI agent;
//	
//	@PlanCapability
//	protected IEnvAccess capa;
//	
//	@PlanAPI
//	protected IPlan plan;
//	
//	@PlanReason
//	protected AchieveMoveToLocation goal;
//	
//	@PlanBody
//	public void body(){
//		ISpaceObject myself	= agent.getMyself();
//		IVector2 dest = goal.getDestination();
//		
////		System.out.println("move plan: "+goal.getDestination());
//		
////		if(!((String)myself.getProperty("state")).equals("moving_to_hospital") && dest.equals(home))
////			myself.setProperty("state", "moving_home");
//		
//		// Create a move task
//		Map props = new HashMap();
//		props.put(MoveTask.PROPERTY_DESTINATION, dest);
////		props.put(MoveTask.PROPERTY_SCOPE, capa.getCapability().getAgent().getExternalAccess());
//		props.put(AbstractTask.PROPERTY_CONDITION, new PlanFinishedTaskCondition(plan));
//		IEnvironmentSpace space = agent.getEnvironment();
//		
//		Future<Void> fut = new Future<Void>();
//		DelegationResultListener<Void> lis = new DelegationResultListener<Void>(fut, true);
//		Object mtaskid = space.createObjectTask(MoveTask.PROPERTY_TYPENAME, props, myself.getId());
//		space.addTaskListener(mtaskid, myself.getId(), lis);
//		fut.get();
////		System.out.println("move after second task: "+rplan);
//		
////		System.out.println("Moved to location: "+capa.getMyself());
//	}
}
