package tn.esprit.gaspillagezero.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientDTO {
    private Long ingredientID;
    private Integer supplierID;
    private Integer quantity;
}
