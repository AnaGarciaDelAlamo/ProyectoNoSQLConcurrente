import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.LibroRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.service.LibroService;
import io.bootify.proyecto_no_s_q_l_concurrente.util.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @Test
    public void testGetNonExistingLibro() {
        // Configurar el comportamiento simulado del repositorio
        when(libroRepository.findById(1L)).thenReturn(Optional.empty());

        // Asegurarse de que NotFoundException sea lanzada
        assertThrows(NotFoundException.class, () -> libroService.get(1L));

        // Verificar que el método del repositorio fue llamado
        verify(libroRepository, times(1)).findById(1L);
    }


}

