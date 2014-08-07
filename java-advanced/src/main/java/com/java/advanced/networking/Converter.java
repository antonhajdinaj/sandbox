package com.java.advanced.networking;

public class Converter {

	public static double euroToUSD(double euro) {
		return 1.3843 * euro;
	}

	public static double usdToEuro(double usd) {
		return usd / 1.3843;
	}
}
