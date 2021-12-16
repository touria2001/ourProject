package Model;

public class message {
    private String  phone, img;
    public message() {

    }

    public message(String phone, String img) {
        this.phone = phone;
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
