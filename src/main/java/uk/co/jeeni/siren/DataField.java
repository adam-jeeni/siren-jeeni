package uk.co.jeeni.siren;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataField {

	public String name;
	public InputTypes type = InputTypes.TEXT;
	public String value;
	
	public DataField(String name) {
		super();
		this.name = name;
	}
	
	public DataField(String name, InputTypes type) {
		this(name);
		this.type = type;
	}
	
	public DataField(String name, InputTypes type, String value) {
		this(name, type);
		this.value = value;
	}
}