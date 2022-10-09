public class Student {
    // fields.
    private String first_name;
    private double gpa;
    private String email;
    private String gender;

    // construction
    public Student(){

    }
    public Student(String first_name, double gpa, String email, String gender){
        this.first_name = first_name;
        this.gpa = gpa;
        this.email = email;
        this.gender = gender;
    }

    public void setFirstName(String first_name){
        this.first_name = first_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setGpa(double gpa){
        this.gpa = gpa;
    }

    public double getGpa(){
        return gpa;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }

}
