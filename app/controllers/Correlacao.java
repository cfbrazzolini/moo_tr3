package controllers;

import java.util.ArrayList;

import org.apache.commons.math3.stat.correlation.*;

/*
 * @param val1 primeiro ArrayList de valores
 * @param val2 segundo ArrayList de valores
 * @param param tipo de correlação a ser calculada: Pearson = pearson, Spearman = spearman
 */
public class Correlacao {
	static double calculaCorrelacao(ArrayList<Double> val1, ArrayList<Double> val2, String param)
	{
		int size = val1.size();
		
		double[] valores1 = new double[size];
		double[] valores2 = new double[size];
		
		for(int i = 0; i < size; i++)
		{
			valores1[i] = val1.get(i);	
			valores2[i] = val2.get(i);
		}
		
		if(param == "pearson")
		{
			PearsonsCorrelation pearsons = new PearsonsCorrelation();
			
			return pearsons.correlation(valores1, valores2);
		}else if(param == "spearman")
		{
			SpearmansCorrelation spearmans = new SpearmansCorrelation();
			
			return spearmans.correlation(valores1, valores2);
		}else
		{
			return 0;
		}
	}
}
