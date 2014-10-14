package uk.co.jeeni.siren;

import java.net.URL;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "rel", "href" })
public class Link {

	private String[] rel;
	private URL href;
	
	public Link(String[] rel, String href) {
		super();
		this.rel = rel;
		this.href = SirenUtils.checkURL(href);
	}
	
	public Link(String selfHref) {
		this(new String[] {"self"}, selfHref);
	}

	public final String[] getRel() {
		return rel;
	}

	public final URL getHref() {
		return href;
	}
}
