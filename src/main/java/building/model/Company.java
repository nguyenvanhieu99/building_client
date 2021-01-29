package building.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Data
public class Company {
	private long id;
	
	private String name;

	private String taxNumber;
	
	private double budget;
	
	private String field;

	private int employeeQuantity;
	
	private String address;
	
	private String phone;
	
	private double area;

}
