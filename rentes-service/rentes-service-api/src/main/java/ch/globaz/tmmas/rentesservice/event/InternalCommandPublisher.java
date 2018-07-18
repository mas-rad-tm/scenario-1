package ch.globaz.tmmas.rentesservice.event;


import ch.globaz.tmmas.rentesservice.command.DomainCommand;

public interface InternalCommandPublisher {

	void publishCommand(DomainCommand command);
}
