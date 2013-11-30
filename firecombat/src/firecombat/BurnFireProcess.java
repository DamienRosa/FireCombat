package firecombat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;
import jadex.extension.envsupport.math.Vector2Int;

public class BurnFireProcess extends SimplePropertyObject implements
		ISpaceProcess {

	public ISpaceObject getObjectByPos(IVector2 pos, Space2D space) {
		Object[] objects = space.getSpaceObjects();
		for (Object object : objects) {
			if (((ISpaceObject) object).getProperty("position").equals(pos)) {
				return ((ISpaceObject) object);
			}
		}
		return null;
	}

	private ArrayList<ISpaceObject> expandToNeighbords(IVector2 pos,
			Space2D space) {
		int x = pos.getXAsInteger();
		int y = pos.getYAsInteger();

		int width = space.getAreaSize().getXAsInteger();
		int height = space.getAreaSize().getYAsInteger();

		ArrayList<ISpaceObject> temp = new ArrayList<ISpaceObject>();

		temp.add(getObjectByPos(new Vector2Double(x, y - 1), space) != null	&& y > 0 ? getObjectByPos(new Vector2Double(x, y - 1), space): null);
		temp.add(getObjectByPos(new Vector2Double(x + 1, y - 1), space) != null	&& y > 0 && x <= width ? getObjectByPos(new Vector2Double(x + 1, y - 1), space) : null);
		temp.add(getObjectByPos(new Vector2Double(x + 1, y), space) != null	&& x <= width ? getObjectByPos(new Vector2Double(x + 1, y),	space) : null);
		temp.add(getObjectByPos(new Vector2Double(x + 1, y + 1), space) != null	&& x <= width && y <= height ? getObjectByPos(new Vector2Double(x + 1, y + 1), space) : null);
		temp.add(getObjectByPos(new Vector2Double(x, y + 1), space) != null	&& y <= height ? getObjectByPos(new Vector2Double(x, y + 1),space) : null);
		temp.add(getObjectByPos(new Vector2Double(x - 1, y + 1), space) != null	&& y <= height && x > 0 ? getObjectByPos(new Vector2Double(	x - 1, y + 1), space) : null);
		temp.add(getObjectByPos(new Vector2Double(x - 1, y), space) != null	&& x > 0 ? getObjectByPos(new Vector2Double(x - 1, y), space): null);
		temp.add(getObjectByPos(new Vector2Double(x - 1, y - 1), space) != null	&& x > 0 && y > 0 ? getObjectByPos(new Vector2Double(x - 1,	y - 1), space) : null);
		return temp;
	}

	@Override
	public void execute(IClockService arg0, IEnvironmentSpace arg1) {
		Space2D space = (Space2D) arg1;

		ISpaceObject[] fire = space.getSpaceObjectsByType("fire");

		for (ISpaceObject flame : fire) {

			ArrayList<ISpaceObject> neightbors = expandToNeighbords(
					(Vector2Double) flame.getProperty("position"), space);
			for (int i = 0; i < neightbors.size(); i++) {
				if (neightbors.get(i) != null && neightbors.get(i).getType().equals("forest")) {
					neightbors.get(i).setProperty("probability", 0.6);
				}
			}
			flame.setProperty("intensity", "2");
			ISpaceObject tree_pos = getObjectByPos((IVector2) flame.getProperty("position"), space);
			tree_pos.setProperty("probability", 1.0);
		}
		
		ISpaceObject[] forest = space.getSpaceObjectsByType("forest");
		for(ISpaceObject tree : forest){
			if(tree.getProperty("probability").equals(0.6)){
				Map flame = new HashMap<>();
				flame.put("position", tree.getProperty("position"));
				flame.put("intensity", "1");
				space.createSpaceObject("fire", flame, null);
			}
		}
	}

	@Override
	public void shutdown(IEnvironmentSpace arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(IClockService arg0, IEnvironmentSpace arg1) {
		Space2D space = (Space2D) arg1;

		Random rand = new Random();

		int x = rand.nextInt(space.getAreaSize().getXAsInteger());
		int y = rand.nextInt(space.getAreaSize().getYAsInteger());
		Vector2Int n_pos = new Vector2Int(x, y);

		Map init_fire = new HashMap<>();
		init_fire.put("position", n_pos);
		init_fire.put("intensity", "1");
		space.createSpaceObject("fire", init_fire, null);

		ISpaceObject tree_pos = getObjectByPos(n_pos, space);
		tree_pos.setProperty("probability", 0.6);
	}

}
