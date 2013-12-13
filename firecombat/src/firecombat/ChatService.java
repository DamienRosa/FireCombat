package firecombat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


import jadex.bridge.IInternalAccess;
import jadex.bridge.nonfunctional.INFProperty;
import jadex.bridge.nonfunctional.INFPropertyMetaInfo;
import jadex.bridge.service.IServiceIdentifier;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.bridge.service.annotation.ServiceStart;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.MethodInfo;
import jadex.commons.future.DelegationResultListener;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;



@Service
public class ChatService implements IChatService {
	/** The agent. */
	@ServiceComponent
	protected IInternalAccess agent;
	protected IClockService clock;
	protected DateFormat format;
 
	/**
	*  Receives a chat message.
	*  @param sender The sender's name.
	*  @param text The message text.
	*/
	
	public void message(final String sender,final String text) {
		System.out.println(agent.getComponentIdentifier().getLocalName()+" received at "
			+format.format(new Date(clock.getTime()))+" from: "+sender+" message: "+text);
	}
	
	@ServiceStart
	public IFuture<Void> startService(){
		format = new SimpleDateFormat("10:10:10");
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
