package parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static parser.JSONHashtag.getCountryFromFile;

public class JSONHashtagTest {

    @Test
    public void getCountryFromFileTest() {
        String filename = "tiktok_colombia.json";
        assertEquals(getCountryFromFile(filename), "colombia");
    }
}