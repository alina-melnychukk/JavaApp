package com.example.InventoryManagement.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "available")
    private boolean available;

    @ManyToOne()
    @JoinColumn(name = "companyId")
    @Schema(hidden = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;

    @ManyToOne()
    @JoinColumn(name = "userId")
    @Schema(hidden = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
