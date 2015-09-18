package pl.execon.osmapi.dto.osm;

import org.simpleframework.xml.Attribute;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class OSMTag {
	
	@JacksonXmlProperty(isAttribute=true)
	@Attribute
	private String k;
	
	@JacksonXmlProperty(isAttribute=true)
	@Attribute
	private String v;
	
	public OSMTag(){		
	}
	
	public OSMTag(String k, String v){
		this.k = k;
		this.v = v;
	}
	
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	
	
}
