package io.bootify.proyecto_no_s_q_l_concurrente.service;

import io.bootify.proyecto_no_s_q_l_concurrente.domain.Bibliotecario;
import io.bootify.proyecto_no_s_q_l_concurrente.domain.Lector;
import io.bootify.proyecto_no_s_q_l_concurrente.domain.Libro;
import io.bootify.proyecto_no_s_q_l_concurrente.domain.Prestamo;
import io.bootify.proyecto_no_s_q_l_concurrente.model.PrestamoDTO;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.BibliotecarioRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.LectorRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.LibroRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.PrestamoRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.util.NotFoundException;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    /*private final LibroRepository libroRepository;

    private final LectorRepository lectorRepository;

    private final BibliotecarioRepository bibliotecarioRepository;*/

    public PrestamoService(final PrestamoRepository prestamoRepository
                           /*final LibroRepository libroRepository, final LectorRepository lectorRepository,
                           final BibliotecarioRepository bibliotecarioRepository*/){
        this.prestamoRepository = prestamoRepository;
        //this.libroRepository = libroRepository;
        //this.lectorRepository = lectorRepository;
        //this.bibliotecarioRepository = bibliotecarioRepository;
    }

    public List<PrestamoDTO> findAll() {
        final List<Prestamo> prestamoes = prestamoRepository.findAll(Sort.by("id"));
        return prestamoes.stream()
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .toList();
    }

    public PrestamoDTO get(final Long id) {
        return prestamoRepository.findById(id)
                .map(prestamo -> mapToDTO(prestamo, new PrestamoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = new Prestamo();
        mapToEntity(prestamoDTO, prestamo);
        return prestamoRepository.save(prestamo).getId();
    }

    public void update(final Long id, final PrestamoDTO prestamoDTO) {
        final Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(prestamoDTO, prestamo);
        prestamoRepository.save(prestamo);
    }

    public void delete(final Long id) {
        prestamoRepository.deleteById(id);
    }

    private PrestamoDTO mapToDTO(final Prestamo prestamo, final PrestamoDTO prestamoDTO) {
        prestamoDTO.setId(prestamo.getId());
        prestamoDTO.setFechaPrestamo(prestamo.getFechaPrestamo());
        prestamoDTO.setFechaDevolucion(prestamo.getFechaDevolucion());
        //prestamoDTO.setLibro(prestamo.getLibro() == null ? null : prestamo.getLibro().getId());
       // prestamoDTO.setLector(prestamo.getLector() == null ? null : prestamo.getLector().getId());
      //  prestamoDTO.setBibliotecario(prestamo.getBibliotecario() == null ? null : prestamo.getBibliotecario().getId());
        return prestamoDTO;
    }

    private Prestamo mapToEntity(final PrestamoDTO prestamoDTO, final Prestamo prestamo) {
        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        /*final Libro libro = prestamoDTO.getLibro() == null ? null : libroRepository.findById(prestamoDTO.getLibro())
                .orElseThrow(() -> new NotFoundException("Libro no encontrado"));
        final Lector lector = prestamoDTO.getLector() == null ? null : lectorRepository.findById(prestamoDTO.getLector())
                .orElseThrow(() -> new NotFoundException("Lector no encontrado"));
        final Bibliotecario bibliotecario = prestamoDTO.getBibliotecario() == null ? null : bibliotecarioRepository.findById(prestamoDTO.getBibliotecario())
                .orElseThrow(() -> new NotFoundException("Bibliotecario no encontrado"));*/
        return prestamo;
    }

    /*public void create(Prestamo prestamo) {
        if (prestamo.getBibliotecario() == null || prestamo.getLector() == null || prestamo.getLibro() == null) {
            throw new IllegalArgumentException("Los campos bibliotecario, lector y libro no deben ser nulos.");
        }

        // Puedes realizar alguna lógica de validación o ajuste aquí si es necesario antes de guardar
        // ...

        // Guardar el préstamo en la base de datos
        prestamoRepository.save(prestamo);
    }*/

}
