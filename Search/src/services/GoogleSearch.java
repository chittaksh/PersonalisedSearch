package services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.Result;

public class GoogleSearch {

	public static List<Result> main(String key) {

		List<Result> bingResult = new ArrayList<Result>();

		try {

			URL url = new URL("http", "www.google.com", "/search?q=" + key.replace(" ", "+"));
			// System.out.println(url);

			// System.out.println("<font color=#ffce9d size=2>Downloading [" +
			// url + "]...</font>\n");
			String html = downloadString(url);
			// System.out.println(html);

			Document doc = Jsoup.parse(html);
			// System.out.println("Doc = "+doc);
			Elements links = doc.select("div[class=g]");
			// System.out.println("Links = " + links);

			for (Element link : links) {

				Result result = new Result();
				if (link.select("cite").text().contains("http")) {
					result.setUrl(new URL(link.select("cite").text()));
				} else {
					result.setUrl(new URL("http://" + link.select("cite").text()));
				}
				result.setDesc(link.select("span[class=st]").text());
				result.setTitle(link.select("h3[class=r]").text());
				result.setService("Google");
				result.setOld(false);
				result.setMetakeys(key);

				// System.out.println(result.getUrl());

				bingResult.add(result);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return bingResult;
	}

	private static String downloadString(final InputStream stream) throws IOException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		while (-1 != (ch = stream.read()))
			out.write(ch);
		return out.toString();
	}

	private static String downloadString(final URL url) throws IOException {
		final String agent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US)";
		final URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", agent);
		final InputStream stream = connection.getInputStream();
		return downloadString(stream);
	}
}