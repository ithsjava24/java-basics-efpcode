package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class PricesStats {
    public static double priceSum(App.Price[] prices){
        double priceSum = 0;
        for (App.Price price : prices){
            priceSum += (double) (price.value() * 1.0);
        }
        return priceSum;

    }
    public static double priceAvg(App.Price[] prices){
        double sumPrices = priceSum(prices);
        double denominator =(prices.length * 1.0d);
        return sumPrices/denominator;
    }
}

public static App.Price[] pricesSortAscending(App.Price[] prices){
    Arrays.sort(prices, Comparator.comparingInt(App.Price::value));
    return prices;
}

public static App.Price[] pricesSortDescending(App.Price[] prices){
    Arrays.sort(prices, Comparator.comparingInt(App.Price::value).reversed());
    return prices;
}

public static App.Price[] pricesSortOriginale(App.Price[] prices){
    Arrays.sort(prices, Comparator.comparingInt(App.Price::index));
    return prices;
}
