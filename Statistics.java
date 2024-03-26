
/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Curtis Edwards
* @version 1.0
* @since   2024-03-26
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] arrayOfIntegers) {
        double sum = 0;
        for (int i = 0; i < arrayOfIntegers.length; i++) {
            sum += arrayOfIntegers[i];
        }
        return sum / arrayOfIntegers.length;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] arrayOfIntegers) {
        double median = 0;
        Arrays.sort(arrayOfIntegers);
        if (arrayOfIntegers.length % 2 == 0) {
            median = (arrayOfIntegers[arrayOfIntegers.length / 2]
                      + arrayOfIntegers[arrayOfIntegers.length / 2 - 1]) / 2.0;
        } else {
            median = arrayOfIntegers[arrayOfIntegers.length / 2];
        }
        return median;
    }

    /**
    * The mode() function.
    *
    * @param numbers the collection of integers
    * @return the mode of the integers
    */
    public static List<Integer> mode(final Integer[] numbers) {
        final List<Integer> modes = new ArrayList<>();
        final Map<Integer, Integer> occurrences = new HashMap<>();
        int highestOccurrences = 0;

        for (int counter : numbers) {
            final int count = occurrences.getOrDefault(counter, 0) + 1;
            occurrences.put(counter, count);
            if (count > highestOccurrences) {
                highestOccurrences = count;
            }
        }

        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            if (entry.getValue() == highestOccurrences) {
                modes.add(entry.getKey());
            }
        }

        return modes;

    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        Integer tempNumber;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("./", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer[] arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        final double mean = mean(arrayOfNumbers);
        final double median = median(arrayOfNumbers);
        final List<Integer> mode = mode(arrayOfNumbers);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);
        System.out.println("The mode(s) is/are: "
                            + Arrays.toString(mode.toArray()));

        System.out.println("\nDone.");
    }
}
