package uk.co.jeeni.siren;

import org.junit.Test;
import static org.junit.Assert.*; 

public class SirenUtilsTest {

	@Test
	public void combineDomainAndPathTest(){
		
		assertEquals("http://www.jeeni.co.uk/world", UrlUtils.toUrl("http://www.jeeni.co.uk/", "/world").toString());
		assertEquals("http://www.jeeni.co.uk/world", UrlUtils.toUrl("http://www.jeeni.co.uk/", "world").toString());
		assertEquals("http://www.jeeni.co.uk/world", UrlUtils.toUrl("http://www.jeeni.co.uk", "/world").toString());
		assertEquals("http://www.jeeni.co.uk/world", UrlUtils.toUrl("http://www.jeeni.co.uk", "world").toString());
		assertEquals("http://www.jeeni.co.uk/other", UrlUtils.toUrl("http://www.jeeni.co.uk", "http://www.jeeni.co.uk/other").toString());
	}
}
