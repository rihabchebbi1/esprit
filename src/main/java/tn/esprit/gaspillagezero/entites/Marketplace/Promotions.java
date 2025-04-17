package tn.esprit.gaspillagezero.entites.Marketplace;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Promotions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long idPromotion;
    String code;
    @Enumerated(EnumType.STRING)
    DiscountType discountType;
    Date startDate;
    Date endDate;
    @OneToMany
    private List<Dish> dishes;

    public long getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(long idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
