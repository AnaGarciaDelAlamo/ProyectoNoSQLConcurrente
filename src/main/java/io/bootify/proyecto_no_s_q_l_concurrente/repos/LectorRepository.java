package io.bootify.proyecto_no_s_q_l_concurrente.repos;

import io.bootify.proyecto_no_s_q_l_concurrente.domain.Lector;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LectorRepository extends MongoRepository<Lector, Long> {
}
