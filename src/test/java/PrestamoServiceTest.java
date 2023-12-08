import io.bootify.proyecto_no_s_q_l_concurrente.repos.PrestamoRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.service.PrestamoService;
import io.bootify.proyecto_no_s_q_l_concurrente.util.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrestamoServiceTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoService prestamoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNonExistingPrestamo() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> prestamoService.get(1L));
    }

}

