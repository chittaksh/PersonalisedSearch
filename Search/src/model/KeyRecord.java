package model;

public class KeyRecord {

	private int id;
	private String key;
	private int count;

	public KeyRecord(int id, String key, int count) {
		super();
		this.id = id;
		this.key = key;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
