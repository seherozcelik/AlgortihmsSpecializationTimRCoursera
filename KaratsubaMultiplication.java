package algorithmCourseStandford;

import java.math.BigDecimal;

public class KaratsubaMultiplication {

	public static void main(String[] args) {
		BigDecimal dd = calculateMult("3141592653589793238462643383279502884197169399375105820974944592",
				"2718281828459045235360287471352662497757247093699959574966967627", 64);
		System.out.println(dd);
	}

	private static BigDecimal calculateMult(String first, String second, int n) {
		if (n == 1) {
			return new BigDecimal(first).multiply(new BigDecimal(second));
		}
		String a = first.substring(0, n / 2);
		String b = first.substring(n / 2, n);
		String c = second.substring(0, n / 2);
		String d = second.substring(n / 2, n);
		int decimaln = 2 * (n - n / 2);
		int decimalHalf = (n - n / 2);
		if (n == 2) {
			BigDecimal ac = new BigDecimal(a).multiply(new BigDecimal(c));
			BigDecimal bd = new BigDecimal(b).multiply(new BigDecimal(d));
			BigDecimal aPlusb = new BigDecimal(a).add(new BigDecimal(b));
			BigDecimal cPlusd = new BigDecimal(c).add(new BigDecimal(d));
			BigDecimal aPbMcPd = aPlusb.multiply(cPlusd);
			BigDecimal adPlusbc = aPbMcPd.subtract(ac).subtract(bd);
			BigDecimal mult = numWithDecs(ac, decimaln, adPlusbc, decimalHalf, bd);
			return mult;
		} else if (n > 2) {
			BigDecimal ac = calculateMult(a, c, n / 2);
			BigDecimal bd = calculateMult(b, d, (n - n / 2));
			BigDecimal aPlusb = new BigDecimal(a).add(new BigDecimal(b));
			BigDecimal cPlusd = new BigDecimal(c).add(new BigDecimal(d));
			String fst = aPlusb.toString();
			String snd = cPlusd.toString();
			n = Math.max(fst.length(), snd.length());
			fst = lpad(fst, n);
			snd = lpad(snd, n);
			BigDecimal aPbMcPd = calculateMult(fst, snd, n);
			BigDecimal adPlusbc = aPbMcPd.subtract(ac).subtract(bd);
			BigDecimal mult = numWithDecs(ac, decimaln, adPlusbc, decimalHalf, bd);
			return mult;
		}
		return null;
	}

	private static BigDecimal numWithDecs(BigDecimal ac, int decimaln, BigDecimal adPlusbc, int decimalHalf,
			BigDecimal bd) {
		BigDecimal sum = BigDecimal.ZERO;
		sum = sum.add(rpad(ac, decimaln));
		sum = sum.add(rpad(adPlusbc, decimalHalf));
		sum = sum.add(bd);
		return sum;
	}

	private static BigDecimal rpad(BigDecimal ac, int zeroNum) {
		String padded = "";
		while (zeroNum > 0) {
			padded = padded + "0";
			zeroNum--;
		}
		padded = ac.toString() + padded;
		return new BigDecimal(padded);
	}

	private static String lpad(String str, int n) {
		if (str.length() == n)
			return str;
		String padded = "";
		int zeroNum = n - str.length();
		while (zeroNum > 0) {
			padded = padded + "0";
			zeroNum--;
		}
		return padded + str;
	}
}
