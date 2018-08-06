package ch.globaz.tmmas.rentesservice.application.dossier.event.impl;

import ch.globaz.tmmas.rentesservice.api.dossier.event.InternalEventPublisher;
import ch.globaz.tmmas.rentesservice.domain.common.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher implements InternalEventPublisher {

	@Autowired
	ApplicationEventPublisher commandPublisher;

	@Override
	public void publishEvent(DomainEvent event) {
		commandPublisher.publishEvent(event);
	}
}
