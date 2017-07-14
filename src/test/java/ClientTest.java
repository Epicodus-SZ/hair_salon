import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
    public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client testClient = new Client("Hulk Hogan");
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void find_findsClientInDB_true() {
    Client testClient = new Client("Hulk Hogan");
    testClient.save();
    Client dbClient = Client.find(testClient.getId());
    assertEquals(dbClient.getId(), testClient.getId());
  }

  @Test
  public void save_savesClientIntoDB_true() {
    Client testClient = new Client("Steve Zaske");
    testClient.save();
    Client dbClient = Client.find(testClient.getId());
    assertEquals(dbClient.getId(), testClient.getId());
  }

  @Test
  public void update_updatesClientStylistInDB_true() {
    Client testClient = new Client("Hulk Hogan");
    testClient.save();
    testClient.assignToStylist(7);
    testClient.update();
    assertEquals(7,Client.find(testClient.getId()).getStylistId());
  }

}
