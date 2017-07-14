import org.sql2o.*;
import java.util.List;


public class Stylist {
  private int id;
  private String name;
  private String phone;
  private int shopId;

  //the constructor
  public Stylist(String name){
    this.name = name;
    this.phone = "000-000-0000";
    this.shopId = 1;
  }

//getters////////////////////////////////////////////
  public int getId(){
    return this.id;
  }

  public int getShopId(){
    return this.shopId;
  }

  public String getName(){
    return this.name;
  }

//setters///////////////////////////////////////////
  public void setShopId(int id){
    this.shopId = id;
  }


  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getShopId() == newStylist.getShopId();
    }
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

  public static List<Stylist> all() {
   String sql = "SELECT id, name, phone, shopId FROM stylists";
   try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Stylist.class);
   }
 }

 public static void delete(int id) {
   try(Connection con = DB.sql2o.open()) {
     //first reassign users to stylist 0
     String reassignUserSQL = "UPDATE clients SET stylistId = 0 WHERE stylistId = :id";;
     con.createQuery(reassignUserSQL)
       .addParameter("id", id)
       .executeUpdate();

     String deleteStylistSQL = "DELETE FROM stylists WHERE id = :id;";
     con.createQuery(deleteStylistSQL)
       .addParameter("id", id)
       .executeUpdate();
   } //end of try
 }

 public List<Client> listClients() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients WHERE stylistId=:stylistId";
    return con.createQuery(sql)
      .addParameter("stylistId", this.id)
      .executeAndFetch(Client.class);
  }
}

public void update() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE stylists SET phone = :phone, shopId = :shopId WHERE id = :id";
    con.createQuery(sql)
    .addParameter("phone", this.phone)
    .addParameter("shopId", this.shopId)
      .addParameter("id", this.id)
      .executeUpdate();
  }
}

}
