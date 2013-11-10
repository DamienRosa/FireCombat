package firecombat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.Vector2Double;

public class BurnFireProcess extends SimplePropertyObject implements ISpaceProcess{

	@Override
	public void execute(IClockService arg0, IEnvironmentSpace arg1) {
		Space2D env = (Space2D)arg1;
		
		ISpaceObject[] space = arg1.getSpaceObjectsByType("fire");
		Random rand = new Random();
		for (ISpaceObject s : space) {

			double x = rand.nextDouble()*env.getAreaSize().getXAsDouble();
			double y = rand.nextDouble()*env.getAreaSize().getYAsDouble();
			s.setProperty("position", new Vector2Double(x,y));
		}
	}

	@Override
	public void shutdown(IEnvironmentSpace arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(IClockService arg0, IEnvironmentSpace env) {
		Space2D space = (Space2D) env;
		Random rand = new Random();
		
		double x = rand.nextDouble()*space.getAreaSize().getXAsDouble();
		double y = rand.nextDouble()*space.getAreaSize().getYAsDouble();
		
		
		Map map = new HashMap();
		map.put("position", new Vector2Double(x,y));
		space.createSpaceObject("fire", map, null);
	}

}
