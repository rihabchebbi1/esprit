package tn.esprit.gaspillagezero.entites.Marketplace;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Locale;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dish {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int dishId;
    String Name;
    @Enumerated(EnumType.STRING)
    KitchenType kitchenType;
    double basePrice;
    String image;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public KitchenType getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(KitchenType kitchenType) {
        this.kitchenType = kitchenType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
