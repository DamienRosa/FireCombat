package soldier;

import jadex.bdiv3.annotation.Goal;
import jadex.extension.envsupport.math.IVector2;

@Goal
public class AchieveExtinguishFire {
	
	private IVector2 combat_position;
	
	public AchieveExtinguishFire(IVector2 combat_position) {
		this.combat_position = combat_position;
	}
	
	public IVector2 getCombatPosition() {
		return combat_position;
	}
	
	public void setCombatPosition(IVector2 combat_position) {
		this.combat_position = combat_position;
	}
}
