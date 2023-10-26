package ru.naumen.collection.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс для добавление доп услуг к билету
 */
public class AdditionalServices {
    private final long idTicket;
    private String foodAndDrinks;
    private String drinks;

    public AdditionalServices(long idTicket) {
        this.idTicket = idTicket;
    }

    public List<String> getAllAdditionalServices() {
        var array = new ArrayList<String>();
        if (foodAndDrinks != null)
            array.add(foodAndDrinks);
        if (drinks != null)
            array.add(drinks);
        if (array.isEmpty()) array.add("нет товаров");
        return array;
    }

    public void buyDrinks(String drinks) {
        this.drinks = drinks;
    }

    public void buyFoodAndDrinks(String foodAndDrinks) {
        this.foodAndDrinks = foodAndDrinks;
    }

    public long getIdTicket() {
        return idTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalServices that = (AdditionalServices) o;
        return idTicket == that.idTicket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket);
    }
}
