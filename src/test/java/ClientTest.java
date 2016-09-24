import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client newclient = new Client("sarah",34, "cropped");
    assertEquals(true, newclient instanceof Client);
  }
  @Test
  public void client_returnsName_True() {
    Client client = new Client("bobby", 42, "crew");
    assertEquals("bobby", client.getName());
  }

  @Test
  public void client_returnsAge_true() {
    Client client = new Client("bobby", 42,"crew");
    assertEquals(42, client.getAge());
  }

  @Test
  public void client_returnsHaircut_true() {
    Client client = new Client("bobby", 67, "crew");
    assertEquals("crew", client.getHaircut());
  }
  @Test
  public void equals_returnsTrueIfAttributesAreTheSame_true() {
    Client myClient = new Client("bobby", 56, "crew");
    Client myClientTwo = new Client("bobby", 56, "crew");
    assertTrue(myClient.equals(myClientTwo));
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client myClient = new Client("bobby", 56, "crew");
    Client myClientTwo = new Client("bobby", 56, "crew");
    myClient.save();
    myClientTwo.save();
    assertTrue(Client.all().get(0).equals(myClient));
    assertTrue(Client.all().get(1).equals(myClientTwo));
  }

  @Test
  public void save_savesClientTo_true() {
    Client myClient = new Client("bobby", 56, "crew");
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToClient_true() {
    Client myClient = new Client("bobby", 56, "crew");
    myClient.save();
    assertTrue(myClient.getId() > 0);
  }
  @Test
  public void find_returnsClientWithSameId_true() {
    Client myClient = new Client("bobby", 56, "crew");
    myClient.save();
    Client myClientTwo = new Client("bobby", 56, "crew");
    myClientTwo.save();
    assertEquals(Client.find(myClientTwo.getId()), myClientTwo);
  }
}
