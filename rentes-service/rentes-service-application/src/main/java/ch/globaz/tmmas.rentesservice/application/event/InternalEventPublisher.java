package ch.globaz.tmmas.rentesservice.application.event;

import ch.globaz.tmmas.rentesservice.domaine.event.DomainEvent;

public interface InternalEventPublisher {
    void publishEvent(DomainEvent event);
}
