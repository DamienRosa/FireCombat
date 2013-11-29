package soldier;

import java.util.Set;

import world.Location;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
@Plans(
{
	@Plan(trigger=@Trigger(goals=MaintainSelfSecurity.class), body=@Body(SelfSecurityPlan.class)),
	@Plan(trigger=@Trigger(goals=AchieveExtinguishFire.class), body=@Body(ExtinguishFirePlan.class)),
	@Plan(trigger=@Trigger(goals=AchieveMoveToLocation.class), body=@Body(MoveToLocationPlan.class))
})
public class SoldierBDI {

	@Agent
	private BDIAgent agent;
	
	@Belief
	protected Location my_location;
	@Belief
	protected double fire_distance;
	@Belief
	protected int wind_speed;
	@Belief
	protected String wind_orientation; // rosa dos ventos ou ângulos
	@Belief
	private Set<?> fire_intensity;
	@Belief
	private Set<?> vegetation_state;
	
	@AgentBody
	public void body(){
		
	}
	
	public double getFireDistance() {
		return fire_distance;
	}
	
	public Set<?> getFireIntensity() {
		return fire_intensity;
	}
	
	public Location getMyLocation() {
		return my_location;
	}
	
	public Set<?> getVegetationState() {
		return vegetation_state;
	}
	
	public String getWindOrientation() {
		return wind_orientation;
	}
	
	public int getWindSpeed() {
		return wind_speed;
	}
	
	public void setFireDistance(double fire_distance) {
		this.fire_distance = fire_distance;
	}
	
	public void setFireIntensity(Set<?> fire_intensity) {
		this.fire_intensity = fire_intensity;
	}
	
	public void setMyLocation(Location my_location) {
		this.my_location = my_location;
	}
	
	public void setVegetationState(Set<?> vegetation_state) {
		this.vegetation_state = vegetation_state;
	}
	
	public void setWindOrientation(String wind_orientation) {
		this.wind_orientation = wind_orientation;
	}
	
	public void setWindSpeed(int wind_speed) {
		this.wind_speed = wind_speed;
	}
}
