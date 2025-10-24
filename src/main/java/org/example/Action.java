package org.example;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private ActionType type;
    private final List<Product> removedProducts;

    public Action(ActionType type) {
        this.type = type;
        this.removedProducts = new ArrayList<>();
    }

    public ActionType getType() {
        return type;
    }

    public List<Product> getRemovedProducts() {
        return removedProducts;
    }

    public void addProduct(Product product) {
        removedProducts.add(product);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: ").append(type).append("\n");
        sb.append("Products removed: ").append(removedProducts.size()).append("\n");
        return sb.toString();
    }
}