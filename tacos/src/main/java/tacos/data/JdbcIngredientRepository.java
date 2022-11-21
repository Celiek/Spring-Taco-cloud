package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository
        implements IngredientRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    //srawdzenie czy działa thymeleaf
    @RequestMapping(method = RequestMethod.GET)
    ModelAndView index(){
        ModelAndView mav = new ModelAndView("design");
        mav.addObject("version" , "0.0.1");
        return mav;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject("select id , name , type from Ingredient where id=?" ,
                this::mapRowToIngredient , id);
    }

    private Ingredient mapRowToIngredient(ResultSet rs , int rowNum) throws SQLException{
        return new Ingredient(
            rs.getString("id"),
            rs.getString("name"),
            Ingredient.Type.valueOf(rs.getString("type"))
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into Ingredient (id , name ,  type) values ( ? ,  ? , ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
