package pl.krax;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Coin {
    private final int value; //in grosz
    private int quantity;

    public void reduceQuantity(int amount) {
        quantity -= amount;
    }
}
