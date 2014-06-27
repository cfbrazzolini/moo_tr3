package controllers;

import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import Util.UtilControladoras;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;

public class dxr extends Controller {
  private static List<String> montarRangeTdi(String serie_inicial, int serie_final) {
    return UtilControladoras.montarRange("tdi",
        UtilControladoras.serieParametro.indexOf(serie_inicial), serie_final);
  }

  private static List<String> montarRangeTxr(String code, int serie_final) {
    return UtilControladoras.montarRange("txr", UtilControladoras.serieParametro.indexOf(code),
        serie_final);
  }

  public static Result index(String area, String code, String serie_inicial, String serie_final,
      String params) {
    DB db = MongoConnect.connect();
    if (db == null) {
      return TODO;
    } else {
      // monta uma lista com as colunas que serão utilizadas no calculo do coeficiente de correlação
      List<String> serieTdi = null;
      List<String> serieRangeTxr = null;
      if (serie_final == null && UtilControladoras.serieParametro.indexOf(serie_inicial) != -1) {
        serieTdi = montarRangeTdi(serie_inicial, UtilControladoras.SERIE_LIMITE);
        serieRangeTxr = montarRangeTxr(serie_inicial, UtilControladoras.SERIE_LIMITE);
      } else if (UtilControladoras.serieParametro.indexOf(serie_inicial) != -1
          && UtilControladoras.serieParametro.indexOf(serie_final) != -1) {
        serieTdi = montarRangeTdi(serie_inicial, UtilControladoras.SERIE_LIMITE);
        serieRangeTxr = montarRangeTxr(serie_inicial, UtilControladoras.SERIE_LIMITE);
      } else {
        throw new RuntimeException("serie fora dos limites");
      }
      // ----------------------------------------------------------------------------------------------

      BasicDBObject query = new BasicDBObject();
      for (String serie : serieTdi) {
        // buscar todas as colunas das séries selecionadas que não tiverem o valor -- (nulo)
        query.append(serie, new BasicDBObject("$ne", "--"));

        // buscar todas as colunas das séries selecionadas
        // query.append(serie, "");
      }
    }
    return TODO;
  }
}
