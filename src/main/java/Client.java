import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int age;
  private String haircut;
  private int salonId;

  public Client(String name, int age, String haircut) {
    this.name = name;
    this.age = age;
    this.haircut = haircut;
    this.salonId = salonId;
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
  public String getHaircut() {
    return haircut;
  }
  public int getSalonId() {
    return salonId;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
      this.getAge()==(newClient.getAge()) &&
      this.getHaircut().equals(newClient.getHaircut()) &&
      this.getId() == newClient.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, age, haircut) VALUES (:name, :age, :haircut)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("age", this.age)
      .addParameter("haircut", this.haircut)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    String sql = "SELECT * FROM Clients where id=:id";
    try(Connection con = DB.sql2o.open()) {
      Client newClient = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
      return newClient;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE StylistId = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
