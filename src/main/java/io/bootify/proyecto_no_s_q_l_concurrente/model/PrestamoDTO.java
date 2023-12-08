package io.bootify.proyecto_no_s_q_l_concurrente.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PrestamoDTO {

    private Long id;

   /* private Long libro;

    private Long lector;

    private Long bibliotecario;*/

    @NotNull
    private LocalDate fechaPrestamo;

    @NotNull
    private LocalDate fechaDevolucion;

}
