import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class StylistTest {

  @Rule
    public DatabaseRule database = new DatabaseRule();

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
    assertEquals("touch-up work", stylist.getSpecialty());
  }
  @Test
  public void stylist_returnsExperience_true() {
    Stylist stylist = new Stylist("Sarah", 34, "touch-up work", 4);
    assertEquals(4, stylist.getExperience());
  }
  @Test
  public void equals_returnsTrueIfAttributesAreTheSame_true() {
    Stylist stylist = new Stylist("Sarah", 34, "touch-up work", 4);
    Stylist stylist2 = new Stylist("Sarah", 34, "touch-up work", 4);
    assertTrue(stylist.equals(stylist2));
   }
  @Test
  public void equals_FalseIfAttributesAreDifferent_false() {
   Stylist stylist = new Stylist("Sarah", 34, "touch-up work", 4);
   Stylist stylist2 = new Stylist("Sophie", 34, "touch-up work", 4);
   assertEquals(false, stylist.equals(stylist2));
 }
  @Test
  public void all_returnsAllInstancesOfStylists_true() {
     Stylist stylist1 = new Stylist("Sarah", 34, "touch-up work", 4);
     Stylist stylist2 = new Stylist("Sarah", 34, "makeup", 4);
     stylist1.save();
     stylist2.save();
     assertEquals(true, Stylist.all().get(0).equals(stylist1));
     assertEquals(true, Stylist.all().get(1).equals(stylist2));
 }
  @Test
  public void save_savesStylistToDataBase_true() {
    Stylist newStylist = new Stylist("Sarah", 34, "touch-up work", 4);
    newStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(newStylist));
  }
  @Test
  public void save_assignsIdToStylist_true() {
    Stylist newStylist = new Stylist("Sarah", 34, "touch-up work", 4);
    newStylist.save();
    assertEquals(true, newStylist.getId() > 0);
  }


}
