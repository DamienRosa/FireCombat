package soldier;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import firecombat.ChatService;
import firecombat.IChatService;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.annotation.Body;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Agent
@Plans(
{
//	@Plan(trigger=@Trigger(goals=MaintainSelfSecurity.class), body=@Body(SelfSecurityPlan.class)),
	@Plan(trigger=@Trigger(goals=AchieveExtinguishFire.class), body=@Body(ExtinguishFirePlan.class)),
	@Plan(trigger=@Trigger(goals=AchieveMoveToLocation.class), body=@Body(MoveToLocationPlan.class)),
})
@ProvidedServices(@ProvidedService(type=IChatService.class, implementation=@Implementation(ChatService.class)))
@RequiredServices(
{
	@RequiredService(name="clockservice", type=IClockService.class, binding=@Binding(scope=Binding.SCOPE_PLATFORM)),
	@RequiredService(name="chatservices", type=IChatService.class, multiple=true, binding=@Binding(dynamic=true, scope=Binding.SCOPE_PLATFORM))
})
public class SoldierBDI {
	
	private static int agent_number = 0;
	
	@Agent
	protected BDIAgent agent;
	@Belief
	protected Grid2D space = (Grid2D)agent.getParentAccess().getExtension("myfc2dspace").get();
	@Belief
	protected ISpaceObject myself = space.getAvatar(agent.getComponentDescription(), agent.getModel().getFullName());
	@Belief
	protected double fire_distance;
	@Belief
	protected int wind_speed;
	@Belief
	protected String wind_orientation;
	@Belief
	private Set<?> fire_intensity;
	@Belief
	private Set<?> vegetation_state;
	
	@AgentBody
	public void body() throws InterruptedException{	
//		Thread.sleep(5005);
		agent_number++;
		System.out.println("I'm soldier no. " + agent_number);
	}
	
	public Grid2D getSpace() {
		return space;
	}
	
	public ISpaceObject getMyself() {
		return myself; 
	}
	
	/** The home position (=first position). */
	public IVector2 getHomePos() {
		return getMyself()!=null? (IVector2)getMyself().getProperty(Space2D.PROPERTY_POSITION): null;
	}
	
	public IVector2 getPosition() {
		return (IVector2)getMyself().getProperty("position");
	}
	
	
	public double getFireDistance() {
		return fire_distance;
	}
	
	public Set<?> getFireIntensity() {
		return fire_intensity;
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
