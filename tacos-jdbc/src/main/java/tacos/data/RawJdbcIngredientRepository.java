package tacos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import tacos.Ingredient;

/**
 * Implementacja {@link IngredientRepository} przeznaczona do
 * porównania z {@link JdbcIngredientRepository}, aby pokazać
 * potężne możliwości używania {@link JdbcTemplate}. 
 * @author habuma
 */
public class RawJdbcIngredientRepository implements IngredientRepository {

  private DataSource dataSource;

  public RawJdbcIngredientRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }
  
  @Override
  public Iterable<Ingredient> findAll() {
    List<Ingredient> ingredients = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      connection = dataSource.getConnection();
      statement = connection.prepareStatement(
          "select id, name, type from Ingredient");
      resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Ingredient ingredient = new Ingredient(
            resultSet.getString("id"),
            resultSet.getString("name"),
            Ingredient.Type.valueOf(resultSet.getString("type")));
        ingredients.add(ingredient);
      }      
    } catch (SQLException e) {
      // Co powinno być tutaj zrobione?
    } finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {}
      }
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {}
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {}
      }
    }
    return ingredients;
  }
  
  // tag::rawfindOne[]
  @Override
  public Ingredient findById(String id) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      connection = dataSource.getConnection();
      statement = connection.prepareStatement(
          "select id, name, type from Ingredient");
      statement.setString(1, id);
      resultSet = statement.executeQuery();
      Ingredient ingredient = null;
      if(resultSet.next()) {
        ingredient = new Ingredient(
            resultSet.getString("id"),
            resultSet.getString("name"),
            Ingredient.Type.valueOf(resultSet.getString("type")));
      } 
      return ingredient;
    } catch (SQLException e) {
      // Co powinno być tutaj zrobione?
    } finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {}
      }
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {}
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {}
      }
    }
    return null;
  }
  // end::rawfindOne[]
  
  @Override
  public Ingredient save(Ingredient ingredient) {
    // TODO: Na potrzeby porównania potrzebna jest tylko jedna metoda,
    //       więc nie będę jej jeszcze implementował.
    return null;
  }
  
}
