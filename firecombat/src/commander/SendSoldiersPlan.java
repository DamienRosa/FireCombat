package commander;

import java.util.Collection;
import java.util.Iterator;

import firecombat.ChatService;
import firecombat.IChatService;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanCapability;
import jadex.bdiv3.annotation.PlanReason;
import jadex.bdiv3.runtime.IPlan;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Plan
@ProvidedServices(@ProvidedService(type=IChatService.class, implementation=@Implementation(ChatService.class)))
@RequiredServices(
{
	@RequiredService(name="clockservice", type=IClockService.class, binding=@Binding(scope=Binding.SCOPE_PLATFORM)),
	@RequiredService(name="chatservices", type=IChatService.class, multiple=true, binding=@Binding(dynamic=true, scope=Binding.SCOPE_PLATFORM))
})
public class SendSoldiersPlan {

	/** The fire property. */
	public static final String	PROPERTY_FIRE = "fire";
	
	/** The intensity property. */
	public static final String	PROPERTY_INTENSITY = "intensity";
	
	
	@PlanCapability
	private CommanderBDI agent;
	
	@PlanAPI
	private IPlan plan;
	
	@PlanReason
	private AchieveSendSoldiers goal;
	
	@PlanBody
	public void body(){
		agent.setCombatPosition(goal.getPosition());
		
		final String msg = goal.getPosition().getXAsDouble() + "-" + goal.getPosition().getYAsDouble();
		
		IFuture<Collection<IChatService>>	chatservices	= agent.getMyself().getServiceContainer().getRequiredServices("chatservices");
		chatservices.addResultListener(new DefaultResultListener<Collection<IChatService>>()
		{
			public void resultAvailable(Collection<IChatService> result)
			{
				for(Iterator<IChatService> it=result.iterator(); it.hasNext(); ) {
					IChatService cs = it.next();
					cs.message(agent.getMyself().getComponentIdentifier().getLocalName(), msg);
				}
			}
		});
	}
}
