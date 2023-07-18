package edu.utp.nisiadmin.model;

import edu.utp.nisiadmin.enums.EstadoProducto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Producto {
    @ToString.Include
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Long id;

    @ToString.Include
    @Column(nullable = false)
    private String nombre;

    @ToString.Include
    @Column(nullable = false)
    private Integer cantidad;

    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoProducto estado;

    @Column(columnDefinition = "text")
    private String fotoUrl;

    private String descripcion;
    private String categoria;

    @ToString.Include
    private Double precio;

    private Long ventas;
    private Long visualizaciones;
    private Long vecesEnCarrito;

    /*
        Entrenas con estado, precio, ventas, visualizaciones, vecesEnCarrito, Categoría, nombre.
        Request: (precio, categoría)->([precio1,precio2])
        Para que esto funciones: cada vez que se crea un nuevo producto, se manda un request a Flask para que reentrene a la NN.
     */

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;
}
