package firecombat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import soldier.AchieveExtinguishFire;
import soldier.AchieveMoveToLocation;


import jadex.bdiv3.BDIAgent;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.bridge.service.annotation.ServiceStart;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.DelegationResultListener;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;



@Service
public class ChatService implements IChatService {
	/** The agent. */
	@ServiceComponent
	protected BDIAgent agent;
	protected IClockService clock;
	protected DateFormat format;
 
	/**
	*  Receives a chat message.
	*  @param sender The sender's name.
	*  @param text The message text.
	*/
	
	public void message(final String sender, final String msg) {		
		if (agent.getComponentIdentifier().getLocalName().equals("Soldier"))
		{
			System.out.println(sender+" send a message");
			System.out.println("Agent "+agent.getComponentIdentifier().getLocalName()+" received the message");
			
			Vector2Double destination = new Vector2Double(Double.parseDouble(msg.split("-")[0]), Double.parseDouble(msg.split("-")[1]));
			agent.dispatchTopLevelGoal(new AchieveExtinguishFire(destination)).get();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ServiceStart
	public IFuture<Void> startService(){
		format = new SimpleDateFormat("hh:mm:ss");
		final Future ret = new Future();
		
		IFuture<IClockService> fut = agent.getServiceContainer().getRequiredService("clockservice");
		fut.addResultListener(new DelegationResultListener<IClockService>(ret)
		{
		  public void customResultAvailable(IClockService result)
		  {
		    clock = result;
		    super.customResultAvailable(null);
		  }
		});
		return ret;
	}

}
