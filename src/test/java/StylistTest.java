import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
      }
    }

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist stylist = new Stylist("sarah", 34, "touch-up work", 4);
    assertEquals(true, stylist instanceof Stylist);
  }

  @Test
  public void stylist_returnsName_true() {
    Stylist stylist = new Stylist("sarah", 34, "touch-up work", 4);
    assertEquals("sarah", stylist.getName());
  }
  @Test
  public void stylist_returnsAge_true() {
    Stylist stylist = new Stylist("Sarah", 34, "touch-up work", 4);
    assertEquals(34, stylist.getAge());
  }
  @Test
  public void stylist_returnsSpecialty_true() {
    Stylist stylist = new Stylist("Sarah", 34, "touch-up work", 4);
    assertEquals("touch-up work", stylist.getSpeciality());
  }
  @Test
  public void stylist_returnsExperience_true() {
    Stylist stylist = new Stylist("Sarah", 34, "touch-up work", 4);
    assertEquals(4, stylist.getExperience());
  }
}
