package com.quaildev.source.allies.parse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ParserPunctuationTest {

    private final Parser parser = new Parser();
    private final String word;

    public ParserPunctuationTest(String word) {
        this.word = word;
    }

    @Parameters
    public static Collection<String> parameters() {
        return asList("word.", "word,", "word;");
    }

    @Test
    public void parserIgnoresPunctuation() throws Exception {
        Map<String, Integer> parseResults = parser.parse(word);

        assertThat(parseResults).containsOnlyKeys("word");
    }
}