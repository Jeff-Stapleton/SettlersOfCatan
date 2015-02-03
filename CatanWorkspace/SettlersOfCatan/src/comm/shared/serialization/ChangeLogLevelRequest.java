package comm.shared.serialization;

public class ChangeLogLevelRequest {
	public transient final String ALL = "ALL";
	public transient final String SEVERE = "SEVERE";
	public transient final String WARNING = "WARNING";
	public transient final String INFO = "INFO";
	public transient final String CONFIG = "CONFIG";
	public transient final String FINE = "FINE";
	public transient final String FINER = "FINER";
	public transient final String OFF = "OFF";
	
	private String _logLevel;
	
	public ChangeLogLevelRequest(String logLevel)
	{
		_logLevel = logLevel;
	}
	
	public String getLogLevel()
	{
		return _logLevel;
	}
}
