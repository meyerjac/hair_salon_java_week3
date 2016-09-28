import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int age;
  private String haircut;
  private int stylistId;

  public Client(String name, int age, String haircut, int stylistId) {
    this.name = name;
    this.age = age;
    this.haircut = haircut;
    this.stylistId = stylistId;
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
  public int getStylistId() {
    return stylistId;
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
      this.getStylistId() == (newClient.getStylistId()) &&
      this.getId() == (newClient.getId());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, age, haircut, stylistId) VALUES (:name, :age, :haircut, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("age", this.age)
      .addParameter("haircut", this.haircut)
      .addParameter("stylistId", this.stylistId)
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
    String sql = "SELECT * FROM clients where id=:id";
    try(Connection con = DB.sql2o.open()) {
      Client newClient = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
      return newClient;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
  public void update(String name, int age, String haircut, int stylistId) {
    try (Connection con = DB.sql2o.open()) {
      this.name = name;
      this.age = age;
      this.haircut = haircut;
      this.stylistId = stylistId;
      String sql = "UPDATE clients SET name = :name, age = :age, haircut = :haircut, stylistId = :stylistId WHERE stylistId = :id";
      con.createQuery(sql)
      .addParameter("age", this.age)
      .addParameter("name", this.name)
      .addParameter("haircut", this.haircut)
      .addParameter("stylistId", this.stylistId)
      .addParameter("id", stylistId)
      .executeUpdate();
    }
  }
}
