package io.bootify.proyecto_no_s_q_l_concurrente.repos;

import io.bootify.proyecto_no_s_q_l_concurrente.domain.Libro;
import io.bootify.proyecto_no_s_q_l_concurrente.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class LibroListener extends AbstractMongoEventListener<Libro> {

    private final PrimarySequenceService primarySequenceService;

    public LibroListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Libro> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());
        }
    }

}
