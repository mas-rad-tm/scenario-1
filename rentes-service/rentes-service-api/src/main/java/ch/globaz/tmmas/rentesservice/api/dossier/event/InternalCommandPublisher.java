package ch.globaz.tmmas.rentesservice.api.dossier.event;


import ch.globaz.tmmas.rentesservice.api.dossier.command.DomainCommand;

public interface InternalCommandPublisher {

	void publishCommand(DomainCommand command);
}
