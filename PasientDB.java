package cln;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasientDB {

    private ProektDB proektDB;

    public PasientDB() throws SQLException, ClassNotFoundException {
        proektDB = new ProektDB();
    }

    public int  save(Pasient pasient) throws SQLException, ClassNotFoundException {

    	String sql = String.format("INSERT INTO Pasient (pasientId, name, lastName, phoneNumber, address)" + "VALUES ('%d', '%s', '%s', '%s', '%s') ", findPasientId(), pasient.getName(), pasient.getLastName(), pasient.getPhoneNumber(), pasient.getAddress());
	        try (Connection conn = proektDB.connectionDB();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	System.out.println(pasient.getName() + " " + pasient.getLastName() + " " + pasient.getPhoneNumber()+ " " + pasient.getAddress()+ " " + findPasientId());
	            pstmt.executeUpdate();
	        	 
	        }
	        
	        
			return getPasientId(pasient.getPhoneNumber());
	        
	       
	    }
    
    public int findPasientId() throws SQLException, ClassNotFoundException {
		   int max = 0;
		   String sql = "SELECT max (pasientId) maxId FROM Pasient";
		   try(Connection conn = proektDB.connectionDB();
				   PreparedStatement pstmt = conn.prepareStatement(sql)){
			   ResultSet res = pstmt.executeQuery();
			   if (res.next()) {
				   max = res.getInt("maxId");
			   }
		   }
		   return max+1;
		   
	   }
 
    
    public int getPasientId(String phoneNumber) throws SQLException, ClassNotFoundException {
		   int id = 0;
		   String sql = String.format("SELECT pasientid  as id FROM Pasient where phonenumber='%s' " ,phoneNumber);
		   try(Connection conn = proektDB.connectionDB();
				   PreparedStatement pstmt = conn.prepareStatement(sql)){
			   ResultSet res = pstmt.executeQuery();
			   if (res.next()) {
				   id = res.getInt("id");
			   }
		   }
		   return id;
		   
	   }

     
    public Pasient read(int pasientId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Pasient WHERE pasientId = ?";
        try (Connection conn = proektDB.connectionDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pasientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Pasient pasient = new Pasient();
                    pasient.setPasientId(rs.getInt("pasientId"));
                    pasient.setName(rs.getString("name"));
                    pasient.setLastName(rs.getString("lastName"));
                    pasient.setAddress(rs.getString("address"));
                    pasient.setPhoneNumber(rs.getString("phoneNumber"));
                    return pasient;
                } else {
                    throw new SQLException("Pasient with ID not found");
                }
            }
        }
    }
}