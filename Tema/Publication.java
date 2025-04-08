package org.example;

import org.example.config.Config;
import org.example.config.Range;
import org.example.utility.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Publication {

    public void generatePublications(int numberOfPublications, BufferedWriter writer) {

        for (int i = 0; i < numberOfPublications; i++) {

            StringBuilder publication = new StringBuilder();
            publication.append("{");

            for (String field : Config.ORDERED_FIELDS) {

                String chosenValue;

                if(field.equals("stationid") || field.equals("city") || field.equals("direction") || field.equals("date")) {

                    List<String> fixedValues = Config.FIXED_VALUES.get(field);

                    int randomIndex = ThreadLocalRandom.current().nextInt(fixedValues.size());
                    String randomValue = fixedValues.get(randomIndex);

                    if (field.equals("city") || field.equals("direction")) {
                        chosenValue = "\"" + randomValue + "\"";

                    } else {
                        chosenValue = randomValue;
                    }

                } else {

                    Range<Double> range = Config.RANGE_VALUES.get(field);
                    double randomRangeValue = ThreadLocalRandom.current().nextDouble(range.min, range.max);

                    String formattedRangeValue = field.equals("temp") || field.equals("wind")
                            ? String.valueOf((int) Math.round(randomRangeValue))
                            : String.format(Locale.US, "%.1f", randomRangeValue);

                    chosenValue = formattedRangeValue;

                }

                publication.append("(").append(field).append(",").append(chosenValue).append(");");


            }

            if (publication.charAt(publication.length() - 1) == ';') {
                publication.setLength(publication.length() - 1);
            }
            publication.append("}");



            Utility.writeToFile(publication.toString(), writer);


        }

    }
}
