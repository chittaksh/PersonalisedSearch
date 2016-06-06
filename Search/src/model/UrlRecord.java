package model;

import java.net.URL;

public class UrlRecord {

	private int id;
	private URL url;
	private int count;

	public UrlRecord(int id, URL url, int count) {
		super();
		this.id = id;
		this.url = url;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
