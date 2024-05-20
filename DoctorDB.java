package cln;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDB {
	
	private ProektDB proektDB;

    public DoctorDB() throws SQLException, ClassNotFoundException {
        proektDB = new ProektDB();
    }
	
  public void  Save (Doctor doctor) throws SQLException, ClassNotFoundException {
	        String sql = "INSERT INTO Doctor (doctorId, name, lastName, numberDistrict) VALUES (?, ?, ?, ?)";
	        try (Connection conn = proektDB.connectionDB();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	pstmt.setInt(1, findDoctorId());
	            pstmt.setString(2, doctor.getName());
	            pstmt.setString(3, doctor.getLastName());
	            pstmt.setInt(4, doctor.getNumberDistrict());
	            pstmt.executeUpdate();
	        }
  }
	  
  public int findDoctorId() throws SQLException, ClassNotFoundException {
	   int max = 0;
	   String sql = "SELECT max (doctorId) maxId FROM Doctor";
	   try(Connection conn = proektDB.connectionDB();
			   PreparedStatement pstmt = conn.prepareStatement(sql)){
		   ResultSet res = pstmt.executeQuery();
		   if (res.next()) {
			   max = res.getInt("maxId");
		   }
	   }
	   return max+1;
	   
  }  
  
  public Doctor read (int doctorId) throws SQLException, ClassNotFoundException {
      String sql = "SELECT * FROM Doctor WHERE doctorId = ?";
      try (Connection conn = proektDB.connectionDB();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setInt(1, doctorId);
          try (ResultSet rs = pstmt.executeQuery()) {
              if (rs.next()) {
                  Doctor doctor = new Doctor();
                  doctor.setDoctorId(rs.getInt("doctorId"));
                  doctor.setName(rs.getString("name"));
                  doctor.setLastName(rs.getString("lastName"));
                  doctor.setNumberDistrict(rs.getInt("numberDistrict"));
                  return doctor;
              } else {
                  throw new SQLException("Doctor with ID " + doctorId + " no find.");
              }
          }
      }
  }
}
