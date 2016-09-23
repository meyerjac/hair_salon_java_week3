import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ClientTest {

  @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void client_instantiatesCorrectly_true() {
    Client newclient = new Client("sarah",34, "cropped", 987-666-5454);
      assertEquals(true, newclient instanceof Client);
  }
}
