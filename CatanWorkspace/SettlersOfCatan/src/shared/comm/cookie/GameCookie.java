package shared.comm.cookie;

public class GameCookie implements ICookie
{
	Integer gameNumber = null;
	
	public GameCookie(int gameNumber)
	{
		this.gameNumber = gameNumber;
	}

	@Override
	public String getCookie()
	{
		StringBuilder cookie = new StringBuilder();
		cookie.append("catan.game=").append(gameNumber).append(";");
		
		return cookie.toString();
	}

}
