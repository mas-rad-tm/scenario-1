package ch.globaz.tmmas.rentesservice.dossier.event;


import ch.globaz.tmmas.rentesservice.dossier.command.DomainCommand;

public interface InternalCommandPublisher {

	void publishCommand(DomainCommand command);
}
