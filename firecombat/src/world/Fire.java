package world;

public class Fire {
	int x, y;
	int intensity;

	public Fire(int x, int y) {
		this.intensity = 0;

		setXY(x, y);
	}

	private void setXY(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}
	
	
}
