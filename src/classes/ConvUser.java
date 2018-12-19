package classes;

public class ConvUser {
    
    int id_conv;
    int id_user;

    public ConvUser(int id_conv, int id_user) {
        this.id_conv = id_conv;
        this.id_user = id_user;
    }

    public int getId_conv() {
        return id_conv;
    }

    public void setId_conv(int id_conv) {
        this.id_conv = id_conv;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
