package io.bootify.proyecto_no_s_q_l_concurrente.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BibliotecarioDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombre;

    @NotNull
    @Size(max = 255)
    private String apellido;

    @NotNull
    @Size(max = 20)
    private String dni;

    @NotNull
    @Size(max = 255)
    private String zona;

}