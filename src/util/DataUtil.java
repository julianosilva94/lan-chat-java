package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    public static java.util.Date converterData(java.sql.Date dataSql) {
	Date dataConvertidaEmUtil = new Date(dataSql.getTime());
	return dataConvertidaEmUtil;
    }
    
    public static String formatarData(java.util.Date data) {
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
        return dataFormatada;
    }
          
}
