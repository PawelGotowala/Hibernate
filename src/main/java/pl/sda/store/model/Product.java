package pl.sda.store.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Product implements IBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Inventory> inventory = new ArrayList<>();

    public Product(String name) {
        this.name = name;
    }
}
