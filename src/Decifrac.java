import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Decifrac {
	public Set<Long> factor(long n) {
		Set<Long> factors = new HashSet<Long>();
		for (long i = 2; i < n + 1; i++) {
			if (n % i == 0) {
				factors.add(i);
			}
		}
		return factors;
	}

	public void init() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Decimal:");
		String numstr = sc.next();
		double num = Double.parseDouble(numstr);
		int acc = numstr.substring(numstr.indexOf('.')).length() - 1;
		System.out.print("Enter max denominator size:");
		int dlen = sc.nextInt();
		System.out.print("Enter 1 for flooring mode and 2 for rounding mode:");
		int m = sc.nextInt();
		boolean flag = false;
		long numlim = 0;
		long numval = 0;
		long denval = 1;
		long target = (long) Math.round(num * Math.pow(10, acc));

		while (denval < Math.pow(10, dlen) + 1) {
			numval = numlim;
			while (true) {
				BigDecimal numvalbd = new BigDecimal(numval);
				BigDecimal denvalbd = new BigDecimal(denval);
				long attempt = ((m == 1)
						? numvalbd.divide(denvalbd, 16, RoundingMode.FLOOR).multiply(new BigDecimal(Math.pow(10, acc))).longValue()
						: numvalbd.divide(denvalbd, 16, RoundingMode.HALF_EVEN).multiply(new BigDecimal(Math.pow(10, acc))).longValue());
				if (attempt < target) {
					numlim++;
					numval++;
				} else if (attempt == target) {
					if (Collections.disjoint(factor(numval), factor(denval))) {
						System.out.println(numval + "/" + denval + "=" + numvalbd.divide(denvalbd, 16, RoundingMode.FLOOR));
					}
					if (numlim == numval) {
						numlim--;
					}
					numval++;
					flag = true;
				} else {
					denval++;
					break;
				}
			}
		}
		if (!flag) {
			System.out.println("No solutions try a higher denominator size or mode");
		}
		/*
		 * for (int i = 1; i < Math.pow(10, dlen) + 1; i++) { for (int j = (int)
		 * ((Math.ceil(num) - 1) * i); j < (Math.ceil(num) * (i)) + 1; j++) { boolean p
		 * = false; if ((m == 1 && (Math.floor(((double) j / i) * (Math.pow(10, acc)))
		 * == target)) || (m == 2 && (Math.round(((double) j / i) * (Math.pow(10, acc)))
		 * == target))) { p = true; }
		 * 
		 * 
		 * if (num.multiply(new BigDecimal(Math.pow(10, acc))).setScale(0,
		 * RoundingMode.HALF_UP) .compareTo(new BigDecimal(j / i).multiply(new
		 * BigDecimal(Math.pow(10, acc))).setScale(0, RoundingMode.HALF_UP)) == 0) { p =
		 * true; }
		 * 
		 * if (p) { if (Collections.disjoint(factor(j), factor(i))) {
		 * System.out.println(j + "/" + i + "=" + (double) j / i); } } flag = flag || p;
		 * }}
		 */

		System.out.print("Press enter to close");
		sc.nextLine();
		sc.nextLine();
		sc.close();
	}

	public static void main(String[] args) {
		Decifrac df = new Decifrac();
		df.init();
	}

}
