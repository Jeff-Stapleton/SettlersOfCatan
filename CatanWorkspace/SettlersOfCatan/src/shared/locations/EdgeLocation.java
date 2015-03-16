package shared.locations;

/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation extends HexLocation
{
	
//	private HexLocation hexLoc;
	private EdgeDirection direction;
	
	public EdgeLocation(int x, int y, /*HexLocation hexLoc, */EdgeDirection dir)
	{
		super(x, y);
//		setHexLoc(hexLoc);
		setDir(dir);
	}
	
//	public HexLocation getHexLoc()
//	{
//		return hexLoc;
//	}
	
//	private void setHexLoc(HexLocation hexLoc)
//	{
//		if(hexLoc == null)
//		{
//			throw new IllegalArgumentException("hexLoc cannot be null");
//		}
//		this.hexLoc = hexLoc;
//	}
	
	public EdgeDirection getDir()
	{
		return direction;
	}
	
	private void setDir(EdgeDirection dir)
	{
		this.direction = dir;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
//		string.append("hexLoc : ").append(hexLoc.toString()).append(",\n");
		string.append("direction : ").append(direction.toString()).append(",\n");
		string.append("x : ").append(getX()).append(",\n");
		string.append("y : ").append(getY()).append("\n");
		
		string.append("}");
		
		return string.toString();
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + (super.hashCode());
//		result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		EdgeLocation other = (EdgeLocation)obj;
		if(direction != other.direction)
			return false;
//		if(hexLoc == null)
//		{
//			if(other.hexLoc != null)
//				return false;
//		}
//		else if(!hexLoc.equals(other.hexLoc))
//			return false;
		if (!(getX() == other.getX() && getY() == other.getY()))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this edge location. Since
	 * each edge has two different locations on a map, this method converts a
	 * hex location to a single canonical form. This is useful for using hex
	 * locations as map keys.
	 * 
	 * @return Normalized hex location
	 */
	public EdgeLocation getNormalizedLocation()
	{
		
		// Return an EdgeLocation that has direction NW, N, or NE
		
		switch (direction)
		{
			case NorthWest:
			case North:
			case NorthEast:
				return this;
			case SouthWest:
			case South:
			case SouthEast:
				return new EdgeLocation(getNeighborLoc(direction).getX(),
										getNeighborLoc(direction).getY(),
										direction.getOppositeDirection());
//				return new EdgeLocation(hexLoc.getNeighborLoc(dir),
//										dir.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}
}

