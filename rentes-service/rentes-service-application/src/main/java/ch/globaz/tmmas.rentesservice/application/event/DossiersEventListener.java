package ch.globaz.tmmas.rentesservice.application.event;



import ch.globaz.tmmas.rentesservice.domaine.event.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class DossiersEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossiersEventListener.class);

    /**
    @Autowired
    NotificationService notificationService;

    @Autowired
    ObjectMapper mapper;

*/

    @EventListener
    void onDomainEvent(DomainEvent event) throws JsonProcessingException {

        LOGGER.info("onDomainEvent {}",event);



    }

}
