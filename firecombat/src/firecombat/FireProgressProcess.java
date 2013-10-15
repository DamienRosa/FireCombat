package firecombat;

import java.util.Set;

import jadex.bridge.service.types.clock.IClockService;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.ISpaceProcess;

public class FireProgressProcess implements ISpaceProcess {

	@Override
	public void execute(IClockService arg0, IEnvironmentSpace env) {
		ISpaceObject[] fire = env.getSpaceObjectsByType("fire");
		
	}

	@Override
	public void start(IClockService arg0, IEnvironmentSpace arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getProperty(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<?> getPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasProperty(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setProperty(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown(IEnvironmentSpace arg0) {
		// TODO Auto-generated method stub

	}

}
