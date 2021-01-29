package building.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CompanyEmployee{

	private long id;

	private String idCard;
	
	private String name;

	private Date dateOfBirth;
	
	private String phone;
	
	private int companyId;
	
	public String getSimpleDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(dateOfBirth);
		return String.format("%d-%d-%d", c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}

}
