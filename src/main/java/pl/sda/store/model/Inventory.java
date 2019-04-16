package pl.sda.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@SequenceGenerator(name= "seq" , initialValue = 1, allocationSize = 1000)
public class Inventory implements IBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    private String quantity;
    private String value;
    private LocalDate dateArrived;
    @ManyToOne
    private Product product;

    public Inventory(String quantity, String value, LocalDate dateArrived) {
        this.quantity = quantity;
        this.value = value;
        this.dateArrived = dateArrived;
    }
}
