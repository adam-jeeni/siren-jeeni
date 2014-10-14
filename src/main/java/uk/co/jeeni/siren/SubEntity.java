package uk.co.jeeni.siren;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@JsonPropertyOrder({ "class", "rel", "href", "properties", "entities", "actions", "links" })
public class SubEntity extends Entity {

	private final String[] rel;
	private final String href;
	
	public SubEntity(String[] classes, String selfUrl, String[] relLinks) {
		super(classes);
		this.href = selfUrl;
		this.rel = relLinks;
	}
	
	public SubEntity(String[] classes, String[] relLinks) {
		super(classes);
		this.rel = relLinks;
		this.href = null;
	}
	
	public final String[] getRel() {
		return rel;
	}
	
	public final String getHref() {
		return href;
	}

}
