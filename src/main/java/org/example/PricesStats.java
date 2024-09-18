package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class PricesStats {
    public static double priceSum(App.Price[] prices) {
        double priceSum = 0;
        for (App.Price price : prices) {
            priceSum += (double) (price.value() * 1.0);
        }
        return priceSum;

    }

    public static double priceAvg(App.Price[] prices) {
        double sumPrices = priceSum(prices);
        double denominator = (prices.length * 1.0d);
        return sumPrices / denominator;
    }


    public static App.Price[] pricesSortAscending(App.Price[] prices) {
        Arrays.sort(prices, Comparator.comparingInt(App.Price::value));
        return prices;
    }

    public static App.Price[] pricesSortDescending(App.Price[] prices) {
        Arrays.sort(prices, Comparator.comparingInt(App.Price::value).reversed());
        return prices;
    }

    public static App.Price[] pricesSortOriginale(App.Price[] prices) {
        Arrays.sort(prices, Comparator.comparingInt(App.Price::index));
        return prices;
    }

    public static App.Price[] pricesMinAndMax(App.Price[] prices) {
        App.Price [] priceminiAndMax = new App.Price[2];
        App.Price [] priceSortedAscending = pricesSortAscending(prices);
        priceminiAndMax[0] = priceSortedAscending[0];
        priceminiAndMax[1] = priceSortedAscending[priceSortedAscending.length-1];
        return priceminiAndMax;
    }
    public static App.Price[] pricesInterval(App.Price[] prices, int step) {
        App.Price [] interval = new App.Price[4];
        double meanIntervalPrice = Double.MAX_VALUE;

        for (int i = 0; i <prices.length; i++, ++step) {
            if (step > prices.length-1){
                break;
            }
            App.Price [] temp = Arrays.copyOfRange(prices, i, step);
            double intervalMean = priceAvg(temp);
            if (intervalMean < meanIntervalPrice) {
                meanIntervalPrice = intervalMean;
                interval = temp.clone();
            }

        }
        return interval;

    }
}

