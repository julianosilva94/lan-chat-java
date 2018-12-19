package classes;

import java.util.Date;

public class Conv {
    
    int id_conv;
    int id_remetente;
    Date data;
    String texto;

    public int getId_conv() {
        return id_conv;
    }

    public void setId_conv(int id_conv) {
        this.id_conv = id_conv;
    }

    public int getId_remetente() {
        return id_remetente;
    }

    public void setId_remetente(int id_remetente) {
        this.id_remetente = id_remetente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Conv() {
    }

    public Conv(int id_conv, int id_remetente, Date data, String texto) {
        this.id_conv = id_conv;
        this.id_remetente = id_remetente;
        this.data = data;
        this.texto = texto;
    }
}
