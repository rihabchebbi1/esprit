package tn.esprit.gaspillagezero.dto;

import lombok.*;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientDTO {
    private Long ingredientID;
    private Integer supplierID;
    private Integer quantity;
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }



}
