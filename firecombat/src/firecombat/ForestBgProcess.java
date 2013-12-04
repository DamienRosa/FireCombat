package firecombat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.Vector2Double;
import jadex.extension.envsupport.math.Vector2Int;

public class ForestBgProcess  extends SimplePropertyObject implements ISpaceProcess{

	@Override
	public void execute(IClockService arg0, IEnvironmentSpace arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown(IEnvironmentSpace arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(IClockService arg0, IEnvironmentSpace arg1) {
		Space2D space = (Space2D)arg1;
		Map forest = new HashMap();
		Random rand = new Random();
		for (int x = 0; x < space.getAreaSize().getXAsInteger(); x++) {
			for (int y = 0; y < space.getAreaSize().getYAsInteger(); y++) {
				forest.put("position", new Vector2Int(x,y));
				forest.put("probability", 0.0);
				Integer r = rand.nextInt(2);
				forest.put("density", r.toString());
				forest.put("burning", false);
				space.createSpaceObject("forest", forest, null);
			}
			
		}
	}

}
