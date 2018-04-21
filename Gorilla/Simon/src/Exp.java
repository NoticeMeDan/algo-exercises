import edu.princeton.cs.algs4.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Exp {
	private static List<String> species;
	private static List<String> dnaStrings;
	private static SeqAnalyser seqAnalyser;

	public static void main(String[] args) {
		seqAnalyser = new SeqAnalyser();
		dnaStrings = new ArrayList<>();
		species = new ArrayList<>();

		final String regex = ">(?<species>[A-Za-z-]*)\\s[A-Za-z0-9 ]*\\n(?<dna>[A-Z\\n]+)";
		final String dnaContent = StdIn.readAll();

		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(dnaContent);		

		while (matcher.find()) {
		    species.add(matcher.group("species"));
		    dnaStrings.add(matcher.group("dna"));
		}

		StdOut.println(printResultTable());
	}

	public static String printResultTable() {
		int colLength = 15;
		int leftMargin = 2;
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n\n");
		for (int i = 0; i < colLength ; i++) sb.append(" ");

		for (int i = 0; i < species.size(); i++) {
			int marginLength = (colLength - species.get(i).length()) / 2;
			for (int u = 0; u < marginLength; u++) sb.append(" ");
			sb.append(species.get(i));
			for (int u = 0; u < marginLength; u++) sb.append(" ");
			if ((colLength - species.get(i).length()) % 2 != 0) sb.append(" ");
		}

		sb.append("\n\n");

		for (int i = 0; i < species.size(); i++) {
			for (int u = 0; u < leftMargin; u++) sb.append(" ");
			sb.append(species.get(i));
			for (int u = 0; u < (colLength - species.get(i).length() - leftMargin); u++) sb.append(" ");


			for (int v = 0; v < species.size(); v++) {
				String result = seqAnalyser.compareDNA(dnaStrings.get(i), dnaStrings.get(v));
				int marginLength = (colLength - result.length()) / 2;
				for (int u = 0; u < marginLength; u++) sb.append(" ");
				sb.append(result);
				for (int u = 0; u < marginLength; u++) sb.append(" ");
				if ((colLength - result.length()) % 2 != 0) sb.append(" ");
			}

			sb.append("\n\n");

		}

		return sb.toString();
	}
}