package org.example;

import org.example.config.Config;
import org.example.config.Range;
import org.example.utility.Utility;

import java.io.BufferedWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Subscription {

    public void generateSubscriptions(int numberOfSubscriptions, BufferedWriter writer) {


        Map<String, Set<Integer>> fieldIndices = new HashMap<>();

        for (Map.Entry<String, Double> entry : Config.FIELD_FREQUENCIES.entrySet()) {

            String field = entry.getKey();
            int count = (int) Math.round(entry.getValue() * numberOfSubscriptions);

            Set<Integer> indices = getRandomIndices(numberOfSubscriptions, count);
            fieldIndices.put(field, indices);
        }

        Map<String, Set<Integer>> equalityIndices = new HashMap<>();

        for (String field : Config.EQUALITY_OPERATOR_PERCENTAGES.keySet()) {

            Set<Integer> indices = fieldIndices.getOrDefault(field, new HashSet<>());
            int numEq = (int) Math.round(Config.EQUALITY_OPERATOR_PERCENTAGES.get(field) * indices.size());

            List<Integer> shuffled = new ArrayList<>(indices);
            Collections.shuffle(shuffled);
            equalityIndices.put(field, new HashSet<>(shuffled.subList(0, Math.min(numEq, shuffled.size()))));
        }


        for (int i = 0; i < numberOfSubscriptions; i++) {
            StringBuilder subscription = new StringBuilder();
            subscription.append("{");

            for (String field : Config.ORDERED_FIELDS) {
                if (fieldIndices.get(field).contains(i)) {

                    List<String> availableOperators = Config.AVAILABLE_OPERATORS.get(field);

                    String chosenOperator;

                    if (equalityIndices.containsKey(field) && equalityIndices.get(field).contains(i)) {
                        chosenOperator = "=";
                    } else {
                        List<String> filteredOperators = new ArrayList<>(availableOperators);
//                        filteredOperators.remove("="); // evităm să fie din greșeală tot "="
                        int randomIndex = ThreadLocalRandom.current().nextInt(filteredOperators.size());
                        chosenOperator = filteredOperators.get(randomIndex);
                    }


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


                    subscription.append("(").append(field).append(",").append(chosenOperator).append(",").append(chosenValue).append(");");
                }
            }


            if (subscription.charAt(subscription.length() - 1) == ';') {
                subscription.setLength(subscription.length() - 1);
            }
            subscription.append("}");


            Utility.writeToFile(subscription.toString(), writer);

        }

    }

    private static Set<Integer> getRandomIndices(int total, int count) {

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < total; i++) indices.add(i);

        Collections.shuffle(indices);

        return new HashSet<>(indices.subList(0, count));

    }

}


