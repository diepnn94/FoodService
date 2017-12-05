
public class Guest {
	private String Gname = null;
	private String Gphone = null;
	private String Gemail = null;
	public Guest(String name, String phone, String email){
		Gname = name;
		Gphone = phone; 
		Gemail = email;
	}
	
	public String getGName(){
		return Gname;
	}
	public String getGphone(){
		return Gphone;
	}
	
	public String getEmail(){
		return Gemail;
	}
}
