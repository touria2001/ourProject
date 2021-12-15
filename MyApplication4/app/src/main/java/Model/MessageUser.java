package Model;

public class MessageUser {
    private String message,destinataire,user;

    public MessageUser() {

    }

    public MessageUser(String message, String destinataire, String user) {
        this.message = message;
        this.destinataire = destinataire;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
