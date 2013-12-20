package commander;

import jadex.bdiv3.annotation.Goal;
import jadex.extension.envsupport.math.IVector2;


@Goal/*(unique=true, deliberation=@Deliberation(inhibits=AchieveMoveToLocation.class))*/
public class AchieveSendSoldiers {

	private IVector2 position;
	
	public AchieveSendSoldiers(IVector2 position) {
		this.position = position;
	}
	
	public IVector2 getPosition(){
		return position;
	}
}
