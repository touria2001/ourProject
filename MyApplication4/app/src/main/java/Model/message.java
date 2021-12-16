package Model;

public class message {
    private String  phone, image;
    public message() {

    }

    public message(String phone, String image) {
        this.phone = phone;
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImg(String image) {
        this.image = image;
    }
}
