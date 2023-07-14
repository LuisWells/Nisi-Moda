package edu.utp.nisiadmin.model;

import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.enums.Rol;
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
public class Empleado {
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
    private String apellido;

    @ToString.Include
    @Column(nullable = false, unique = true)
    private String dni;

    private String direccion;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefono;

    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ToString.Include
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ToString.Include
    private String fotoUrl;

    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
