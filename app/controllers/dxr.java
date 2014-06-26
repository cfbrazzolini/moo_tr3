package controllers;

import java.util.List;

import play.mvc.Result;

import com.mongodb.DB;

public class dxr extends UtilControladoras {

  public static Result index(String area, String code, String serie_inicial, String serie_final,
      String params) {
    DB db = MongoConnect.connect();
    if (db == null) {
      return TODO;
    } else {
      // monta uma lista com as colunas que serão utilizadas no calculo do coeficiente de correlação
      if (serie_final == null && serieParametro.indexOf(code) != -1) {
        List<String> serieRangeTdi = montarRange("tdi", serieParametro.indexOf(code), SERIE_LIMITE);
        List<String> serieRangeTxr = montarRange("txr", serieParametro.indexOf(code), SERIE_LIMITE);
      }

    }
    return TODO;
  }
}
