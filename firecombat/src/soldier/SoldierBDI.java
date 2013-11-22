package soldier;

import java.util.Set;

import world.Location;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class SoldierBDI {

	@Agent
	private BDIAgent agent;
	
	@Belief
	private Location my_location;
	@Belief
	private Set<?> combat_positions;
//	@Belief
	//implementar IEnvironment interface e também a classe Environment
//	private IEnvironment environment = Environment.getInstance();
	@Belief
	private int wind_speed;
	@Belief
	private String wind_orientation; // rosa dos ventos ou ângulos
	@Belief
	private Set<?> fire_intensity;
	@Belief
	private Set<?> vegetation_state;
	
	@AgentBody
	public void body(){
		
	}
	
	@Plan(trigger=@Trigger(goals=AchieveMoveTo.class))
	public void moveToLocationPlan(AchieveMoveTo goal){
		
	}
	
	@Plan(trigger=@Trigger(goals=MaintainSelfSecurity.class))
	public void selfSecurityPlan(MaintainSelfSecurity goal){
		
	}
	
	@Plan(trigger=@Trigger(goals=QueryCombatPositions.class))
	public void getCombatPositionPlan(QueryCombatPositions goal){
		
	}
}
