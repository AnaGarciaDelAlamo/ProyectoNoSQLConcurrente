package io.bootify.proyecto_no_s_q_l_concurrente.repos;

import io.bootify.proyecto_no_s_q_l_concurrente.domain.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LibroRepository extends MongoRepository<Libro, Long> {
}
