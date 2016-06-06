package model;

import java.net.URL;

public class Result {

	private int id;
	private int visitCount;
	private URL url;
	private String title;
	private String desc;
	private String metakeys;
	private String service;
	private boolean old;

	public Result() {

	}

	public Result(int visitCount, URL url, String title, String desc, String metakeys, String service,
			boolean old) {
		super();
		this.visitCount = visitCount;
		this.url = url;
		this.title = title;
		this.desc = desc;
		this.metakeys = metakeys;
		this.service = service;
		this.old = old;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMetakeys() {
		return metakeys;
	}

	public void setMetakeys(String metakeys) {
		this.metakeys = metakeys;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public boolean isOld() {
		return old;
	}

	public void setOld(boolean old) {
		this.old = old;
	}
}
