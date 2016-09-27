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

  @Override
  public boolean equals(Object myStylist) {
    if(!(myStylist instanceof Stylist)){
      return false;
      } else {
      Stylist stylist = (Stylist) myStylist;
      return this.getName().equals(stylist.getName())&&
             this.getAge()==(stylist.getAge())&&
             this.getSpecialty().equals(stylist.getSpecialty())&&
             this.getExperience()==(stylist.getExperience())&&
             this.getId() == stylist.getId();

    }
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
  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }
  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistid=:id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Client.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE * FROM stylists WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
