import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // Client Routes /////////////////////////////////////////////

    get("/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/client-new.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      int stylistId = Integer.parseInt(request.queryParams("stylist"));
      Client newClient = new Client(name, phone, stylistId);
      newClient.assignToShop(Stylist.find(stylistId).getShopId()); //assigns them to a shop depending on stylist
      newClient.save(); // Saves to the client database table
      response.redirect("/clients");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client.delete(Integer.parseInt(request.params(":id"))); //the the client
      response.redirect("/clients");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("client", client);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/client-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client updatedClient = Client.find(Integer.parseInt(request.params(":id")));
      int stylistId = Integer.parseInt(request.queryParams("stylist"));
      updatedClient.setName(request.queryParams("name"));
      updatedClient.setPhone(request.queryParams("phone"));
      updatedClient.assignToStylist(stylistId);
      updatedClient.assignToShop(Stylist.find(stylistId).getShopId()); //assigns them to a shop depending on stylist
      updatedClient.update(); // updates the client database entry
      response.redirect("/clients");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("client", client);
      model.put("template", "templates/client-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // Stylist Routes /////////////////////////////////////////////

    get("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-new.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      int shopId = Integer.parseInt(request.queryParams("shopId"));
      Stylist newStylist = new Stylist(name);
      newStylist.setPhone(phone);
      newStylist.setShopId(shopId);
      newStylist.save(); // Saves to the category database table
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist.delete(Integer.parseInt(request.params(":id"))); //the the client
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist updatedStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      updatedStylist.setName(request.queryParams("name"));
      updatedStylist.setPhone(request.queryParams("phone"));
      updatedStylist.setShopId(Integer.parseInt(request.queryParams("shopId"))); //assigns them to a shop depending on stylist
      updatedStylist.update(); // updates the stylists database entry
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist-details.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  } //end of main
} //end of class
