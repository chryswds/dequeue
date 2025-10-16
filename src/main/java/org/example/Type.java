package org.example;

public enum Type {
    BURGER("1. 🍔 Burger  "),
    PIZZA("2. 🍕 Pizza   "),
    FRIES("3. 🍟 Fries   "),
    SANDWICH("4. 🥖 Sandwich"),
    HOTDOG("5. 🌭 HotDog  ");

    private final String displayName;

    Type(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
