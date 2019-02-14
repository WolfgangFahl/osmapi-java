package pl.execon.osmapi;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pl.execon.osmapi.util.Preferences;
import pl.execon.osmapi.util.Preferences.Mode;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestPreferences.class,OSMElementAPITest.class, PlacesAPITest.class,
    RoutingAPITest.class,SearchAPITest.class })
/**
 * TestSuite
 * 
 * @author wf
 *
 *         no content necessary - annotation has info
 */
public class TestSuite {
  public static boolean debug = false;
  @BeforeClass
  public static void setUp() {
      Preferences.mode=Mode.Development;
  }
}
