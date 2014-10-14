package uk.co.jeeni.siren;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "class", "rel", "href"})
public class LinkToSubEntity implements ISirenEntity{

	@JsonProperty("class")
	public String[] classes;
	private String[] rel;
	private String href;
	
	public LinkToSubEntity(String[] classes, String[] rel, String href) {
		super();
		this.classes = classes;
		this.rel = rel;
		this.href = href;
	}

	public final String[] getClasses() {
		return classes;
	}

	public final String[] getRel() {
		return rel;
	}

	public final String getHref() {
		return href;
	}

}
