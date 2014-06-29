package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import Util.UtilControladoras;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class dxr extends Controller {

  public static Result index(String area, String code, String serie_inicial, String serie_final,
      String params) 
  {
    DB db = MongoConnect.connect();
    if (db == null) 
    {
      return TODO;
    } 
    else
    {
      // monta uma lista com as colunas que serão utilizadas no calculo do coeficiente de correlação
      List<String> serieTdi = null;
      List<String> serieRangeTxr = null;
      if (serie_final == null && UtilControladoras.serieParametro.indexOf(serie_inicial) != -1) 
      {
        serieTdi = UtilControladoras.montarRangeTdi(serie_inicial, UtilControladoras.SERIE_LIMITE);
        serieRangeTxr = UtilControladoras.montarRangeTxr(serie_inicial, UtilControladoras.SERIE_LIMITE);
      } 
      else if (UtilControladoras.serieParametro.indexOf(serie_inicial) != -1
          && UtilControladoras.serieParametro.indexOf(serie_final) != -1) 
      {
        serieTdi = UtilControladoras.montarRangeTdi(serie_inicial, UtilControladoras.SERIE_LIMITE);
        serieRangeTxr = UtilControladoras.montarRangeTxr(serie_inicial, UtilControladoras.SERIE_LIMITE);
      }
      else 
      {
        throw new RuntimeException("serie fora dos limites");
      }
      // ----------------------------------------------------------------------------------------------
      BasicDBObject query = new BasicDBObject();
      query = restringeArea(query, area);
      DBCursor resultado = db.getCollection("merge").find( query );
      if (area != null) {
        query = restringeArea(query, area);
      } else if (code != null) {
        // TODO - filipe restringe código
      }
      
      ArrayList< Double > tdiList = new ArrayList< Double >();
	  ArrayList< Double > txrList = new ArrayList< Double >();
	  UtilControladoras.selectResultData(resultado, serieTdi, serieRangeTxr, tdiList, txrList);
	  
	  System.out.println( "TDI X TXR" );
	  for( int i = 0; i < tdiList.size(); ++i )
	  {
		  String output = "";
		  output += tdiList.get(i);
		  output += " x ";
		  output += txrList.get(i);
		  System.out.println( output );
	  }
	  
	  //http://localhost:9000/distxrend?area=DF&serie_inicial=b1&serie_final=b9
	  Double resultFinalPearson  = Correlacao.calculaCorrelacao(tdiList, txrList, "pearson");
	  Double resultFinalSpearman = Correlacao.calculaCorrelacao(tdiList, txrList, "spearman");
	  System.out.println( "Resultados da correlacao TDI e TXR Pearson " +  resultFinalPearson );
	  System.out.println( "Resultados da correlacao TDI e TXR Spearman " +  resultFinalSpearman );
    }
    return TODO;
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
