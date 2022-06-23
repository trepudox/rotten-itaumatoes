package com.trepudox.rottenitaumatoes.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class DateAndTimeConfig {

    @PostConstruct
    public void setZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));
    }

}
