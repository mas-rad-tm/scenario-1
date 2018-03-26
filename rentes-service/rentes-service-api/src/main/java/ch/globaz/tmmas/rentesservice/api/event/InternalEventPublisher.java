package ch.globaz.tmmas.rentesservice.api.event;

import ch.globaz.tmmas.rentesservice.domain.event.DomainEvent;

public interface InternalEventPublisher {
    void publishEvent(DomainEvent event);
}
