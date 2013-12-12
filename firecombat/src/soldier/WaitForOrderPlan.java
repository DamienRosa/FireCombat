package soldier;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class WaitForOrderPlan {

	@PlanBody
	public void body(){
		while (true){
			System.out.println("Waiting for order...");
		}
	}
}
