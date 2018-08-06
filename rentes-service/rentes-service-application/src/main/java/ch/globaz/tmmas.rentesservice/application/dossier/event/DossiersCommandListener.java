package ch.globaz.tmmas.rentesservice.application.dossier.event;

import ch.globaz.tmmas.rentesservice.dossier.command.DomainCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DossiersCommandListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossiersCommandListener.class);


    @EventListener
    public void onCommand(DomainCommand command){
        LOGGER.info("onDomainCommand: {}",command);
    }
}