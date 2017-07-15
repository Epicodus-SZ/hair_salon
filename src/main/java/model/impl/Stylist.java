package model.impl;

import model.Person;
import org.sql2o.Connection;

import java.util.HashMap;
import java.util.List;


public class Stylist extends Person {
    public Stylist(String name) {
        super.name = name;
        super.phone = "000-000-0000";
        super.personId = 0;

        super.sqlQueries = new HashMap();
        sqlQueries.put("find", "SELECT * FROM stylists where id=:id");
        sqlQueries.put("save", "INSERT INTO stylists(name, phone, shopId) VALUES (:name, :phone, :shopId);");
        sqlQueries.put("update", "UPDATE stylists SET phone = :phone, shopId = :shopId WHERE id = :id");

        super.typeOfPerson = Stylist.class;
    }

    protected static List<Stylist> all() {
        String sql = "SELECT id, name, phone, shopId FROM stylists";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

    protected List<Client> listClients() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients WHERE stylistId=:stylistId";
            return con.createQuery(sql)
                    .addParameter("stylistId", super.personId)
                    .executeAndFetch(Client.class);
        }
    }

    public int getShopId() {
        return super.personId;
    }

    public void setShopId(int id) {
        super.personId = id;
    }
}
