package es.urjc.code.daw.library.notification;

import es.urjc.code.daw.library.Features;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.manager.FeatureManager;

@Component
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private FeatureManager featureManager;

    public void notify(String message) {
        if (this.featureManager.isActive(Features.FEATURE_ASYNC_NOTIFICATION)) {
            System.out.println("Async notification enabled");
        }else{
            logger.info(message);
        }
    }
}

