package comm.shared.serialization;

public class CreateGameRequest {
	String name;
	boolean randomTiles;
	boolean randomNumbers;
	boolean randomPorts;
	
	public CreateGameRequest(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts)
	{
		this.name = name;
		this.randomTiles = randomTiles;
		this.randomNumbers = randomNumbers;
		this.randomPorts = randomPorts;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isRandomTiles() { return getRandomTiles(); }
	public boolean getRandomTiles()
	{
		return randomTiles;
	}
	
	public boolean isRandomNumbers() { return getRandomNumbers(); }
	public boolean getRandomNumbers()
	{
		return randomNumbers;
	}
	
	public boolean isRandomPorts() { return getRandomPorts(); }
	public boolean getRandomPorts()
	{
		return randomPorts;
	}
}
