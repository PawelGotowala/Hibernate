package pl.sda.store.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@SequenceGenerator(name= "seqSale" , initialValue = 1, allocationSize = 1000)
@Entity
public class ProductSale implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSel")
    private Long id;

    private String quantity;
    private String price;


    public ProductSale(String quantity, String price) {
        this.quantity = quantity;
        this.price = price;
    }
}
