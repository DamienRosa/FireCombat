package firecombat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector1Int;
import jadex.extension.envsupport.math.Vector2Double;
import jadex.extension.envsupport.math.Vector2Int;

public class BurnFireProcess1 extends SimplePropertyObject implements
		ISpaceProcess {

	@Override
	public void execute(IClockService arg0, IEnvironmentSpace arg1) {
		Space2D env = (Space2D) arg1;

		ISpaceObject[] spacefire = arg1.getSpaceObjectsByType("fire");
		ISpaceObject[] spaceforest = arg1.getSpaceObjectsByType("forest");

		Map mapfire = new HashMap();

		// env.getSpaceObject0(id)

		for (ISpaceObject forest : spaceforest) {
			if (forest.getProperty("probability").equals(0.6)) {
				mapfire.put("intensity", "2");
				mapfire.put("position",
						(IVector2) forest.getProperty("position"));
				env.createSpaceObject("fire", mapfire, null);
			} else if (forest.getProperty("probability").equals(1.0)) {
				mapfire.put("intensity", "3");
				mapfire.put("position",
						(IVector2) forest.getProperty("position"));
				env.createSpaceObject("fire", mapfire, null);
			} else if (forest.getProperty("probability").equals(0.2)) {
				mapfire.put("intensity", "1");
				mapfire.put("position",
						(IVector2) forest.getProperty("position"));
				env.createSpaceObject("fire", mapfire, null);
			}
		}

		for (ISpaceObject fire : spacefire) {
			if (fire.getProperty("intensity").equals("2")) {
				expandFire(env, ((Vector2Double) fire.getProperty("position")));
			}
		}

	}

	@Override
	public void shutdown(IEnvironmentSpace arg0) {
		// TODO Auto-generated method stub

	}

	public void start(IClockService arg0, IEnvironmentSpace env) {
		Space2D space = (Space2D) env;

		Random rand = new Random();

		int x = rand.nextInt(space.getAreaSize().getXAsInteger());
		int y = rand.nextInt(space.getAreaSize().getYAsInteger());

		Map map = new HashMap();
		map.put("position", new Vector2Int(x, y));
		map.put("intensity", "3");
		space.createSpaceObject("fire", map, null);

		expandFire(env, new Vector2Int(x, y));

	}

	public void expandFire(IEnvironmentSpace env, IVector2 pos) {

		Set set2 = ((Space2D) env).getNearObjects(pos, new Vector1Int(0));
		for (Object object : set2) {
			if (!((ISpaceObject) object).getType().equals("fire")) {
				((ISpaceObject) object).setProperty("probability", 1.0);
			}
		}
		
/*		Set set1 = ((Space2D) env).getNearObjects(pos, new Vector1Int(2));
		for (Object object : set1) {
			if (!((ISpaceObject) object).getType().equals("fire")) {
				if ((double) ((ISpaceObject) object).getProperty("probability") < 0.2) {
					((ISpaceObject) object).setProperty("probability", 0.2);
				}
			}
		}*/

		Set set = ((Space2D) env).getNearObjects(pos, new Vector1Int(1));
		for (Object object : set) {
			if (!((ISpaceObject) object).getType().equals("fire")) {
				if ((double) ((ISpaceObject) object).getProperty("probability") < 0.6) {
					((ISpaceObject) object).setProperty("probability", 0.6);
				}
			}
		}
	}
	
	
	
	
}
