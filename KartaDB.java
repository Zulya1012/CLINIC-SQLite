package cln;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KartaDB {

    private ProektDB proektDB;

    public KartaDB() throws SQLException, ClassNotFoundException {
        proektDB = new ProektDB();
    }

    public void save(Karta karta) throws SQLException, ClassNotFoundException {
            String sql = "INSERT INTO Karta (doctorId, pasientId, clinicId, dataOpenCard, numberCard) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = proektDB.connectionDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, karta.getDoctor().getDoctorId());
            pstmt.setInt(2, karta.getPasient().getPasientId());
            pstmt.setInt(3, karta.getClinic().getClinicId());
            pstmt.setString(4, karta.getDataOpenCard());
            pstmt.setInt(5, karta.getNumberCard());
            pstmt.executeUpdate();
        }
    }

    public Karta read(int cardId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Karta WHERE cardId = ?";
        try (Connection conn = proektDB.connectionDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cardId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Karta karta = new Karta();
                    karta.setCardId(cardId);
                    karta.setDataOpenCard(rs.getString("dateOpenCard"));
                    karta.setNumberCard(rs.getInt("numberCard"));

                    int doctorId = rs.getInt("doctorId");
                    int pasientId = rs.getInt("pasientId");
                    int clinicId = rs.getInt("clinicId");

                    DoctorDB doctorDB = new DoctorDB();
                    PasientDB pasientDB = new PasientDB();
                    ClinicDB clinicDB = new ClinicDB();

                    karta.setDoctor(doctorDB.read(doctorId));
                    karta.setPasient(pasientDB.read(pasientId));
                    karta.setClinic(clinicDB.read(clinicId));

                    return karta;
                } else {
                    throw new SQLException("Medical card with CardId not found!");
                }
            }
        }
    }
}
