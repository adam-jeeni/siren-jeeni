package uk.co.jeeni.siren;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {
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
	 * 	toUrl("http://www.jeeni.co.uk/", "/world");
	 * 	toUrl("http://www.jeeni.co.uk/", "world");
	 *  toUrl("http://www.jeeni.co.uk", "/world");
	 *  toUrl("http://www.jeeni.co.uk", "world");
	 *  toUrl("http://www.jeeni.co.uk", "http://www.jeeni.co.uk/other");
	 * }
	 * </pre>
	 * 
	 * @param domain
	 * @param path
	 * @return
	 */
	public static URL toUrl(String domain, String path) {
		if(isUrl(path)){
			try {
				return new URL(path);
			} catch (MalformedURLException e) {
				// Can not happen because of previous check.
			}
		}
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
	
	public static URL[] buildUrls(String domain, String[] paths){
		if(paths == null || paths.length == 0){
			return new URL[0];
		}
		
		URL[] urls = new URL[paths.length];
		
		for(int i = 0; i < paths.length; i++){
			urls[i] = UrlUtils.toUrl(domain, paths[i]);
		}
		return urls;
	}
	
	public static boolean isUrl(String maybeUrl){
		try {
			new URL(maybeUrl);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

}
