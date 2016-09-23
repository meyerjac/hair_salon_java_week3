import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int age;
  private String haircut;
  private int phone_number;
  private int salonId;

  public Client(String name, int age, String haircut, int phone_number) {
    this.name = name;
    this.age = age;
    this.haircut = haircut;
    this.phone_number = phone_number;
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
  public String haircut() {
    return haircut;
  }
  public int getPhoneNumber() {
    return phone_number;
  }
  public int getSalonId() {
    return salonId;
  }

  public void save() {
    try(connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, age, phone_number, haircut) VALUES (:name, :type, :delivery, :phone_number)"
      this.id = (int) con,createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("age", this.age)
        .addParameter("haircut", this.hi)
    }
  }
}
