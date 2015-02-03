package comm.shared.serialization;

public class CreateGameRequest {
	String _name;
	boolean _randomTiles;
	boolean _randomNumbers;
	boolean _randomPorts;
	
	public CreateGameRequest(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts)
	{
		_name = name;
		_randomTiles = randomTiles;
		_randomNumbers = randomNumbers;
		_randomPorts = randomPorts;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public boolean isRandomTiles() { return getRandomTiles(); }
	public boolean getRandomTiles()
	{
		return _randomTiles;
	}
	
	public boolean isRandomNumbers() { return getRandomNumbers(); }
	public boolean getRandomNumbers()
	{
		return _randomNumbers;
	}
	
	public boolean isRandomPorts() { return getRandomPorts(); }
	public boolean getRandomPorts()
	{
		return _randomPorts;
	}
}
