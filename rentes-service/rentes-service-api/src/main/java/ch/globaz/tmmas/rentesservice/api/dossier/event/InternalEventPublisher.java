package ch.globaz.tmmas.rentesservice.api.dossier.event;

import ch.globaz.tmmas.rentesservice.domain.common.event.DomainEvent;

public interface InternalEventPublisher {
    void publishEvent(DomainEvent event);
}
