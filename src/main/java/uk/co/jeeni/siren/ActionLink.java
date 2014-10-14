package uk.co.jeeni.siren;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActionLink {
	public String name;
	public String title;
	public HttpMethod method = HttpMethod.GET;
	public URL href;
	public String type = "application/x-www-form-urlencoded";
	public LinkDataField[] fields;
	
	public ActionLink(String name, String title, String href) {
		super();
		this.name = name;
		this.title = title;
		this.href = SirenUtils.checkURL(href);
	}
	
	public ActionLink(String name, String title, String href, HttpMethod method) {
		this(name, title, href);
		this.method = method;
	}

	public ActionLink setMethod(HttpMethod method){
		this.method = method;
		return this;
	}
	
	public ActionLink addDataField(LinkDataField field){
		final Set<LinkDataField> links = new HashSet<LinkDataField>();
		
		if(fields != null && fields.length > 0){
			Collections.addAll(links, fields);
		}
		links.add(field);
		fields = links.toArray(new LinkDataField[links.size()]);
		return this;
	}
	
	public ActionLink setFormType(String type){
		this.type = type;
		return this;
	}
}
