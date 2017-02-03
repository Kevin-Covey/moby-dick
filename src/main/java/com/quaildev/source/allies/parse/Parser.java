package com.quaildev.source.allies.parse;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class Parser {

    public static final String PUNCTUATION_REGEX = "(\\.|,|;)";

    public Map<String, Integer> parse(String s) {
        Map<String, Integer> results = new HashMap<>();

        Arrays.stream(s.replaceAll(PUNCTUATION_REGEX, "").toLowerCase().split(" ")).forEach(token -> {
            results.compute(token, (key, count) -> count == null ? 1 : count + 1);
        });

        return results;
    }

}
