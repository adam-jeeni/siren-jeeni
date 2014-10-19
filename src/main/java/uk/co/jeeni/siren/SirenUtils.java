package uk.co.jeeni.siren;

import java.net.MalformedURLException;
import java.net.URL;

public class SirenUtils {
	public static URL checkURL(String strUrl) throws SirenExcepton {
		try {
			return new URL(strUrl);
		} catch (MalformedURLException e) {
			throw new SirenExcepton(e.getMessage(), e);
		}
	}

	/**
	 * Combines the following domain and path parts of a URL into a correctly
	 * formatted URL. (This is to allow users flexibility on how they refer to
	 * different parts of a URL.
	 * 
	 * For the URL http://www.jeeni.co.uk/world the inputs could be
	 * 
	 * 
	 * <pre>
	 * {@code
	 * 	combineDomainAndPath("http://www.jeeni.co.uk/", "/world");
	 * 	combineDomainAndPath("http://www.jeeni.co.uk/", "world");
	 *  combineDomainAndPath("http://www.jeeni.co.uk", "/world");
	 *  combineDomainAndPath("http://www.jeeni.co.uk", "world");
	 * }
	 * </pre>
	 * 
	 * 
	 * 
	 * @param domain
	 * @param path
	 * @return
	 */
	public static URL combineDomainAndPath(String domain, String path) {
		if (domain.endsWith("/") && path.startsWith("/")) {
			domain = domain.substring(0, domain.length() - 1);
			return checkURL(domain + path);
		} else if (domain.endsWith("/") && !path.startsWith("/")) {
			return checkURL(domain + path);
		} else if (!domain.endsWith("/") && path.startsWith("/")) {
			return checkURL(domain + path);
		} else {
			return checkURL(domain + "/" + path);
		}
	}

}
