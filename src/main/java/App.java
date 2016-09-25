
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
      model.put("stylist", Stylist.all());
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
      model.put("template", "templates/index.vtl");
      model.put("stylists", Stylist.all());
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

  }
}



//     get("/stylist/:id/add-review", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
//       model.put("restaurant", restaurant);
//       model.put("template", "templates/add-review.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     post("restaurant/:id/delete", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       Stylist restaurant = Stylist.find(Integer.parseInt(request.params(":id")));
//       restaurant.delete();
//       model.put("template", "templates/index.vtl");
//       model.put("restaurants", Stylist.all());
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     post("restaurant/:id", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       Stylist restaurant = Stylist.find(Integer.parseInt(request.params(":id")));
//       model.put("restaurant", restaurant);
//       String paragraph = request.queryParams("paragraph");
//       String pictureUrl = request.queryParams("pictureUrl");
//       String rating = request.queryParams("rating");
//       int restaurantId =  restaurant.getId();
//       Review review = new Review(paragraph, pictureUrl, rating, restaurantId);
//       review.save();
//       model.put("template", "templates/restaurant-reviews.vtl");
//       model.put("reviews", restaurant.getReviews());
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//   }
// }
