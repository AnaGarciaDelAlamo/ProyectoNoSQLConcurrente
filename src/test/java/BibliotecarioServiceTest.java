import io.bootify.proyecto_no_s_q_l_concurrente.repos.BibliotecarioRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.service.BibliotecarioService;
import io.bootify.proyecto_no_s_q_l_concurrente.util.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BibliotecarioServiceTest {

    @Mock
    private BibliotecarioRepository bibliotecarioRepository;

    @InjectMocks
    private BibliotecarioService bibliotecarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNonExistingBibliotecario() {
        when(bibliotecarioRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> bibliotecarioService.get(1L));
    }
}

