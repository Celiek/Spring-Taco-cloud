package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args);
  }

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.save(new Ingredient("FLTO", "pszenna", Type.WRAP));
        repo.save(new Ingredient("COTO", "kukurydziana", Type.WRAP));
        repo.save(new Ingredient("GRBF", "mielona wołowina", Type.PROTEIN));
        repo.save(new Ingredient("CARN", "kawałki mięsa", Type.PROTEIN));
        repo.save(new Ingredient("TMTO", "pomidory pokrojone w kostkę", Type.VEGGIES));
        repo.save(new Ingredient("LETC", "sałata", Type.VEGGIES));
        repo.save(new Ingredient("CHED", "cheddar", Type.CHEESE));
        repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        repo.save(new Ingredient("SLSA", "pikantny sos pomidorowy", Type.SAUCE));
        repo.save(new Ingredient("SRCR", "śmietana", Type.SAUCE));
      }
    };
  }
  
}
