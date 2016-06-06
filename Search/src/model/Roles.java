package model;

public enum Roles
{
	Manager(1), User(2);
	private int Value;

	private Roles(int value)
	{
		this.Value = value;
	}
	
	public static Roles getRole(int value){
		Roles temp = null;
		for(Roles role: Roles.values()){
			if(role.Value==value){
				temp = role;
				break;
			}
		}
		return temp;
	}
	
	public static int getValue(String value){
		Roles temp = null;
		for(Roles role: Roles.values()){
			if(role.name().equals(value)){
				temp = role;
			break;
			}
		}
		return temp.Value;
	}
}