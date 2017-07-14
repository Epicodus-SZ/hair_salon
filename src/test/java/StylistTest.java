import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Tony Stark");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void find_findsStylistInDB_true() {
    Stylist testStylist = new Stylist("Tony Stark");
    testStylist.save();
    Stylist dbStylist = Stylist.find(testStylist.getId());
    assertEquals(dbStylist.getId(), testStylist.getId());
  }

  @Test
  public void save_savesStylistIntoDB_true() {
    Stylist testStylist = new Stylist("Tony Stark");
    testStylist.save();
    Stylist dbStylist = Stylist.find(testStylist.getId());
    assertEquals(dbStylist.getId(), testStylist.getId());
  }
}
