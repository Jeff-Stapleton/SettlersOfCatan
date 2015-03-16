package shared.locations;

/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation extends HexLocation
{
	
//	private HexLocation hexLoc;
	private VertexDirection direction;
	
	public VertexLocation(int x, int y, /*HexLocation hexLoc, */VertexDirection dir)
	{
		super(x, y);
//		setHexLoc(hexLoc);
		setDirection(dir);
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
	
	public VertexDirection getDirection()
	{
		return direction;
	}
	
	private void setDirection(VertexDirection direction)
	{
		this.direction = direction;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
//		string.append("hexLoc : ").append(hexLoc.toString()).append(",\n");
		string.append("direction : ").append(direction.toString()).append("\n");
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
		result = prime * result + (super.hashCode());//((hexLoc == null) ? 0 : hexLoc.hashCode());
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
		VertexLocation other = (VertexLocation)obj;
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
			return false;
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this vertex location. Since
	 * each vertex has three different locations on a map, this method converts
	 * a vertex location to a single canonical form. This is useful for using
	 * vertex locations as map keys.
	 * 
	 * @return Normalized vertex location
	 */
	public VertexLocation getNormalizedLocation()
	{
		
		// Return location that has direction NW or NE
		
		switch (direction)
		{
			case NorthWest:
			case NorthEast:
				return this;
			case West:
//				return new VertexLocation(
//										  hexLoc.getNeighborLoc(EdgeDirection.SouthWest),
//										  VertexDirection.NorthEast);
				return new VertexLocation(getNeighborLoc(EdgeDirection.SouthWest).getX(),
										  getNeighborLoc(EdgeDirection.SouthWest).getY(),
										  VertexDirection.NorthEast);
			case SouthWest:
//				return new VertexLocation(
//										  hexLoc.getNeighborLoc(EdgeDirection.South),
//										  VertexDirection.NorthWest);
				return new VertexLocation(getNeighborLoc(EdgeDirection.South).getX(),
						  				  getNeighborLoc(EdgeDirection.South).getY(),
						  				  VertexDirection.NorthWest);
			case SouthEast:
//				return new VertexLocation(
//										  hexLoc.getNeighborLoc(EdgeDirection.South),
//										  VertexDirection.NorthEast);
				return new VertexLocation(getNeighborLoc(EdgeDirection.South).getX(),
						  				  getNeighborLoc(EdgeDirection.South).getY(),
						  				  VertexDirection.NorthEast);
			case East:
//				return new VertexLocation(
//										  hexLoc.getNeighborLoc(EdgeDirection.SouthEast),
//										  VertexDirection.NorthWest);
				return new VertexLocation(getNeighborLoc(EdgeDirection.SouthEast).getX(),
										  getNeighborLoc(EdgeDirection.SouthEast).getY(),
										  VertexDirection.NorthWest);
			default:
				assert false;
				return null;
		}
	}
}

