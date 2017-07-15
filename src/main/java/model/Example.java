package model;

import model.impl.Client;
import model.impl.Stylist;

/**
 * Created by mrchebik on 7/14/17.
 */
public class Example {
    public static void main(String[] args) {
        Person client = new Client("Church");
        client.update();

        Person stylist = new Stylist("Kate");
        stylist.save();
    }
}
