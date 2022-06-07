package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.pojo.StudentInfo;

public class StudentDbUtil {

	private static StudentDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/student_tracker";
	
	public static StudentDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new StudentDbUtil();
		}
		
		return instance;
	}
	
	private StudentDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<StudentInfo> getStudents() throws Exception {

		// List<Student> students = new ArrayList<>();

		List<StudentInfo> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from student order by last_name";
			String sqlAddress = "SELECT s.`id`, s.`first_name`,s.`last_name`,s.`email`,sa.`address_line_1`,sa.`address_line_2`,sa.`city`,sa.`state`,sa.`zip_code`\n"
					+ "FROM student AS s\n"
					+ "LEFT JOIN student_address AS sa\n"
					+ "ON s.`id` = sa.`id`\n"
					+ "ORDER BY s.`last_name`;";

			myStmt = myConn.createStatement();

			//myRs = myStmt.executeQuery(sql);
			myRs = myStmt.executeQuery(sqlAddress);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String addressLine1 = myRs.getString("address_line_1");
				String addressLine2 = myRs.getString("address_line_2");
				String city = myRs.getString("city");
				String state = myRs.getString("state");
				String zipCode = myRs.getString("zip_code");
				// create new student object
				StudentInfo tempStudent = new StudentInfo(id, firstName, lastName,
						email, addressLine1, addressLine2, city, state, zipCode);

				// add it to the list of students
				students.add(tempStudent);
			}
			
			return students;	

			/*
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName,
						email);

				// add it to the list of students
				students.add(tempStudent);
			}
			
			return students;		
			*/
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addStudent(StudentInfo theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		System.out.println("addStudent in DbUtil was run");
		try {
			myConn = getConnection();

			String sql = "insert into student (first_name, last_name, email) values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			myStmt.execute();
			
	        //get id
	        int id = -1;
	        ResultSet resultSet = myStmt.getGeneratedKeys();
	        if(resultSet.next()){
	            id = resultSet.getInt(1);
		        System.out.println(id);
	        }

			String sqladdress = "insert into student_address (id, address_line_1, address_line_2, city, state, zip_code) values (?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sqladdress);

			// set params
			myStmt.setInt(1, id);			
			myStmt.setString(2, theStudent.getAddressLine1());
			myStmt.setString(3, theStudent.getAddressLine2());
			myStmt.setString(4, theStudent.getCity());
			myStmt.setString(5, theStudent.getState());
			myStmt.setString(6, theStudent.getZipCode());
			myStmt.execute();
			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public StudentInfo getStudent(int studentId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "SELECT s.`id`, s.`first_name`,s.`last_name`,s.`email`,sa.`address_line_1`,sa.`address_line_2`,sa.`city`,sa.`state`,sa.`zip_code`\n"
					+ "FROM student AS s\n"
					+ "LEFT JOIN student_address AS sa\n"
					+ "ON s.`id` = sa.`id`\n"
					+ "WHERE s.`id`=?;";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			myRs = myStmt.executeQuery();

			StudentInfo theStudent = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String addressLine1 = myRs.getString("address_line_1");
				String addressLine2 = myRs.getString("address_line_2");
				String city = myRs.getString("city");
				String state = myRs.getString("state");
				String zipCode = myRs.getString("zip_code");
				
				theStudent = new StudentInfo(id, firstName, lastName,
						email, addressLine1, addressLine2, city, state, zipCode);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}

			return theStudent;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateStudent(StudentInfo theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update student "
						+ " set first_name=?, last_name=?, email=?"
						+ " where id=?";
			
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			myStmt.execute();
			
			String sqladdress = "update student_address set address_line_1=?, address_line_2=?, city=?, state=?, zip_code=? where id=?";
			
			myStmt = myConn.prepareStatement(sqladdress);

			// set params
		
			myStmt.setString(1, theStudent.getAddressLine1());
			myStmt.setString(2, theStudent.getAddressLine2());
			myStmt.setString(3, theStudent.getCity());
			myStmt.setString(4, theStudent.getState());
			myStmt.setString(5, theStudent.getZipCode());
			
			System.out.println(theStudent.getZipCode());
			
			myStmt.setInt(6, theStudent.getId());	
			
			myStmt.execute();
			
			System.out.println("getZipCode: " + theStudent.getZipCode());
		}
		finally {
			close (myConn, myStmt);
		}	
	}
	
	public void deleteStudent(int studentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from student where id=?";
			
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);
			
			myStmt.execute();
			
			String sqlAddress = "delete from student_address where id=?";
			myStmt = myConn.prepareStatement(sqlAddress);
			myStmt.setInt(1, studentId);			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	
}
