package commander;

import java.util.Set;

import world.Fire;
import world.Soldier;


import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Description;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;

@Agent
@Description("Agent responsible for giving orders to the soldiers.")
public class CommanderBDI {
	
	@Agent
	protected BDIAgent agent;
	
//	@Belief
//	protected IEnvironment environment = Environment.getInstance();
	
	@Belief
	private int wind_speed;
	@Belief
	private int wind_orientation;
	@Belief
	private Set<Fire> fire_location;
	@Belief
	private Set<?> fire_intensity; // modificar o tipo
	@Belief
	private Set<Integer> vegetation_state;
	@Belief
	private Set<Soldier> all_soldiers;
	@Belief
	private Set<Soldier> busy_soldiers;
	@Belief
	private double my_vision = 1; // não tenho a certeza do valor mas deduzo que 1 seja visão total
	
	// um objectivo deste agente é procurar por um incêncio (apenas pela modificação do ambiente)
	
	public int getWindSpeed() {
		return wind_speed;
	}
 
	public void setWindSpeed(int wind_speed) {
		this.wind_speed = wind_speed;
	}
	
	public int getWindOrientation() {
		return wind_orientation;
	}
 
	public void setWindOrientation(int wind_orientation) {
		this.wind_orientation = wind_orientation;
	}
	
	public Set<Fire> getFireLocation() {
		return fire_location;
	}
	
	public void setFireLocation(Set<Fire> fire) {
		this.fire_location = fire;
	}
	
	public Set<?> getFireIntensity() {
		return fire_intensity;
	}
	
	public void setFireIntensity(Set<?> fire_intensity) {
		this.fire_intensity = fire_intensity;
	}
	
	public Set<Integer> getVegetationState() {
		return vegetation_state;
	}
	
	public void setVegetationState(Set<Integer> vegetation_state) {
		this.vegetation_state = vegetation_state;
	}
	
	public Set<Soldier> getAllSoldiers() {
		return all_soldiers;
	}
	
	public void setAllSoldiers(Set<Soldier> all_soldiers) {
		this.all_soldiers = all_soldiers;
	}
	
	public Set<Soldier> getBusySoldiers() {
		return busy_soldiers;
	}
	
	public void setBusySoldiers(Set<Soldier> busy_soldiers) {
		this.busy_soldiers = busy_soldiers;
	}
	
//	@Plan(trigger=@Trigger(factchangeds="fire_location"))
//	protected void printMessage() {
//		System.out.println("Fire found!");
//	}
}
