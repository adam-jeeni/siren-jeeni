package uk.co.jeeni.siren;

import java.net.URL;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@JsonPropertyOrder({ "class", "rel", "href", "properties", "entities", "actions", "links" })
public class SubEntity extends Entity {

	private final String[] strUrlRelLinks;
	private final String strHRef;
	
	private URL[] relLinks;
	private URL href;

	public SubEntity(String[] classes, String selfUrl, String[] relLinks) {
		super(classes);
		this.strHRef = selfUrl;
		this.strUrlRelLinks = relLinks;
	}
	
	public SubEntity(String[] classes, String[] relLinks) {
		super(classes);
		this.strUrlRelLinks = relLinks;
		this.strHRef = null;
	}

	public final URL[] getRel() {
		return relLinks;
	}
	
	public final URL getHref() {
		return href;
	}
	
	final void buildUrls(String domain){
		if(strUrlRelLinks != null && strUrlRelLinks.length > 0){
			relLinks = UrlUtils.buildUrls(domain, strUrlRelLinks);
		}
		if(strHRef != null){
			href = UrlUtils.toUrl(domain, strHRef);
		}
		super.buildLinkUrls(domain);
	}
}
