
import java.util.*;

// ENUM ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

enum Specialization{
    CARDIOLOGIST,
    DERMATOLOGIST,
    ORTHOPEDIC,
    GENERAL_PHYSICIAN
}

// custom exception----------------------------------------------------------------------------------------------------------------------------------------------------------------------

class SlotUnavailableException extends Exception{
    public SlotUnavailableException(String message){
        super(message);
    }
}

// DOCTOR CLASS-------------------------------------------------------------------------------------------------------------------------------------------------------------

class Doctor{
    private int doctorId;
    private String name;
    private Specialization specialization;
    private List<String> availableSlots;

    public Doctor(int doctorId, String name, Specialization specialization){
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.availableSlots = new ArrayList<>();
    }

    public void addSlot(String slot){
        availableSlots.add(slot);
    }

    public boolean isSlotAvailable(String slot){
        return availableSlots.contains(slot);
    }

    public void removeSlot(String slot){
        availableSlots.remove(slot);
    }

    public int getDoctorId(){
        return doctorId;
    }

    public Specialization getSpecialization(){
        return specialization;
    }

    public String getName(){
        return name;
    }

    public List<String> getAvailableSlots(){
        return availableSlots;
    }

    @Override
    public String toString(){
        return "Doctor ID: " + doctorId + ", Name: " + name + ", Specialization: " + specialization + ", Slots: " + availableSlots;
    }
}

// APPOINTMENT CLASS----------------------------------------------------------------------------------------------------------------------------------------------------

class Appointment{
    private String patientName;
    private Doctor doctor;
    private String slot;

    public Appointment(String patientName, Doctor doctor, String slot){
        this.patientName = patientName;
        this.doctor = doctor;
        this.slot = slot;
    }

    @Override
    public String toString(){
        return "Patient: " + patientName + ", Doctor: " + doctor.getName() + ", Slot: " + slot;
    }

}

// SERVICE CLASS-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

class ConsultationScheduler{

    private Map<Specialization, List<Doctor>> doctorMap = new HashMap<>();
    private List<Appointment> consultationHistory = new ArrayList<>();
    
    public void addDoctor(Doctor doctor){
        doctorMap.computeIfAbsent(doctor.getSpecialization(),k -> new ArrayList<>()).add(doctor);
    }

    public void viewDoctors(){
        if(doctorMap.isEmpty()){
            System.out.println("No doctors available");
            return;
        }

        for(Specialization spec : doctorMap.keySet()){
            System.out.println("\nSpecialization: " + spec);
            for(Doctor doc:doctorMap.get(spec)){
                System.out.println(doc);
            }
        }
    }

    public void bookAppointment(String patientName, Specialization specialization, int doctorId, String slot) throws SlotUnavailableException{

        List<Doctor> doctors = doctorMap.get(specialization);

        if(doctors == null){
            System.out.println("No doctors available in this specialization.");
            return;
        }

        for(Doctor doctor : doctors){
            if(doctor.getDoctorId() == doctorId){

                if(!doctor.isSlotAvailable(slot)){
                    throw new SlotUnavailableException("Slot not available!");
                }

                doctor.removeSlot(slot);
                Appointment appointment = new Appointment(patientName, doctor, slot);
                consultationHistory.add(appointment);

                System.out.println("Appointment Booked Successfully!");
                return;
            }
        }
        System.out.println("Doctor not found.");
    }

    public void viewConsultationHistory(){
        if(consultationHistory.isEmpty()){
            System.out.println("No consultations yet.");
            return;
        }

        for(Appointment appt : consultationHistory){
            System.out.println(appt);
        }
    }
}

// MAIN CLASS--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class DoctorConsultation{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ConsultationScheduler scheduler = new ConsultationScheduler();
        while(true){
            System.out.println("\n-Online Doctor Consultation-");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Book Appointment");
            System.out.println("4. View Consultation History");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.print("Enter Doctor ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Doctor Name: ");
                    String name = sc.nextLine();
                    System.out.println("Choose Specialization:");
                    for(Specialization spec : Specialization.values()){
                        System.out.println(spec);
                    }
                    String specInput = sc.nextLine().toUpperCase();
                    Specialization specialization = Specialization.valueOf(specInput);

                    Doctor doctor = new Doctor(id, name, specialization);

                    System.out.print("How many slots to add? ");
                    int slotCount = sc.nextInt();
                    sc.nextLine();
                    for(int i=0; i<slotCount;i++){
                        System.out.print("Enter slot: ");
                        String slot = sc.nextLine();
                        doctor.addSlot(slot);
                    }
                    scheduler.addDoctor(doctor);
                    System.out.println("Doctor Added Successfully!");
                    break;

                case 2:
                    scheduler.viewDoctors();
                    break;
                case 3:
                    try{
                        System.out.print("Enter Patient Name: ");
                        String patient = sc.nextLine();
                        System.out.println("Enter Specialization:");
                        String sp = sc.nextLine().toUpperCase();
                        Specialization spec = Specialization.valueOf(sp);
                        System.out.print("Enter Doctor ID: ");
                        int docId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Slot: ");
                        String slot = sc.nextLine();
                        scheduler.bookAppointment(patient, spec, docId, slot);

                    }catch(SlotUnavailableException e){
                        System.out.println("NO" + e.getMessage());
                    }catch(Exception e){
                        System.out.println("Invalid Input!");
                    }
                    break;

                case 4:
                    scheduler.viewConsultationHistory();
                    break;

                case 5:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
