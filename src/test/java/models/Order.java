package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private Integer petId;
    private Integer quantity;
    private Timestamp shipDate;
    private String status;
    private Boolean complete;
}
