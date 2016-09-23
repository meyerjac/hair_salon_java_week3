import org.junit.rules.ExternalResources;
import org.sql2o.*;

public class DatabaseRule extends
ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new
    Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String delete__________Query = "DELETE FROM __________*;";
      String delete________Query = "DELETE FROM __________ *;";
      con.createQuery(_________).executeUpdate();
      con.createQuery(_________).executeUpdate();
    }
  }




}
