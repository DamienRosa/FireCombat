package firecombat;

import java.text.DateFormat;
import java.util.Date;

import jadex.bridge.IInputConnection;
import jadex.bridge.IInternalAccess;
import jadex.bridge.IOutputConnection;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.bridge.service.types.chat.IChatService;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.future.IFuture;
import jadex.commons.future.ITerminableFuture;
import jadex.commons.future.ITerminableIntermediateFuture;

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
	

	@Override
	public IFuture<String> getNickName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFuture<byte[]> getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFuture<String> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFuture<Void> status(String nick, String status, byte[] image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITerminableIntermediateFuture<Long> sendFile(String nick,
			String filename, long size, String id, IInputConnection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITerminableFuture<IOutputConnection> startUpload(String nick,
			String filename, long size, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFuture<Void> message(String sender, String text,
			boolean privatemessage) {
		
		System.out.println(agent.getComponentIdentifier().getLocalName()+" received at "
				+format.format(new Date(clock.getTime()))+" from: "+sender+" message: "+text);
		return null;
	}
	
	public void message(final String sender,final String text) {
		System.out.println(agent.getComponentIdentifier().getLocalName()+" received at "
			+format.format(new Date(clock.getTime()))+" from: "+sender+" message: "+text);
	}
}
