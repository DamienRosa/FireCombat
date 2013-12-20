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
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;
import jadex.extension.envsupport.math.Vector2Int;

public class BurnFireProcess extends SimplePropertyObject implements
		ISpaceProcess {

	protected double lasttick;

	
	 public ArrayList<ISpaceObject> getNeighbors(IVector2 pos, Grid2D grid) {
		 int x = pos.getXAsInteger();
		 int y = pos.getYAsInteger();
		
		 int w = grid.getAreaSize().getXAsInteger();
		 int h = grid.getAreaSize().getYAsInteger();
		 ArrayList<ISpaceObject> temp = new ArrayList<ISpaceObject>();
		 temp.add( y > 0 ?  (ISpaceObject) grid.getSpaceObjectsByGridPosition(new Vector2Double(x,y-1), "forest").iterator().next() : null);
		 temp.add( x < w && y > 0 ? (ISpaceObject)grid.getSpaceObjectsByGridPosition(new Vector2Double(x+1,y-1), "forest").iterator().next() : null);
		 temp.add( x < w ? (ISpaceObject)grid.getSpaceObjectsByGridPosition(new Vector2Double(x+1,y), "forest").iterator().next() : null);
		 temp.add( x < w && y < h ? (ISpaceObject)grid.getSpaceObjectsByGridPosition(new Vector2Double(x+1,y+1), "forest").iterator().next(): null);
		 temp.add( y < h ? (ISpaceObject)grid.getSpaceObjectsByGridPosition(new Vector2Double(x,y+1), "forest").iterator().next() : null);
		 temp.add( x > 0 && y < h ? (ISpaceObject) grid.getSpaceObjectsByGridPosition(new Vector2Double(x-1,y+1), "forest").iterator().next() : null);
		 temp.add( x > 0 ? (ISpaceObject) grid.getSpaceObjectsByGridPosition(new Vector2Double(x-1,y), "forest").iterator().next() : null);
		 temp.add( x > 0 && y > 0 ?(ISpaceObject)  grid.getSpaceObjectsByGridPosition(new Vector2Double(x-1,y-1), "forest").iterator().next() : null);
		 return temp;
	 }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(IClockService arg0, IEnvironmentSpace arg1) {
		Grid2D space = (Grid2D) arg1;
		Random rng = new Random();
		ISpaceObject[] fire = space.getSpaceObjectsByType("fire");
		
		int sizex = space.getAreaSize().getXAsInteger();
		@SuppressWarnings("unused")
		int sizey = space.getAreaSize().getYAsInteger();

		if (lasttick + 2 < arg0.getTick()) {
			lasttick += 20;

			for (ISpaceObject flame : fire) {
				if(!(boolean) flame.getProperty("extinct")){
					ArrayList<ISpaceObject> neighbors = getNeighbors((IVector2) flame.getProperty("position"), space);
					for (int i = 0; i < neighbors.size(); i++) {
						Vector2Double pos = (Vector2Double) neighbors.get(i).getProperty("position");
						if(pos.getXAsInteger() > 0 && pos.getXAsInteger() < sizex && (pos.getYAsInteger() > 0 && pos.getYAsInteger() < sizex)){
							if (!((boolean) neighbors.get(i).getProperty("burning"))) {
								double prob = rng.nextDouble();
								if (prob >= 0.6 && prob < 0.7) {
									neighbors.get(i).setProperty("probability", 0.6);
								}
							}
						}
					}
					if (((int) flame.getProperty("intensity")) != 3) {
						flame.setProperty("intensity", ((int) flame.getProperty("intensity")) + 1);
					}
					ISpaceObject tree_pos = (ISpaceObject) space.getSpaceObjectsByGridPosition((IVector2) flame.getProperty("position"),"forest").iterator().next();
					tree_pos.setProperty("probability", 1.0);
				}
			}

			ISpaceObject[] forest = space.getSpaceObjectsByType("forest");
			
			for (ISpaceObject tree : forest) {
				@SuppressWarnings("unused")
				Vector2Double pos = (Vector2Double) tree.getProperty("position");
				if (tree.getProperty("probability").equals(0.6)) {
					
					Map flame = new HashMap<>();
					flame.put("position", tree.getProperty("position"));
					flame.put("intensity", 1);
					flame.put("extinct", false);
					space.createSpaceObject("fire", flame, null);

					tree.setProperty("burning", true);
				}
			}
		}
	}

	@Override
	public void shutdown(IEnvironmentSpace arg0) {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		init_fire.put("extinct", false);
		space.createSpaceObject("fire", init_fire, null);

		ISpaceObject tree_pos = (ISpaceObject) space
				.getSpaceObjectsByGridPosition(n_pos, "forest").iterator()
				.next();
		tree_pos.setProperty("probability", 0.6);
		tree_pos.setProperty("bruning", true);
	}

}