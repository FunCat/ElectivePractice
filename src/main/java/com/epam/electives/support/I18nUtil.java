package com.epam.electives.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class I18nUtil {

    @Autowired
    ApplicationContext applicationContext; //TODO: make Autowired CustomBundleMessageSource instead taking it from appCtx
    //    CustomBundleMessageSource customBundleMessageSource;

    public List<String> getKeys(){
        CustomBundleMessageSource customBundleMessageSource = (CustomBundleMessageSource) applicationContext.getBean("messageSource");
        return customBundleMessageSource.getKeys(LocaleContextHolder.getLocale());
    }
}
