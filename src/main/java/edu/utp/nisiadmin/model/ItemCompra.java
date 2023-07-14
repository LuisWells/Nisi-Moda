package edu.utp.nisiadmin.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class ItemCompra {
    @ToString.Include
    @Id
    @Column(nullable = false)
    Long id;
    @ManyToOne
    @JoinColumn
    Producto producto;
    @ToString.Include
    Integer cantidad;
    @ToString.Include
    Double precio; //duplicado, porque el precio puede variar
}
