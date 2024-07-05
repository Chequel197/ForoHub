package com.foroHub.main.modelo;


import com.foroHub.main.records.DatosActualizarPost;
import com.foroHub.main.records.DatosNuevoPost;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "Posts")
@Entity(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Parametro necesario")
    private String titulo;

    @NotBlank(message = "Parametro necesario")
    private String mensaje;

    @NotBlank(message = "Parametro necesario")
    private String autor;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    public Post(DatosNuevoPost datosNuevoPost) {
        this.titulo = datosNuevoPost.titulo();
        this.autor = datosNuevoPost.autor();
        this.mensaje = datosNuevoPost.mensaje();
    }

    public void actualizarDatos(DatosActualizarPost datosActualizarPost) {

        if ( datosActualizarPost.titulo() != null){
            this.titulo = datosActualizarPost.titulo();
        }
        if ( datosActualizarPost.mensaje() != null){
            this.mensaje = datosActualizarPost.mensaje();
        }
    }
}
