package tacos;
import java.util.Date;
import java.util.List;
// end::allButValidation[]
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;


  // end::allButValidation[]
  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt;
  @ManyToMany(targetEntity = Ingredient.class)
  @Size(min=5 , message = "Musisz dodac przynajmniej jeden składnik")
  private List<Ingredient> ingredients;

  @PrePersist
  void createdAt(){
    this.createdAt = new Date();
  }

}