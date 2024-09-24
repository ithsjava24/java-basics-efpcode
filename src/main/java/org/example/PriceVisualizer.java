package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class PriceVisualizer {
    public static PriceDataPoint[] normalizeData(App.Price [] prices, int yAxisScale) {
        PriceDataPoint[] priceDataPoints = new PriceDataPoint[prices.length];
        int i = 0;
        App.Price [] miniAndMaxValue = PricesStats.pricesMinAndMax(prices);

        for (App.Price price : prices) {
            int rowIndexValue = scalarYAxis(
                    miniAndMaxValue[0].value(),
                    miniAndMaxValue[1].value()
                    ,yAxisScale,
                    price.value()
            );
            priceDataPoints[i] = PriceDataPoint.createDataplot(price,rowIndexValue);
            i++;

        }
        return priceDataPoints;



    }

    public static int scalarYAxis(int min, int max, int upperBound, int value){
//                (b-a)(x - min)
//        f(x) = --------------  + a
//                  max - min
        return (int) (((((value - min)* 1.0f)/((max - min))) * (upperBound-1)));
    }


    public record PriceDataPoint(int rowIndex, int columnIndex, int originalDataPoint) {
        public static PriceDataPoint createDataplot(App.Price price, int numberOfRows) {
            return new PriceDataPoint(numberOfRows, price.index(), price.value());
        }

        public static PriceDataPoint[] rowSortDescending(PriceDataPoint[] datapoints) {
            Arrays.sort(datapoints,
                    Comparator
                            .comparingInt(PriceDataPoint::rowIndex)
                            .thenComparing(PriceDataPoint::columnIndex)
                            .reversed());
            return datapoints;
        }


    }

    public static String[][] rowBuilder(PriceDataPoint[] priceDataPoints, int rowNumber, String filPattern, String defaultPattern) {
        String [][] matrix = new String[rowNumber][priceDataPoints.length];
        int lastIndex = rowNumber-1;
        for (String [] row : matrix){
            Arrays.fill(row,defaultPattern);
        }



        for (PriceDataPoint datapoint: priceDataPoints) {
            matrix[Math.abs(datapoint.rowIndex() - lastIndex)][datapoint.columnIndex()] = filPattern;
        }

        for (int j = 0, k=1; k <matrix.length ; j++, k++) {
            for (int l = 0; l < matrix[0].length ; l++) {
                if (!matrix[j][l].equals(matrix[k][l])){
                    matrix[k][l] = filPattern;
                }

            }

        }

        return matrix;

    }
    public static void PriceConsolePlotter(String[][] priceMatrix, App.Price[] priceStats, int lineWidth, int rightPadding, String sep){
        String[] firstAndLastRow = new String[2];
        String[] templateColumn = new String[24];
        StringBuilder bottomLine = new StringBuilder();
        StringBuilder numbers = new StringBuilder();

        for (int i = 0; i < firstAndLastRow.length; i++) {
            String value = priceStats[(Math.abs(i-1))].value() +"";
            String [] matrixValues = i != 0 ? priceMatrix[priceMatrix.length-1] : priceMatrix[0];
            String rightSide = value.length()%rightPadding == 0 ? value : " ".repeat((Math.abs(value.length()-rightPadding))) + value;
            firstAndLastRow[i] = String.format("%s %s%s\n",rightSide, sep,String.join("",matrixValues));

        }



        //System.out.printf("%s",firstAndLastRow[0]);
        for (int i = 0; i <priceMatrix.length; i++) {
            String row = String.format("    %s%s",sep,String.join("",priceMatrix[i]));
            if(i == 0){
                System.out.printf("%s",firstAndLastRow[0]);

            } else if (i == priceMatrix.length-1) {
                System.out.printf("%s",firstAndLastRow[1]);

            }else {
                System.out.printf("%s\n", row);
            }

        }
        for (int i = 0; i <templateColumn.length ; i++) {
            bottomLine.append("---");
            numbers.append(String.format("%02d ",i));

        }
        String renderLine = String.format("    %s%s",sep,bottomLine);
        String renderNumbers = String.format("    %s %s",sep,numbers);
        System.out.printf("%s\n",renderLine);
        System.out.printf("%s\n",renderNumbers.substring(0,lineWidth+1));

    }
}


