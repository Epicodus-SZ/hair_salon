import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private String phone;
  private int stylistId;

  //constructor
  public Client(String name, String phone, int stylistId){
    this.name = name;
    this.phone = phone;
    this.stylistId = stylistId;
  }

  //constructor2 with defaults
  public Client(String name){
    this(name, "000-000-0000", 0);
  }

  public int getId(){
    return this.id;
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, phone, stylistId) VALUES (:name, :phone, :stylistId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public void assignToStylist(int stylistId){
    this.stylistId = stylistId;
  }

}
