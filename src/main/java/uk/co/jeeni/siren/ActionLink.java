package uk.co.jeeni.siren;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({"strHRef"})
public class ActionLink {
	private final String name;
	private final String title;
	private final String strHRef;

	private URL href;
	
	public HttpMethod method = HttpMethod.GET;
	private String type = "application/x-www-form-urlencoded";
	private DataField[] fields;
	
	public ActionLink(String name, String title, String href) {
		super();
		this.name = name;
		this.title = title;
		this.strHRef = href;
	}
	
	public ActionLink(String name, String title, String href, HttpMethod method) {
		this(name, title, href);
		this.method = method;
	}
	
	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getStrHRef() {
		return strHRef;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public URL getHref() {
		return href;
	}

	public String getType() {
		return type;
	}

	public DataField[] getFields() {
		return fields;
	}

	public ActionLink setMethod(HttpMethod method){
		this.method = method;
		return this;
	}
	
	public ActionLink addDataField(DataField field){
		final Set<DataField> links = new HashSet<DataField>();
		
		if(fields != null && fields.length > 0){
			Collections.addAll(links, fields);
		}
		links.add(field);
		fields = links.toArray(new DataField[links.size()]);
		return this;
	}
	
	public ActionLink setFormType(String type){
		this.type = type;
		return this;
	}
	
	final void buildUrls(String domain){
		href = UrlUtils.toUrl(domain, strHRef);
	}
}
