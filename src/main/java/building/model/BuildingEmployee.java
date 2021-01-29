package building.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.Data;
@Data

public class BuildingEmployee {
	

	private long id;
	
	private String name;
	
	private Date dateOfBirth;
	
	private String address;
	
	private String phone;
	
	private String ranking;
	
	private String position;
	
	private int serviceId;

	public String getSimpleDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(dateOfBirth);
		return String.format("%d-%d-%d", c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}
	
}
