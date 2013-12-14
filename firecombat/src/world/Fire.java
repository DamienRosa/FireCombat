package world;

import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;

import java.util.ArrayList;

public class Fire {
	
	public Fire() {
		
	}
	
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
}
