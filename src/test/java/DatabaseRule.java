import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);

    //add our default none stylist as ID zero
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(id, name, phone, shopId) VALUES (0, 'none', 'none', 0)";
      con.createQuery(sql, true)
        .executeUpdate();
    }
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

}
