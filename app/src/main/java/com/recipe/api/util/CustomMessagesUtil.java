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

	@Autowired
    private MessageSource messageSource;


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
     * @param args        the args
     * @return message
     */
    public String getMessage(String messageCode, List<Object> args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, args != null ? args.toArray() : null, locale);
    }
}
