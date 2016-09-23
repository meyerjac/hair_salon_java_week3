import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;
  private int age;
  private String specialty;
  private int experience;
  private List<Client> clients;

  public Stylist(String name, int age, String specialty, int experience) {
    this.name = name;
    this.age = age;
    this.specialty = specialty;
    this.experience = experience;
  }

  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }
  public String getSpecialty() {
    return specialty;
  }
  public int getExperience() {
    return experience;
  }






  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, age, specialty, experience) VALUES (:name, :age, :specialty, :experience)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("age", this.age)
        .addParameter("specialty", this.specialty)
        .addParameter("experience", this.experience)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }



}
