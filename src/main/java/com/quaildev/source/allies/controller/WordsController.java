package com.quaildev.source.allies.controller;

import com.quaildev.source.allies.parse.Parser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/words")
public class WordsController {

    static final String MOBY_DICK_TEXT = "/moby-dick.txt";

    @Autowired
    private Parser parser;

    @Autowired
    private MobyDickClasspathResourceProvider resourceProvider;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Map<String, Integer> get() {
        return parser.parse(retrieveText());
    }

    private String retrieveText() {
        try {
            return IOUtils.toString(resourceProvider.get().getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
