package model.impl;

import model.Person;

import java.util.HashMap;

public class Client extends Person {
    public Client(String name, String phone, int stylistId) {
        super.name = name;
        super.phone = phone;
        super.personId = stylistId;

        super.sqlQueries = new HashMap();
        sqlQueries.put("find", "SELECT * FROM clients where id=:id");
        sqlQueries.put("save", "INSERT INTO clients(name, phone, stylistId) VALUES (:name, :phone, :stylistId);");
        sqlQueries.put("update", "UPDATE clients SET name = :name, phone = :phone, stylistId = :stylistId WHERE id = :id");

        super.typeOfPerson = Client.class;
    }

    public Client(String name) {
        this(name, "000-000-0000", 0);
    }

    public int getStylistId() {
        return super.personId;
    }

    public void assignToStylist(int stylistId) {
        super.personId = stylistId;
    }
}
