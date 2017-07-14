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

  @Test
  public void all_returnsListOfAllStylists_true() {
    Stylist testStylist = new Stylist("Tony Stark");
    testStylist.save();
    Stylist testStylist2 = new Stylist("Bruce Banner");
    testStylist2.save();
    assertEquals(testStylist2.getId(),Stylist.all().get(1).getId());
  }

  @Test
  public void listClients_returnsListOfClientsAssignedToStylist_true() {
    Stylist testStylist = new Stylist("Tony Stark");
    testStylist.save();
    Client testClient = new Client("Madonna");
    testClient.assignToStylist(testStylist.getId());
    testClient.save();
    Client testClient2 = new Client("Jack Sparrow");
    testClient2.assignToStylist(testStylist.getId());
    testClient2.save();
    assertEquals(testClient2.getId(), testStylist.listClients().get(1).getId());
  }

  @Test
  public void update_updatesStylistShopIdInDB_true() {
    Stylist testStylist = new Stylist("Tony Stark");
    testStylist.save();
    testStylist.setShopId(23);
    testStylist.update();
    assertEquals(23,Stylist.find(testStylist.getId()).getShopId());
  }

}
