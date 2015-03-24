package shared.comm.cookie;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class CookieFactory
{
	private static final Logger log = Logger.getLogger(CookieFactory.class);
	private static Gson gson = new Gson();
	public static ICookie parseCookie(String key, String value)
	{
		log.trace("Parsing cookie");
		switch(key)
		{
		case "catan.user":
			log.trace("Parsing player cookie");
			PlayerCookie cookie = gson.fromJson(value, PlayerCookie.class);
			return cookie;
		case "catan.game":
			log.trace("Parsing game cookie");
			return new GameCookie(Integer.valueOf(value));
		}
		log.debug("Unrecognized cookie key \"" + key + "\"");
		return null;
	}

}
