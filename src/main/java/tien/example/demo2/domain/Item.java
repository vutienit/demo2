package tien.example.demo2.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequenceGenerator")
    @SequenceGenerator(name = "itemSequenceGenerator", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    private BigDecimal price;
}
