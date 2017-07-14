package model;

import org.sql2o.Connection;

import java.util.Map;

/**
 * Created by mrchebik on 7/14/17.
 */
public abstract class Person {
    private   int id;
    protected int personId;
    protected String name;
    protected String phone;
    protected Map<String, String> sqlQueries;
    protected Class typeOfPerson;

    public Object find(int id) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sqlQueries.get("find"))
                    .addParameter("id", id)
                    .executeAndFetchFirst(typeOfPerson);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            this.id = (int) con.createQuery(sqlQueries.get("save"), true)
                    .addParameter("name", this.name)
                    .addParameter("phone", this.phone)
                    .addParameter("stylistId", this.personId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update() {
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sqlQueries.get("update"))
                    .addParameter("name", this.name)
                    .addParameter("phone", this.phone)
                    .addParameter("stylistId", this.personId)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }

    public int getId() {
        return this.id;
    }
}
