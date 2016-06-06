package helper;

public class connectionObject
{
	private String serverLink;
	private String username;
	private String password;
	
	public connectionObject(String serverLink, String username, String password)
	{
		super();
		this.serverLink = serverLink;
		this.username = username;
		this.password = password;
	}

	public String getServerLink()
	{
		return serverLink;
	}

	public void setServerLink(String serverLink)
	{
		this.serverLink = serverLink;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}