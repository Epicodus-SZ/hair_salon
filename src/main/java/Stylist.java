import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;
  private String phone;
  private int shopId;

  //the constructor
  public Stylist(String name){
    this.name = name;
    this.phone = "000-000-0000";
    this.shopId = 0;
  }

  public int getId(){
    return this.id;
  }
  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return client;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name, phone, shopId) VALUES (:name, :phone, :shopId);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("shopId", this.shopId)
        .executeUpdate()
        .getKey();
    }
  }

}
