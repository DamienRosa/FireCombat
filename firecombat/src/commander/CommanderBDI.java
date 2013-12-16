package commander;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Trigger;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import soldier.AchieveMoveToLocation;
import soldier.MoveToLocationPlan;
import soldier.WaitForOrderPlan;

import firecombat.ChatService;
import firecombat.IChatService;

@Agent
@Plans(
{
	@Plan(body=@Body(FindFirePlan.class)),
	@Plan(trigger=@Trigger(goals=AchieveSendSoldiers.class), body=@Body(SendSoldiersPlan.class))
})
@ProvidedServices(@ProvidedService(type=IChatService.class, implementation=@Implementation(ChatService.class)))
@RequiredServices(
{
	@RequiredService(name="clockservice", type=IClockService.class, binding=@Binding(scope=Binding.SCOPE_PLATFORM)),
	@RequiredService(name="chatservices", type=IChatService.class, multiple=true, binding=@Binding(dynamic=true, scope=Binding.SCOPE_PLATFORM))
})
public class CommanderBDI {
	
	@Agent
	public BDIAgent agent;
	@Belief
	protected Grid2D space = (Grid2D)agent.getParentAccess().getExtension("myfc2dspace").get();
	@Belief
	protected ISpaceObject myself = space.getAvatar(agent.getComponentDescription(), agent.getModel().getFullName());
	@Belief
	private int wind_speed;
	@Belief
	private int wind_orientation;
	@Belief
	private ISpaceObject[] fire_location;
	@Belief
	private Set<?> fire_intensity; // modificar o tipo
	@Belief
	private Set<Integer> vegetation_state;
	@Belief
	private double my_vision = 1; // não tenho a certeza do valor mas deduzo que 1 seja visão total
	@Belief
	private IVector2 combat_position;
	
	// um objectivo deste agente é procurar por um incêncio (apenas pela modificação do ambiente)
	
	/**
	 *  Execute the functional body of the agent.
	 *  Is only called once.
	 * @throws InterruptedException 
	 */
	@AgentBody
	public void executeBody() throws InterruptedException {
//		Thread.sleep(5000);
		
		agent.adoptPlan(new FindFirePlan()).get();
		
		final String msg = combat_position.getXAsDouble() + "-" + combat_position.getYAsDouble();
		
		IFuture<Collection<IChatService>>	chatservices	= agent.getServiceContainer().getRequiredServices("chatservices");
		chatservices.addResultListener(new DefaultResultListener<Collection<IChatService>>()
		{
			public void resultAvailable(Collection<IChatService> result)
			{
				for(Iterator<IChatService> it=result.iterator(); it.hasNext(); ) {
					IChatService cs = it.next();
					cs.message(agent.getComponentIdentifier().getLocalName(), msg);
				}
			}
		});
	}
	
	public Grid2D getSpace() {
		return space;
	}
	
	public ISpaceObject getMyself() {
		return myself;
	}
	
	public IVector2 getCombatPosition(){
		return combat_position;
	}
	
	public void setCombatPosition(IVector2 combat_position){
		this.combat_position = combat_position;
	}
	
	public int getWindSpeed() {
		return wind_speed;
	}
 
	public void setWindSpeed(int wind_speed) {
		this.wind_speed = wind_speed;
	}
	
	public int getWindOrientation() {
		return wind_orientation;
	}
 
	public void setWindOrientation(int wind_orientation) {
		this.wind_orientation = wind_orientation;
	}
	
	public ISpaceObject[] getFireLocation() {
		return fire_location;
	}
	
	public void setFireLocation(ISpaceObject[] fire) {
		this.fire_location = fire;
	}
	
	public Set<?> getFireIntensity() {
		return fire_intensity;
	}
	
	public void setFireIntensity(Set<?> fire_intensity) {
		this.fire_intensity = fire_intensity;
	}
	
	public Set<Integer> getVegetationState() {
		return vegetation_state;
	}
	
	public void setVegetationState(Set<Integer> vegetation_state) {
		this.vegetation_state = vegetation_state;
	}
}
