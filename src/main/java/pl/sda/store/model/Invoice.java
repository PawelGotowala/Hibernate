package pl.sda.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.sda.store.logic.EntityDao;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@SequenceGenerator(name= "seqInv" , initialValue = 1, allocationSize = 1000)
@Entity

public class Invoice implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqInv")
    private Long id;
// to jest do sprawdzenia czy tyle wystaczy!
    @CreationTimestamp
    private LocalDate saleDate;
    @UpdateTimestamp
    private LocalDate updateStamp;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER)
    private List<ProductSale> productSalesList = new ArrayList<>();


}


