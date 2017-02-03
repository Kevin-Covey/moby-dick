package com.quaildev.source.allies.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.inject.Provider;

import static com.quaildev.source.allies.controller.WordsController.MOBY_DICK_TEXT;

@Component
public class MobyDickClasspathResourceProvider implements Provider<ClassPathResource> {

    @Override
    public ClassPathResource get() {
        return new ClassPathResource(MOBY_DICK_TEXT);
    }
}
