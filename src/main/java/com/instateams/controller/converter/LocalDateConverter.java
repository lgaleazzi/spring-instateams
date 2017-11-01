package com.instateams.controller.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Component
public class LocalDateConverter implements Converter<String, LocalDate>
{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String source)
    {
        return LocalDate.parse(source, FORMATTER);
    }

    @Bean
    public ConversionService getConversionService()
    {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new LocalDateConverter());
        bean.setConverters(converters);
        return bean.getObject();
    }
}
