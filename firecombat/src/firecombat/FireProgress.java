package firecombat;

import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Int;

public class FireProgress {
	public static int x = 0;
	public static int y = 0;

	public static Vector2Int getNextPositions(Space2D space) {

		IVector2 area = space.getAreaSize();
		
		
		
		return new Vector2Int((x+=2) , (y++));
	}
}
