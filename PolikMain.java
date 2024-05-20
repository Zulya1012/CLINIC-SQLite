package cln;

import java.sql.SQLException;
import java.util.Scanner;

public class PolikMain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    		Doctor doctor = new Doctor();
    		DoctorDB doctorDb = new DoctorDB();
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("1. Add Clinic");
            System.out.println("2. Add Doctor");
            System.out.println("3. Add Medical card for pasient");

            int choice = s.nextInt();
            s.nextLine(); 

            if (choice == 1) {
                Clinic clinic = new Clinic();
                ClinicDB clinicDb = new ClinicDB();

                System.out.println("Enter Id code");
                clinic.setClinicId(s.nextInt());

                System.out.println("Enter number clinic");
                clinic.setNumber(s.nextInt());

                s.nextLine(); 
                System.out.println("Enter Address");
                clinic.setAddress(s.nextLine());

                clinicDb.save(clinic);
                
            } else if (choice == 2) {
               

                System.out.println("Enter Id code doctor");
                doctor.setDoctorId(s.nextInt());
                s.nextLine(); 

                System.out.println("Enter name doctor");
                doctor.setName(s.nextLine());

                System.out.println("Enter lastName doctor");
                doctor.setLastName(s.nextLine());

                System.out.println("Enter number district");
                doctor.setNumberDistrict(s.nextInt());

                doctorDb.Save(doctor);
                
            } else if (choice == 3) {
                Karta karta = new Karta();
                KartaDB kartadb = new KartaDB();
                PasientDB pasientDb = new PasientDB();
                
                
                
                ClinicDB clinicDb = new ClinicDB();

                Pasient pasient = new Pasient();
                
                System.out.println("Enter phone number");
                pasient.setPhoneNumber(s.nextLine());
                int pasientId=pasientDb.getPasientId(pasient.getPhoneNumber());
                if (pasientId==0) {
               
                /*
                System.out.println("Enter id code pasient");
                pasient.setPasientId(s.nextInt());
                s.nextLine();
                
                */
                
                System.out.println("Enter name pasient");
                pasient.setName(s.nextLine());
                
                System.out.println("Enter lastname pasient");
                pasient.setLastName(s.nextLine());
                
                System.out.println("Enter address pasient");
                pasient.setAddress(s.nextLine());

                System.out.println("Enter phone number");
                pasient.setPhoneNumber(s.nextLine());

                pasient.setPasientId(pasientDb.save(pasient));
                System.out.println("ok"+ pasient.getPasientId());
                 
                } else {
                	
                	pasient.setPasientId(pasientId);
                }
                
                pasient = pasientDb.read(pasient.getPasientId());

                System.out.println("Enter date open card");
                karta.setDataOpenCard(s.nextLine());

                System.out.println("Enter number card");
                karta.setNumberCard(s.nextInt());
                s.nextLine(); 

                System.out.println("Enter  ID code doctor");
                int doctorId = s.nextInt(); 
                s.nextLine();
                
                System.out.println("Enter Id code clinic");
                int clinicId = s.nextInt();

                
                //karta.setDataOpenCard("01.02.24");
             
        
				karta.setDoctor(doctorDb.read(doctorId));  
                karta.setPasient(pasient);
         
				karta.setClinic(clinicDb.read(clinicId));
                kartadb.save(karta);   

                
            }
        }
    }
}
