import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client newclient = new Client("sarah",34, "cropped", 1);
    assertEquals(true, newclient instanceof Client);
  }
  @Test
  public void client_returnsName_True() {
    Client client = new Client("bobby", 42, "crew", 1);
    assertEquals("bobby", client.getName());
  }

  @Test
  public void client_returnsAge_true() {
    Client client = new Client("bobby", 42,"crew", 1);
    assertEquals(42, client.getAge());
  }

  @Test
  public void client_returnsHaircut_true() {
    Client client = new Client("bobby", 67, "crew", 1);
    assertEquals("crew", client.getHaircut());
  }
  @Test
  public void client_returnsStylistId_True() {
    Client client = new Client("bobby", 42, "crew", 6);
    assertEquals(6, client.getStylistId());
  }
  @Test
  public void equals_returnsTrueIfAttributesAreTheSame_true() {
    Client myClient = new Client("bobby", 56, "crew", 1);
    Client myClientTwo = new Client("bobby", 56, "crew", 1);
    assertTrue(myClient.equals(myClientTwo));
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client myClient = new Client("bobby", 56, "crew", 1);
    Client myClientTwo = new Client("bobby", 56, "crew", 1);
    myClient.save();
    myClientTwo.save();
    assertTrue(Client.all().get(0).equals(myClient));
    assertTrue(Client.all().get(1).equals(myClientTwo));
  }

  @Test
  public void save_savesClientTo_true() {
    Client myClient = new Client("bobby", 56, "crew", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToClient_true() {
    Client myClient = new Client("bobby", 56, "crew", 1);
    myClient.save();
    assertTrue(myClient.getId() > 0);
  }
  @Test
  public void find_returnsClientWithSameId_true() {
    Client myClient = new Client("bobby", 56, "crew", 1);
    myClient.save();
    Client myClientTwo = new Client("bobby", 56, "crew", 1);
    myClientTwo.save();
    assertEquals(Client.find(myClientTwo.getId()), myClientTwo);
  }
  @Test
  public void delete_deletesTheClient_true() {
  Client testClient = new Client("bobby", 56, "crew", 1);
  testClient.save();
  int id = testClient.getId();
  testClient.delete();
  assertEquals(null, Client.find(id));
}
}
