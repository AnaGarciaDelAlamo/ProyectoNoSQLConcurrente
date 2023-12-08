package io.bootify.proyecto_no_s_q_l_concurrente.controller;

import com.mongodb.MongoException;
import io.bootify.proyecto_no_s_q_l_concurrente.domain.Bibliotecario;
import io.bootify.proyecto_no_s_q_l_concurrente.domain.Lector;
import io.bootify.proyecto_no_s_q_l_concurrente.domain.Libro;
import io.bootify.proyecto_no_s_q_l_concurrente.domain.Prestamo;
import io.bootify.proyecto_no_s_q_l_concurrente.model.PrestamoDTO;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.BibliotecarioRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.LectorRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.repos.LibroRepository;
import io.bootify.proyecto_no_s_q_l_concurrente.service.PrestamoService;
import io.bootify.proyecto_no_s_q_l_concurrente.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;
    //private final LibroRepository libroRepository;
   //private final LectorRepository lectorRepository;
    //private final BibliotecarioRepository bibliotecarioRepository;
    //private final MongoTemplate mongoTemplate;

    @Autowired
    public PrestamoController(final PrestamoService prestamoService
                              /*final LibroRepository libroRepository,
                              final LectorRepository lectorRepository,
                              final BibliotecarioRepository bibliotecarioRepository
                              /*final MongoTemplate mongoTemplate*/) {
        this.prestamoService = prestamoService;
        //this.libroRepository = libroRepository;
        //this.lectorRepository = lectorRepository;
        //this.bibliotecarioRepository = bibliotecarioRepository;
        //this.mongoTemplate = mongoTemplate;
    }

   /* @ModelAttribute
    public void prepareContext(final Model model) {
        List<Libro> libros = libroRepository.findAll();
        List<Lector> lectores = lectorRepository.findAll();
        List<Bibliotecario> bibliotecarios = bibliotecarioRepository.findAll();

        model.addAttribute("libroValues", libros.stream()
                .collect(Collectors.toMap(Libro::getId, Libro::getTitulo, (existing, replacement) -> existing, () -> new TreeMap<>(Comparator.comparingLong(Long::valueOf)))));

        model.addAttribute("lectorValues", lectores.stream()
                .collect(Collectors.toMap(Lector::getId, Lector::getNombre, (existing, replacement) -> existing, () -> new TreeMap<>(Comparator.comparingLong(Long::valueOf)))));

        model.addAttribute("bibliotecarioValues", bibliotecarios.stream()
                .collect(Collectors.toMap(Bibliotecario::getId, Bibliotecario::getNombre, (existing, replacement) -> existing, () -> new TreeMap<>(Comparator.comparingLong(Long::valueOf)))));
    }*/

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("prestamoes", prestamoService.findAll());
        return "prestamo/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("prestamo") final PrestamoDTO prestamoDTO) {
        return "prestamo/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prestamo/add";
        }
        prestamoService.create(prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.create.success"));
        return "redirect:/prestamos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("prestamo", prestamoService.get(id));
        return "prestamo/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prestamo/edit";
        }
        prestamoService.update(id, prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.update.success"));
        return "redirect:/prestamos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        prestamoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("prestamo.delete.success"));
        return "redirect:/prestamos";
    }

    /*@PostMapping("/prestamos")
    public String processPrestamoForm(@ModelAttribute Prestamo prestamo, Model model) {
        // Obtener instancias de Lector, Bibliotecario y Libro
        Lector lector = lectorRepository.findById(prestamo.getLector().getId()).orElse(null);
        Bibliotecario bibliotecario = bibliotecarioRepository.findById(prestamo.getBibliotecario().getId()).orElse(null);
        Libro libro = libroRepository.findById(prestamo.getLibro().getId()).orElse(null);

        // Validar que las instancias no sean nulas
        if (lector == null || bibliotecario == null || libro == null) {
            // Manejar el caso donde algún objeto es nulo
            // Puedes lanzar una excepción, redirigir a una página de error, etc.
            // Por ejemplo, podrías redirigir a la página de préstamos con un mensaje de error
            model.addAttribute("error", "Error al procesar el préstamo. Por favor, verifica los datos.");
            return "redirect:/prestamos";
        }


        // Asignar los objetos al préstamo
        prestamo.setLector(lector);
        prestamo.setBibliotecario(bibliotecario);
        prestamo.setLibro(libro);

        // Guardar el préstamo usando MongoTemplate
        try {
            prestamoService.create(prestamo);
        } catch (MongoException e) {
            // Manejar la excepción de MongoDB, si es necesario
            model.addAttribute("error", "Error al procesar el préstamo. Por favor, verifica los datos.");
            return "redirect:/prestamos";
        }

        // Redirigir a la página de préstamos
        return "redirect:/prestamos";
    }*/




}
