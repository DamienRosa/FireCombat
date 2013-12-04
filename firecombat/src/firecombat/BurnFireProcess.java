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
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector1Int;
import jadex.extension.envsupport.math.Vector2Int;
import java.util.Iterator;

public class BurnFireProcess extends SimplePropertyObject implements
		ISpaceProcess {

	protected double lasttick;

//	public ISpaceObject getObjectByPos(int x, int y, Grid2D grid) {
//		Object[] objects = grid.getSpaceObjects();
//		for (Object object : objects) {
//			if (((ISpaceObject) object).getProperty("position").equals(
//					new Vector2Int(x, y))) {
//				return ((ISpaceObject) object);
//			}
//		}
//		return null;
//	}
//
//	public ArrayList<ISpaceObject> getNeighbors(IVector2 pos, Grid2D grid) {
//		int x = pos.getXAsInteger();
//		int y = pos.getYAsInteger();
//
//		int w = grid.getAreaSize().getXAsInteger();
//		int h = grid.getAreaSize().getYAsInteger();
//
//		ArrayList<ISpaceObject> temp = new ArrayList<>();
//		temp.add(y > 0 ? getObjectByPos(x, y - 1, grid) : null);
//		temp.add(x < w && y > 0 ? getObjectByPos(x + 1, y - 1, grid) : null);
//		temp.add(x < w ? getObjectByPos(x + 1, y, grid) : null);
//		temp.add(x < w && y < h ? getObjectByPos(x + 1, y + 1, grid) : null);
//		temp.add(y < h ? getObjectByPos(x, y + 1, grid) : null);
//		temp.add(x > 0 && y < h ? getObjectByPos(x - 1, y + 1, grid) : null);
//		temp.add(x > 0 ? getObjectByPos(x - 1, y, grid) : null);
//		temp.add(x > 0 && y > 0 ? getObjectByPos(x - 1, y - 1, grid) : null);
//		return temp;
//	}

	@Override
	public void execute(IClockService arg0, IEnvironmentSpace arg1) {
		Grid2D space = (Grid2D) arg1;

		ISpaceObject[] fire = space.getSpaceObjectsByType("fire");

		if (lasttick + 2  < arg0.getTick()) {
			lasttick += 3;

			for (ISpaceObject flame : fire) {

				Set neighbors = space.getNearObjects((IVector2) flame.getProperty("position"),	new Vector1Int(2), "forest");

				for (Iterator iterator = neighbors.iterator(); iterator.hasNext();) {
					ISpaceObject neighbor = (ISpaceObject) iterator.next();
					if (!((boolean) neighbor.getProperty("burning"))) {
						neighbor.setProperty("probability", 0.6);
					}
				}
				if (((int) flame.getProperty("intensity")) != 3) {
					flame.setProperty("intensity",
							((int) flame.getProperty("intensity")) + 1);
				}
				ISpaceObject tree_pos = (ISpaceObject) space
						.getSpaceObjectsByGridPosition(
								(IVector2) flame.getProperty("position"),
								"forest").iterator().next();
				tree_pos.setProperty("probability", 1.0);
			}

			ISpaceObject[] forest = space.getSpaceObjectsByType("forest");
			for (ISpaceObject tree : forest) {
				if (tree.getProperty("probability").equals(0.6)) {
					Map flame = new HashMap<>();
					flame.put("position", tree.getProperty("position"));
					flame.put("intensity", 1);
					space.createSpaceObject("fire", flame, null);

					tree.setProperty("burning", true);
				}
			}
		}
	}

	@Override
	public void shutdown(IEnvironmentSpace arg0) {

	}

	@Override
	public void start(IClockService arg0, IEnvironmentSpace arg1) {

		lasttick = arg0.getTick();

		Grid2D space = (Grid2D) arg1;

		Random rand = new Random();

		int x = rand.nextInt(space.getAreaSize().getXAsInteger());
		int y = rand.nextInt(space.getAreaSize().getYAsInteger());
		Vector2Int n_pos = new Vector2Int(x, y);

		Map init_fire = new HashMap<>();
		init_fire.put("position", n_pos);
		init_fire.put("intensity", 1);
		space.createSpaceObject("fire", init_fire, null);

		ISpaceObject tree_pos = (ISpaceObject) space
				.getSpaceObjectsByGridPosition(n_pos, "forest").iterator()
				.next();
		tree_pos.setProperty("probability", 0.6);
		tree_pos.setProperty("bruning", true);
	}

}
