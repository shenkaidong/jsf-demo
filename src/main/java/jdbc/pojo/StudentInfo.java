package jdbc.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class StudentInfo {

		private int id;
		private String firstName;
		private String lastName;
		private String email;
		private String addressLine1;
		private String addressLine2;
		private String city;
		private String state;
		private String zipCode;

		private List<String> stateOptions;
		
		public StudentInfo() {
			// populate the list of countries
			stateOptions = new ArrayList<>();
			
			stateOptions.add("NJ");stateOptions.add("AL");stateOptions.add("AK");stateOptions.add("AZ");stateOptions.add("AR");
			stateOptions.add("CA");stateOptions.add("CO");stateOptions.add("CT");stateOptions.add("DE");stateOptions.add("FL");
			stateOptions.add("GA");stateOptions.add("HI");stateOptions.add("ID");stateOptions.add("IL");stateOptions.add("IN");
			stateOptions.add("IA");stateOptions.add("KS");stateOptions.add("KY");stateOptions.add("LA");stateOptions.add("ME");
			stateOptions.add("MD");stateOptions.add("MA");stateOptions.add("MI");stateOptions.add("MN");stateOptions.add("MS");
			
			stateOptions.add("MO");stateOptions.add("MT");stateOptions.add("NE");stateOptions.add("NV");stateOptions.add("NH");
			stateOptions.add("NM");stateOptions.add("NY");stateOptions.add("NC");stateOptions.add("ND");stateOptions.add("OH");
			stateOptions.add("OK");stateOptions.add("OR");stateOptions.add("PA");stateOptions.add("RI");stateOptions.add("SC");
			stateOptions.add("SD");stateOptions.add("TN");stateOptions.add("TX");stateOptions.add("UT");stateOptions.add("VT");
			stateOptions.add("VA");stateOptions.add("WA");stateOptions.add("WV");stateOptions.add("WI");stateOptions.add("WY");
	
			stateOptions.add("AS");stateOptions.add("GU");stateOptions.add("PR");stateOptions.add("MP");stateOptions.add("VI");
		}
		
		public List<String> getstateOptions() {
			return stateOptions;
		}
		
		public StudentInfo(int id, String firstName, String lastName, String email, String addressLine1, String addressLine2, String city, String state, String zipCode) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.addressLine1 = addressLine1;
			this.addressLine2 = addressLine2;
			this.city = city;
			this.state = state;
			this.zipCode = zipCode;
			
			// populate the list of countries
			stateOptions = new ArrayList<>();
			
			stateOptions.add("NJ");stateOptions.add("AL");stateOptions.add("AK");stateOptions.add("AZ");stateOptions.add("AR");
			stateOptions.add("CA");stateOptions.add("CO");stateOptions.add("CT");stateOptions.add("DE");stateOptions.add("FL");
			stateOptions.add("GA");stateOptions.add("HI");stateOptions.add("ID");stateOptions.add("IL");stateOptions.add("IN");
			stateOptions.add("IA");stateOptions.add("KS");stateOptions.add("KY");stateOptions.add("LA");stateOptions.add("ME");
			stateOptions.add("MD");stateOptions.add("MA");stateOptions.add("MI");stateOptions.add("MN");stateOptions.add("MS");
			
			stateOptions.add("MO");stateOptions.add("MT");stateOptions.add("NE");stateOptions.add("NV");stateOptions.add("NH");
			stateOptions.add("NM");stateOptions.add("NY");stateOptions.add("NC");stateOptions.add("ND");stateOptions.add("OH");
			stateOptions.add("OK");stateOptions.add("OR");stateOptions.add("PA");stateOptions.add("RI");stateOptions.add("SC");
			stateOptions.add("SD");stateOptions.add("TN");stateOptions.add("TX");stateOptions.add("UT");stateOptions.add("VT");
			stateOptions.add("VA");stateOptions.add("WA");stateOptions.add("WV");stateOptions.add("WI");stateOptions.add("WY");
	
			stateOptions.add("AS");stateOptions.add("GU");stateOptions.add("PR");stateOptions.add("MP");stateOptions.add("VI");
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddressLine1() {
			return addressLine1;
		}

		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}

		public String getAddressLine2() {
			return addressLine2;
		}

		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		@Override
		public String toString() {
			return "StudentInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
					+ ", state=" + state + ", zipCode=" + zipCode + "]";
		}

}
