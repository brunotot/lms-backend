package tvz.btot.zavrsni.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ContextWrapper {
    private static ApplicationContext context;

    @Autowired
    public ContextWrapper(final ApplicationContext ac) {
        context = ac;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}