import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Kata {

    public static int add(String input) throws Exception {
        if (input.equals("")) {
            return 0;
        } else {
            List<Integer> numbers = splitNumbersIntoList(input);
            checkForNegatives(numbers);
            return numbers.stream().filter(i -> i <= 1000).reduce(0, (x, y) -> x + y);
        }
    }

    private static void checkForNegatives(List<Integer> numbers) throws Exception {
        if (numbers.stream().anyMatch(i -> i < 0)) {
            throw new Exception("Negatives: " + joinListNegativesIntoCommaDelimitedString(numbers));
        }
    }

    private static String joinListNegativesIntoCommaDelimitedString(List<Integer> numbers) {
        return numbers.stream().filter(i -> i < 0).map(String::valueOf).collect(Collectors.joining(","));
    }

    private static List<Integer> splitNumbersIntoList(String input) {
        if (input.startsWith("//[")) {
            String[] delimiters = input.substring(input.indexOf("[") + 1, input.lastIndexOf("]")).split("]\\[");
            String restOfNumbers = input.substring(input.indexOf("\n") + 1, input.length());
            return convertStringArrayToIntList(restOfNumbers.split(createRegexFromDelimiters(delimiters)));
        } else if (input.startsWith("//")) {
            String delimiter = input.substring(2, input.indexOf("\n"));
            String restOfNumbers = input.substring(input.indexOf("\n") + 1, input.length());
            return convertStringArrayToIntList(restOfNumbers.split(Pattern.quote(delimiter)));
        } else {
            return convertStringArrayToIntList(input.split("[,\n]"));
        }
    }

    private static List<Integer> convertStringArrayToIntList(String[] arr) {
        return Arrays.stream(arr).map(Integer::valueOf).collect(Collectors.toList());
    }

    private static String createRegexFromDelimiters(String[] delimiters) {
        return Arrays.stream(delimiters).map(Pattern::quote).collect(Collectors.joining("|"));
    }

}
