package com.sa.tickestoque.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product implements Serializable {
    @SequenceGenerator(
            name="product_sequence",
            sequenceName = "product_sequence",
            allocationSize =  1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String productName;
    private String productImage;
    private String productDescription;
    private String productCategory;
}
