package main.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    private Document page;

    Parser() throws IOException {
        page = Jsoup.connect("https://www.success.com/17-motivational-quotes-to-inspire-you-to-be-successful").get();
    }

    public ArrayList<String> getQuotes() {
        ArrayList<String> quotes = new ArrayList<>();
        Elements elems = page.select("h3");
        for (Element elem: elems) {
            if (elem.hasText()) {
                quotes.add(elem.text());
            }
        }
        for (int i = 0; i < 3; ++i) {
            quotes.remove(quotes.size() - 1);
        }
        return quotes;
    }

}