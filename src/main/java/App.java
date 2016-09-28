
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.io.Console;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    Console console = System.console();

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/add-stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name= request.queryParams("name");
      int age=Integer.parseInt(request.queryParams("age"));
      String specialty= request.queryParams("specialty");
      int experience=Integer.parseInt(request.queryParams("experience"));
      Stylist newStylist = new Stylist(name, age, specialty, experience);
      newStylist.save();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/stylist-summary.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id/add-client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/add-client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      String name = request.queryParams("name");
      int age = Integer.parseInt(request.queryParams("age"));
      String haircut = request.queryParams("haircut");
      int stylistId =  stylist.getId();
      Client client = new Client(name, age, haircut, stylistId);
      client.save();
      model.put("clients", stylist.getClients());
      model.put("template", "templates/stylist-summary.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylist/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      stylist.delete();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id/client/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("client", client);
      model.put("template", "templates/client-summary.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("client/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      client.delete();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:id/edit", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
     model.put("stylist", stylist);
     model.put("template", "templates/edit-stylist.vtl");
     return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylist/:id/edited", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      int age = Integer.parseInt(request.queryParams("age"));
      String specialty = request.queryParams("specialty");
      int experience = Integer.parseInt(request.queryParams("experience"));
      stylist.update(name, age, specialty, experience);
      model.put("stylist", stylist);
      model.put("template", "templates/stylist-summary.vtl");
      response.redirect("/" );
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
