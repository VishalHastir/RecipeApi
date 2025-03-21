package com.recipe.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

/**
 * The type Custom messages util.
 *
 * @author Vishal
 */
@Component
public class CustomMessagesUtil {

    private MessageSource messageSource;

    public CustomMessagesUtil (MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    /**
     * Gets message.
     *
     * @param messageCode the message code
     * @return message
     */
    public String getMessage(String messageCode) {
        return getMessage(messageCode, null);
    }

    /**
     * Gets message.
     *
     * @param messageCode the message code
     * @param objects        the objects
     * @return message
     */
    public String getMessage(String messageCode, List<Object> objects) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, objects != null ? objects.toArray() : null, locale);
    }
}
