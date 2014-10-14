package uk.co.jeeni.siren;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class LinkDataField {

	public String name;
	public InputTypes type = InputTypes.TEXT;
	public String value;
	
	public LinkDataField(String name) {
		super();
		this.name = name;
	}
	
	public LinkDataField(String name, InputTypes type) {
		this(name);
		this.type = type;
	}
	
	public LinkDataField(String name, InputTypes type, String value) {
		this(name, type);
		this.value = value;
	}
}