package soldier;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalMaintainCondition;
import jadex.bdiv3.annotation.GoalTargetCondition;
import jadex.bdiv3.annotation.Deliberation;

@Goal(deliberation=@Deliberation(inhibits=ExtinguishFirePlan.class))
public class MaintainSelfSecurity extends SoldierBDI {
	/**
	 *  When the security level is below 0.2
	 *  the soldier will activate this goal.
	 */
	@GoalMaintainCondition(beliefs="fire_distance")
	public boolean checkSecurity()
	{
		return (fire_distance > 0.8 && wind_speed <= 10) || 
			   (fire_distance > 0.5 && (wind_speed > 10 && wind_speed <= 50)) ||
			   (fire_distance > 0.2 && wind_speed > 50);
	}// falta incluir a wind_orientation e o vegetation_state
	
	/**
	 *  The target condition determines when
	 *  the goal goes back to idle. 
	 */
	@GoalTargetCondition(beliefs="fire_distance")
	public boolean checkTarget()
	{
		return fire_distance >= 0.9;
	}
}
