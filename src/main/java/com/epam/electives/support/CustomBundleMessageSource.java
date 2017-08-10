package com.epam.electives.support;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


public class CustomBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public List<String> getKeys(Locale locale){ // locale passed for variant with different keys in different locales
        List<String> keys = new ArrayList<>();
        PropertiesHolder propertiesHolder = getMergedProperties(locale);
        Properties properties = propertiesHolder.getProperties();
        for(Object key : properties.keySet()){
            keys.add(key.toString());
        }
        return keys;
    }
}
