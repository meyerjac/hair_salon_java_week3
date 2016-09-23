import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;
  private int age;
  private String speciality;
  private int experience;
  private List<Client> clients;

  public Stylist(String name, int age, String speciality, int experience) {
    this.name = name;
    this.age = age;
    this.speciality = speciality;
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
  public String getSpeciality() {
    return speciality;
  }
  public int getExperience() {
    return experience;
  }
}
