package tacos.data;

import tacos.Order;
import tacos.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
