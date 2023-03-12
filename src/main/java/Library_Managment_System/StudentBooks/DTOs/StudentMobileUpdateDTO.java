package Library_Managment_System.StudentBooks.DTOs;

public class StudentMobileUpdateDTO {
    private int id;
    private String mobile;

    //constructor
    public StudentMobileUpdateDTO() {

    }

    public StudentMobileUpdateDTO(int id, String mobile) {
        this.id = id;
        this.mobile = mobile;
    }

    //getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }


}
