package util;


//import Classes.Configuracao;
import classes.Configuracao;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author 265551
 */
public class AcessaArquivo {

    public static void grava(Configuracao CF) {
        try {
            FileOutputStream arquivo = new FileOutputStream("lib/chat.cfg");
            ObjectOutputStream fluxo = new ObjectOutputStream(arquivo);
            fluxo.writeObject(CF);
            fluxo.flush();
            System.out.println("Dados gravados com sucesso no arquivo chat.cfg");
        } catch (Exception e) {
            System.out.println("Falha na gravação do arquivo" + (e));

        }

    }

    /**
     *
     * @return
     */
    public static Configuracao le() {
        Configuracao CF = new Configuracao();
        try {
            FileInputStream arquivo = new FileInputStream("lib/chat.cfg");
            ObjectInputStream fluxo = new ObjectInputStream(arquivo);
            CF = (Configuracao) fluxo.readObject();
            //System.out.println("Dados lidos com sucesso no arquivo chat.cfg");
        } catch (Exception e) {
            System.out.println("Falha na leitura do arquivo" + (e));
        }
        return CF;
    }

}
