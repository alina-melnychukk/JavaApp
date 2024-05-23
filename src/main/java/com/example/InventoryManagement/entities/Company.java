package com.example.InventoryManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Company")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "country", nullable = false, length = 40)
    private String country;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;
}
