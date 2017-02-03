package com.quaildev.source.allies.parse;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void parseIdentifiesWordsInText() throws Exception {
        Map<String, Integer> parseResults =
                parser.parse("the quick brown fox jumped over the lazy dog");

        assertThat(parseResults).containsOnlyKeys("the", "quick", "brown", "fox", "jumped", "over", "lazy", "dog");
    }

    @Test
    public void parseIsCaseInsensive() throws Exception {
        Map<String, Integer> parseResults = parser.parse("the The THE tHE");

        assertThat(parseResults).containsOnlyKeys("the");
    }

    @Test
    public void parseIgnoresPunctuation() throws Exception {
        Map<String, Integer> parseResults = parser.parse("a short sentence.");

        assertThat(parseResults).containsOnlyKeys("a", "short", "sentence");
    }

    @Test
    public void parserCountsWords() throws Exception {
        Map<String, Integer> parseResults = parser.parse("the the blah is is is");

        assertThat(parseResults.get("the")).isEqualTo(2);
        assertThat(parseResults.get("blah")).isEqualTo(1);
        assertThat(parseResults.get("is")).isEqualTo(3);
    }

}