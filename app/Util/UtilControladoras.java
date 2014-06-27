package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import play.mvc.Controller;

public class UtilControladoras extends Controller {
  // valores admitidos nos campos serie_inicial e serie_final
  public static final List<String> serieParametro = new ArrayList<String>(Arrays.asList("b1", "b2",
      "b3", "b4", "b5", "b6", "b7", "b8", "b9", "m1", "m2", "m3"));

  // siglas dos estados, utilizadas para realizar as queries por range, admitidas no campo area
  public static final String[] estadosNorte = { "AC", "AP", "AM", "PA", "RO", "RR", "TO" };
  public static final String[] estadosNordeste = { "AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN",
      "SE" };
  public static final String[] estadosCentroOeste = { "GO", "MT", "MS" };
  public static final String[] estadosSul = { "PR", "RS", "SC" };
  public static final String[] estadosSudeste = { "ES", "MG", "RJ", "SP" };
  public static final String[] estadosBrasil = { "AC", "AP", "AM", "PA", "RO", "RR", "TO", "AL",
      "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE", "GO", "MT", "MS", "PR", "RS", "SC", "ES",
      "MG", "RJ", "SP" };

  // ultima série do arquivo é o terceiro ano do ensino médio,
  // contando desde o ensino básico é a décima segunda série
  public static final int SERIE_LIMITE = 12;

  public static List<String> montarRange(String prefixo, int inicio, int fim) {
    int i;
    List<String> range = new ArrayList<String>();
    for (i = inicio; i <= fim; i++) {
      if (i <= 9) {
        range.add(prefixo + "Ano" + i);
      } else {
        range.add(prefixo + "Ano" + (i - 9) + "Medio");
      }
    }
    return range;
  }

}