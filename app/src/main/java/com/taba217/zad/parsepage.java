package com.taba217.zad;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Example program to list links from a URL.
 */
public class parsepage {

    public static void main(String[] args) throws IOException {
//        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = //"https://archive.org/details/sera-mohmed-said";
                "https://archive.org/details/khodwat_mohamed_said_haj";
        print("Fetching %s ...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("title");

        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.tagName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                        trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            if(!link.text().contains("icon")&&!link.text().contains("logo"))
            print(" * %s <%s> ", link.tagName(),link.text());
        }

        for (Element link : links) {
            if (link.attr("abs:href").contains(".mp3") && trim(link.text(), 100).replace("download","").length()>10)
                    print("%s  %s", link.attr("abs:href"), trim(link.text(), 100).replace("download",""));
//                }
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}