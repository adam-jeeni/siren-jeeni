package uk.co.jeeni.siren;

import java.net.MalformedURLException;
import java.net.URL;

public class SirenUtils {
	public static URL checkURL(String strUrl) throws SirenExcepton{
		try {
			return new URL(strUrl);
		} catch (MalformedURLException e) {
			throw new SirenExcepton(e.getMessage(), e);
		}
	}

}
