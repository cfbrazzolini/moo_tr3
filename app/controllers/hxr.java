package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import Util.UtilControladoras;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class hxr extends Controller {

  public static Result index(String area, String code, String serie_inicial, String serie_final,
      String params) {
    DB db = MongoConnect.connect();
    if (db == null) {
      return TODO;
    } else {
      // monta uma lista com as colunas que serão utilizadas no calculo do coeficiente de correlação
      List<String> serieMha = null;
      List<String> serieRangeTxr = null;
      if (serie_final == null && UtilControladoras.serieParametro.indexOf(serie_inicial) != -1) {
        serieMha = UtilControladoras.montarRangeMha(serie_inicial, UtilControladoras.SERIE_LIMITE);
        serieRangeTxr = UtilControladoras.montarRangeTxr(serie_inicial,
            UtilControladoras.SERIE_LIMITE);
      } else if (UtilControladoras.serieParametro.indexOf(serie_inicial) != -1
          && UtilControladoras.serieParametro.indexOf(serie_final) != -1) {
        if (UtilControladoras.serieParametro.indexOf(serie_final) != -1) {
          serieMha = UtilControladoras.montarRangeMha(serie_inicial,
              UtilControladoras.serieParametro.indexOf(serie_final) + 1);
          serieRangeTxr = UtilControladoras.montarRangeTxr(serie_inicial,
              UtilControladoras.serieParametro.indexOf(serie_final) + 1);
        }
      } else {
        throw new RuntimeException("serie fora dos limites");
      }
      // ----------------------------------------------------------------------------------------------
      BasicDBObject query = new BasicDBObject();
      if (area != null) {
        query = restringeArea(query, area);
      } else if (code != null) {
        query.append("CodigoDoMunicipio", code);
      }
      DBCursor resultado = db.getCollection("merge").find(query);
      if (resultado != null) {
        System.out.println(resultado);
      }

      ArrayList<Double> mhaList = new ArrayList<Double>();
      ArrayList<Double> txrList = new ArrayList<Double>();
      UtilControladoras.selectResultData(resultado, serieMha, serieRangeTxr, mhaList, txrList);

      ObjectNode result = Json.newObject();
      result.put("Query", "Taxa de distorção idade-série X Taxa de rendimento");
      if (mhaList.size() == 0) {
        result.put("Result", "Sem dados válidos");
        return ok(result);
      }

      System.out.println("MHA X TXR");
      for (int i = 0; i < mhaList.size(); ++i) {
        String output = "";
        output += mhaList.get(i);
        output += " x ";
        output += txrList.get(i);
        System.out.println(output);
        result.put("entrada " + i, mhaList.get(i) + " x " + txrList.get(i));
      }

      // http://localhost:9000/distxrend?area=DF&serie_inicial=b1&serie_final=b9
      Double resultFinalPearson = Correlacao.calculaCorrelacao(mhaList, txrList, "pearson");
      Double resultFinalSpearman = Correlacao.calculaCorrelacao(mhaList, txrList, "spearman");
      result.put("Correlacao de Pearson", resultFinalPearson);
      result.put("Correlacao de Spearman", resultFinalSpearman);
      System.out.println("Resultados da correlacao MHA e TXR Pearson " + resultFinalPearson);
      System.out.println("Resultados da correlacao MHA e TXR Spearman " + resultFinalSpearman);
      return ok(result);
    }
  }

  public static BasicDBObject restringeArea(BasicDBObject query, String area) {
    if ("brasil".equals(area)) {
      // casa com todas as entradas pois todas são de 2010
      query.append("Ano", 2010);
    } else if ("norte".equals(area)) {
      query.append("Regiao", "Norte");
    } else if ("nordeste".equals(area)) {
      query.append("Regiao", "Nordeste");
    } else if ("sudeste".equals(area)) {
      query.append("Regiao", "Sudeste");
    } else if ("sul".equals(area)) {
      query.append("Regiao", "Sul");
    } else if ("centroeste".equals(area)) {
      query.append("Regiao", "Centro_Oeste");
    } else if (area.split(",").length > 1) {
      String[] ufs = area.split(",");
      List<String> ufsList = Arrays.asList(ufs);
      BasicDBList ufsDBList = new BasicDBList();
      ufsDBList.addAll(ufsList);
      DBObject inClause = new BasicDBObject("$in", ufsDBList);
      query.append("UF", inClause);
    } else if (UtilControladoras.estadosBrasil.contains(area)) {
      query.append("UF", area);
    }
    return query;
  }
}
