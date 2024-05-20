package cln;

public class Karta {
	private int CardId;
    private String dataOpenCard;
    private int numberCard;
    private Pasient pasient;
    private Doctor doctor;
    private Clinic clinic;
    
 public int getCardId() {
   	return CardId;
 }
 public void setCardId(int cardId) {
   	CardId = cardId;
 }
 public String getDataOpenCard() {
  return dataOpenCard;
 }
 public void setDataOpenCard(String dateOpenCard) {
  this.dataOpenCard = dateOpenCard;
 }
 public int getNumberCard() {
  return numberCard;
 }
 public void setNumberCard(int numberCard) {
  this.numberCard = numberCard;
 }
 public Pasient getPasient() {
  return pasient;
 }
 public void setPasient(Pasient pasient) {
  this.pasient = pasient;
 }
 public Doctor getDoctor() {
  return doctor;
 }
 public void setDoctor(Doctor doctor) {
  this.doctor = doctor;
 }
 public Clinic getClinic() {
  return clinic;
 }
 public void setClinic(Clinic clinic) {
  this.clinic = clinic;
 }
   
}
