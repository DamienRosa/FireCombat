package world;

public class Location {

	public static final double DEFAULT_TOLERANCE = 0.001;
	protected double x;
	protected double y;

	public Location(){
	}

	public Location(double x, double y)
	{
		setX(x);
		setY(y);
	}

	public double getX()
	{
		return this.x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return this.y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	//-------- object methods --------

	/**
	 *  Get a string representation of this Location.
	 *  @return The string representation.
	 */
	public String toString()
	{
		return "Location(" + "x=" + getX() + ", y=" + getY() + ")";
	}

	//-------- custom code --------

	/**
	 *  Caculate is a location is near this location.
	 *  @return The distance.
	 */
	public double getDistance(Location other)
	{
		assert other != null;
		return Math.sqrt((other.y - this.y) * (other.y - this.y) + (other.x - this.x) * (other.x - this.x));
	}

	/**
	 *  Check, if two locations are near to each other
	 *  using the default tolerance.
	 *  @return True, if two locations are near to each other.
	 */
	public boolean isNear(Location other)
	{
		return isNear(other, DEFAULT_TOLERANCE);
	}

	/**
	 *  Check, if two locations are near to each other.
	 *  @param tolerance	The distance, when two locations are considered near.
	 *  @return True, if two locations are near to each other.
	 */
	public boolean isNear(Location other, double tolerance)
	{
		return getDistance(other) <= tolerance;
	}

	/**
	 *  Test if two instances are equal.
	 *  @return True, if equal.
	 */
	public boolean equals(Object o)
	{
		boolean ret = false;
		if(o instanceof Location)
		{
			Location loc = (Location)o;
			if(loc.x == x && loc.y == y)
				ret = true;
		}
		return ret;
	}

	/**
	 *  Clone the object.
	 */
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			assert false;
			throw new RuntimeException("Clone not supported");
		}
	}
}
