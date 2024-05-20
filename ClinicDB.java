package cln;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClinicDB {
	    private ProektDB proektDB;
	    public ClinicDB() throws SQLException, ClassNotFoundException {
	        proektDB = new ProektDB();
	    }

    	public void save(Clinic clinic) throws SQLException, ClassNotFoundException {
		    String sql = String.format("INSERT INTO Clinic (clinicId, address, number)" + "VALUES ('%d', '%s', '%d')", findClinicId(), clinic.getAddress(), clinic.getNumber());
		    System.out.println(sql);
		      try (Connection conn = proektDB.connectionDB();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.executeUpdate();
		        }
    	}
    	
    	  public int findClinicId() throws SQLException, ClassNotFoundException {
  	    	int max = 0;
  	    	String sql = "SELECT max (clinicId) maxId From Clinic";
  	    	try(Connection conn = proektDB.connectionDB();
  	    			PreparedStatement pstmt = conn.prepareStatement(sql)){
  	    		ResultSet res = pstmt.executeQuery();
  	    		if (res.next()) {
  	    			max = res.getInt("maxId");
  	    		}
  	    	}
  	    	return max+1;
    	  }
    	  
    	  
    	  public Clinic read(int clinicId) throws SQLException, ClassNotFoundException {
    	        String sql = "SELECT * FROM Clinic WHERE clinicId = ?";
    	        try (Connection conn = proektDB.connectionDB();
    	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    	            pstmt.setInt(1, clinicId);
    	            try (ResultSet rs = pstmt.executeQuery()) {
    	                if (rs.next()) {
    	                    Clinic clinic = new Clinic();
    	                    clinic.setClinicId(rs.getInt("clinicId"));
    	                    clinic.setNumber(rs.getInt("number"));
    	                    clinic.setAddress(rs.getString("address"));
    	                    return clinic;
    	                } else {
    	                    throw new SQLException("Clinic with Id not find!"+ clinicId);
    	                }
    	            }
    	        }
    	  }
}
