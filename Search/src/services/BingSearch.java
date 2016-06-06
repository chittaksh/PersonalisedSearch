//import java.io.IOException;
//import java.io.*;
package services;

import java.net.URL;
import java.util.*;
import model.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BingSearch {

	public static List<Result> main(String key) {

		List<Result> result = new ArrayList<Result>();

		Document doc;
		try {

			// System.out.println(key);
			String urlString = "http://www.bing.com/search?q=" + key.replace(" ", "+");
			doc = Jsoup.connect(urlString).get();
			// System.out.println(urlString);

			Elements links = doc.select("a[href]");

			for (Element link : links) {

				// get the value from href attribute
				String s = link.attr("href");

				if (s.contains("http://") || s.contains("https://")) {

					Result re = new Result();

					if (link.attr("href").contains("http")) {
						re.setUrl(new URL(link.attr("href")));
					} else {
						re.setUrl(new URL("http://" + link.attr("href")));
					}
					re.setTitle(link.text());
					re.setOld(false);
					re.setMetakeys(key);
					re.setService("Bing");
					re.setVisitCount(0);

					result.add(re);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}