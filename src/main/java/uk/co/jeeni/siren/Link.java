package uk.co.jeeni.siren;

import java.net.URL;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "rel", "href" })
public class Link {

	private final String[] rel;
	private final String strHRef;
	private URL href;
	
	public Link(String[] rel, String href) {
		super();
		this.rel = rel;
		this.strHRef = href;
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
	
	void buildUrl(String domain){
		href = UrlUtils.toUrl(domain, strHRef);
	}
}
