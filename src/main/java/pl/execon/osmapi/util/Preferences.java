package pl.execon.osmapi.util;

import java.io.File;
import java.io.IOException;

public class Preferences {
  public final static String VERSION = "v1.0";

  public static enum Mode {
    Production, Development, Test
  };

  public static Mode mode = Mode.Production;
  public final static String ENDPOINT_SEARCH_API_BASE_URL = "osmapi.execon.pl/search/address";
  public final static String ENDPOINT_ROUTING_API_BASE_URL = "osmapi.execon.pl/routing/viaroute";
  public final static String ENDPOINT_PLACES_API_BASE_URL = "osmapi.execon.pl:8085/places";
  public final static String ENDPOINT_WEBSNAP_API_BASE_URL = "websnap.execon.pl/c.php";

  public final static int WEBSNAP_DEFAULT_WIDTH = 300; // pixels
  public final static int WEBSNAP_DEFAULT_HEIGHT = 225; // pixels
  public final static String WEBSNAP_DEFAULT_CONTENT_TYPE = "image/png";

  public final static String NAME = "OSMAPIJavaClient";
  public final static String ENCODING = "UTF-8";
  public static final String ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL = "https://api06.dev.openstreetmap.org/api/0.6"; // DEVELOPMENT
  // public static final String DEVELOPMENT_OSM_CREDENTIALS_LOGIN =
  // "PLEASE_PROVIDE_LOGIN"; // DEVELOPMENT
  // public static final String DEVELOPMENT_OSM_CREDENTIALS_PASS =
  // "PLEASE_PROVIDE_PASS"; // DEVELOPMENT
  // public static final String ENDPOINT_OSM_API_V06_ELEMENT_BASE_URL =
  // "https://api.openstreetmap.org/api/0.6"; // PRODUCTION
  public static final String ENDPOINT_OSM_API_V06_DEFAULT_COMMENT = "Created by OSMAPI call"; // DEVELOPMENT

  String user;
  String password;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private static Preferences instance = null;

  /**
   * default constructor
   */
  public Preferences() {
    instance = this;
  }

  public static File getPrefsXmlFile() {
    File prefsFile = XMLStorage.getXmlFile("preferences", "preferences_%s",
        mode.toString());
    return prefsFile;
  }
  
  /**
   * save the preferences
   * @throws IOException
   */
  public void save() throws IOException {
    EncodeDecoderUtils<Preferences> encoder = new EncodeDecoderUtils<Preferences>(
        Preferences.class);
    encoder.save(getPrefsXmlFile(), this);
  }

  /**
   * get an instance of the preferences
   * 
   * @return the instance
   * @throws IOException
   */
  public static Preferences getInstance() throws IOException {
    if (instance == null) {
      File prefsFile = getPrefsXmlFile();

      EncodeDecoderUtils<Preferences> decoder = new EncodeDecoderUtils<Preferences>(
          Preferences.class);
      instance = decoder.fromXML(prefsFile);
    }
    return instance;
  }
  
  /**
   * reinitialize my instance
   */
  public static void reinit() {
    instance=null;
  }

}
