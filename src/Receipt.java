import java.util.Date;
import java.util.Formatter;
import java.io.File;

public class Receipt {
	private String orderInfo =  null;
	private Guest guest;
	
	public Receipt(String text, Guest guestInfo){
		orderInfo = text;
		guest = guestInfo;
	}
	private String getTime(){
		Date date = new Date();
		return date.toString();
	}
	public void writeReceipt(){
		String time = this.getTime();
		time = time.replace(" ", "");
		time = time.replace(":", "");
		String path = "C:\\Users\\Amy\\Desktop\\receipt" +"\\"+ time + ".txt";
		File file = new File(path);
		if (file.exists()){
			System.out.println("File Already Existed!");
		}
		else{
			try{
				if (file.createNewFile()){
					Formatter f = new Formatter(path);
					f.format("Hi %s, \r\nEmail: %s \r\nPhone: %s \r\n\r\n%s", guest.getGName(), guest.getEmail(), guest.getGphone(), orderInfo);
					f.close();
				}
				
			}
			catch(Exception e){
				System.out.println("ERROR: FILE WAS NOT CREATED");
			}
			
			
		}
	}
}
