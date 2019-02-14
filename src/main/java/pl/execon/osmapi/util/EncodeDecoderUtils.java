package pl.execon.osmapi.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import pl.execon.osmapi.dto.osm.OSMElement;

public class EncodeDecoderUtils<T> {
  protected static Logger LOGGER = Logger.getLogger("pl.execon.osmapi.util");
  private Class<T> clazz;

  /**
   * construct me for the given class
   * @param clazz - the class to be encoded/decoded
   */
  public EncodeDecoderUtils(Class<T> clazz) {
    this.clazz=clazz;
  }
  
  /**
   * convert the given t to xml
   * @param t
   * @return the XML representation
   */
  public String instanceToXML(T t){
    String stringRepresentation = null;
    try {
      Serializer xmlMapper = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
      StringWriter stringWriter = new StringWriter();
      xmlMapper.write(t, stringWriter);
      stringRepresentation = stringWriter.toString();
    } catch (Exception e) {
      LOGGER.log(Level.WARNING,"Error while serializing element: "+e.getMessage(),e);
    }
    return stringRepresentation;
  }
  
  /**
   * Converts string xml into T object instance
   * @param xml string xml to be transformed into element object
   * @return null on error, T instance on success
   */
  public T instanceFromXML(String xml){
    T t = null;
    try {
      Serializer xmlMapper = new Persister(new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>"));
      t = xmlMapper.read(clazz, xml);
      
    } catch (Exception e) {
      LOGGER.log(Level.WARNING,"Error while deserializing element: "+e.getMessage(),e);
    }
    return t;
  }
  
  /**
   * get a T from the given xmlFile
   * @param xmlFile
   * @return the T
   * @throws IOException 
   */
  public T fromXML(File xmlFile) throws IOException {
    String xml=FileUtils.readFileToString(xmlFile, "UTF-8");
    T t=this.instanceFromXML(xml);
    return t;
  }
  
  /**
   * save the given t to the given xml file
   * @param xmlFile
   * @param t
   * @throws IOException
   */
  public void save(File xmlFile, T t) throws IOException {
    String xml=this.instanceToXML(t);
    FileUtils.writeStringToFile(xmlFile, xml,"UTF-8");
  } 
  
  static EncodeDecoderUtils<OSMElement> eduInstance=null;
  
  /**
   * get an instance of the Utilities for OSMElements (for backward compatibility to old static version)
   * @return the EduInstance
   */
  public static EncodeDecoderUtils<OSMElement> getEduInstance() {
    if (eduInstance==null) {
      eduInstance=new EncodeDecoderUtils<OSMElement>(OSMElement.class);
    }
    return eduInstance;
  }
  
	/**
	 * Converts OSMElement object into its xml representation in accordance with
	 * OSM api specification
	 * @param osmElement element to be converted into string xml
	 * @return null on error, string representation of xml on success
	 */
	public static String toXML(OSMElement osmElement){
	  String xml=getEduInstance().instanceToXML(osmElement);
	  return xml;
	}
	
	/**
	 * Converts string xml into OSMElement object instance
	 * @param xml string xml to be transformed into element object
	 * @return null on error, element instance on success
	 */
	public static OSMElement fromXML(String xml){
		OSMElement osmElement = getEduInstance().instanceFromXML(xml);
		return osmElement;
	}
	
}
