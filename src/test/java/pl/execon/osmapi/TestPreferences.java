package pl.execon.osmapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import pl.execon.osmapi.util.Preferences;
import pl.execon.osmapi.util.Preferences.Mode;

public class TestPreferences {
  @Test
  public void testGetInstance() throws IOException {
    Preferences prefs=Preferences.getInstance();
    assertNotNull(prefs);
    assertNotNull(Preferences.getPrefsXmlFile().getPath()+" needs user entry",prefs.getUser());
    assertNotNull(Preferences.getPrefsXmlFile().getPath()+" needs password entry",prefs.getPassword());
  }

  /**
   * test saving
   * @throws IOException
   */
  // uncomment and add your own data to create your preferences file
  // or cut&paste to some place where you would like to do so
  @Test
  public void testSave() throws IOException {
    Preferences.mode=Mode.Test;
    Preferences prefs=Preferences.getInstance();
    prefs.setUser("John Doe");
    prefs.setPassword("LetMeIn");
    prefs.save();
    Preferences.reinit();
    Preferences prefs2 = Preferences.getInstance();
    assertNotNull(prefs2);
    assertEquals(prefs.getUser(),prefs2.getUser());
    assertEquals(prefs.getPassword(),prefs2.getPassword());
  }
  
}
