package com.epam.electives.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class I18nUtil {

    @Autowired
    CustomBundleMessageSource customBundleMessageSource;

    public List<String> getKeys(){
        return customBundleMessageSource.getKeys(LocaleContextHolder.getLocale());
    }
}
