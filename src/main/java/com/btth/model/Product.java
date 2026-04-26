package com.btth.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @Column(length = 2000)
    private String description;

    private Double price;

    private Integer stockQuantity;

    private String category;

    private Boolean status;

}
