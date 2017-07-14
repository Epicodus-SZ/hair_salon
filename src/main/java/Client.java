import org.sql2o.*;
import java.util.List;


public class Client {
  private int id;
  private String name;
  private String phone;
  private int stylistId;
  private int shopId;

  //constructor
  public Client(String name, String phone, int stylistId){
    this.name = name;
    this.phone = phone;
    this.stylistId = stylistId;
    this.shopId = 1;
  }

  //constructor2 with defaults
  public Client(String name){
    this(name, "000-000-0000", 0);
  }

  //getters////////////////////////////////////////////
  public int getId(){
    return this.id;
  }

  public int getStylistId(){
    return this.stylistId;
  }

  //setters////////////////////////////////////////////
  public void assignToStylist(int stylistId){
    this.stylistId = stylistId;
  }

  //methods////////////////////////////////////////////
  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, phone, stylistId, shopId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, phone, stylistId, shopId) VALUES (:name, :phone, :stylistId, :shopId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("stylistId", this.stylistId)
        .addParameter("shopId", this.shopId)
        .executeUpdate()
        .getKey();
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, phone = :phone, stylistId = :stylistId, shopId = :shopId WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("stylistId", this.stylistId)
        .addParameter("shopId", this.shopId)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }



} //end of Client class
