package project.bookrental.management;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class RentalTaskDTO implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -871761011538307620L;
	private String userId;
	private String bookId;
	private String rentalDay;
	private String returnDay;
	private String arrears;
	private UserDTO userDTO;
	private SeperateBookDTO seperateBookDTO;
	
	public RentalTaskDTO() {}
	
	public RentalTaskDTO(SeperateBookDTO seperateBookDTO, UserDTO userDTO) {
		this.seperateBookDTO = seperateBookDTO;
		this.userDTO = userDTO;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getRentalDay() {
		return rentalDay;
	}

	public void setRentalDay() {
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar time = Calendar.getInstance();
		time.add(Calendar.DATE, -3);
		rentalDay = sdfm.format(time.getTime());
		time.add(Calendar.DATE, 2);
		this.returnDay = sdfm.format(time.getTime());
		
	}

	public String getReturnDay() {
		return returnDay;
	}

	public void setReturnDay(String returnDay) {
		this.returnDay = returnDay;
	} 

	public String getArrears(String returnDay) { 
		Calendar time = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date to = Date.valueOf(returnDay);
			Date current = new Date(System.currentTimeMillis());
			
			long calDate = to.getTime() - current.getTime();
	        long calDateDays = calDate / ( 24*60*60*1000); 
	        calDateDays = Math.abs(calDateDays);
	        arrears = String.valueOf(calDateDays);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrears;
	}

	public void setArrears() {
 
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public SeperateBookDTO getSeperateBookDTO() {
		return seperateBookDTO;
	}

	public void setSeperateBookDTO(SeperateBookDTO seperateBookDTO) {
		this.seperateBookDTO = seperateBookDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
