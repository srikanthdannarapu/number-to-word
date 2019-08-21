package com.numbertoword;

import java.text.DecimalFormat;

import com.numbertoword.exception.InvalidNumberException;

/**
 * @author Srikanth Dannarapu
 *
 */
public class NumberToWords {

	private static final String[] tensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numberInWords = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	/**
	 * 
	 */
	private NumberToWords() {
	}

	/**
	 * @param number
	 * @return
	 */
	private static String converNumberstLessThanOneThousandToWord(int number) {
		String convertedNumberTillnow;

		if (number % 100 < 20) {
			convertedNumberTillnow = numberInWords[number % 100];
			number /= 100;
		} else {
			convertedNumberTillnow = numberInWords[number % 10];
			number /= 10;

			convertedNumberTillnow = tensNames[number % 10] + convertedNumberTillnow;
			number /= 10;
		}
		if (number == 0)
			return convertedNumberTillnow;
		return numberInWords[number] + " hundred" + convertedNumberTillnow;
	}

	/**
	 * @param number
	 * @return
	 */
	public static String convert(long number) {
		// 0 to 999 999 999
		if (number > 999999999) {
			throw new InvalidNumberException(100, "Number can't be greater than 999,999,999");
		}
		if (number == 0) {
			return "zero";
		}

		String snumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);
		// XXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(6, 9));

		String result = "";

		String millionNumInWords;
		switch (millions) {
		case 0:
			millionNumInWords = "";
			break;
		case 1:
			millionNumInWords = converNumberstLessThanOneThousandToWord(millions) + " million ";
			break;
		default:
			millionNumInWords = converNumberstLessThanOneThousandToWord(millions) + " million ";
		}
		result = result + millionNumInWords;

		String hundredThousandsInWords;
		switch (hundredThousands) {
		case 0:
			hundredThousandsInWords = "";
			break;
		case 1:
			hundredThousandsInWords = "one thousand ";
			break;
		default:
			hundredThousandsInWords = converNumberstLessThanOneThousandToWord(hundredThousands) + " thousand ";
		}
		result = result + hundredThousandsInWords;

		String tradThousand;
		tradThousand = converNumberstLessThanOneThousandToWord(thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}

	/**
	 * testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("*** " + NumberToWords.convert(721));
		/*
		 * System.out.println("*** " + NumberToWords.convert(1));
		 * System.out.println("*** " + NumberToWords.convert(16));
		 * System.out.println("*** " + NumberToWords.convert(100));
		 * System.out.println("*** " + NumberToWords.convert(118));
		 * System.out.println("*** " + NumberToWords.convert(200));
		 * System.out.println("*** " + NumberToWords.convert(219));
		 * System.out.println("*** " + NumberToWords.convert(800));
		 * System.out.println("*** " + NumberToWords.convert(801));
		 * System.out.println("*** " + NumberToWords.convert(1316));
		 * System.out.println("*** " + NumberToWords.convert(1000000));
		 * System.out.println("*** " + NumberToWords.convert(2000000));
		 * System.out.println("*** " + NumberToWords.convert(3000200));
		 * System.out.println("*** " + NumberToWords.convert(700000));
		 * System.out.println("*** " + NumberToWords.convert(9000000));
		 * System.out.println("*** " + NumberToWords.convert(9001000));
		 * System.out.println("*** " + NumberToWords.convert(56945781));
		 * System.out.println("*** " + NumberToWords.convert(123456789));
		 * System.out.println("*** " + NumberToWords.convert(2147483647));
		 * System.out.println("*** " + NumberToWords.convert(3000000010L));
		 */

	}
}