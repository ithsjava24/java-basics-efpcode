package org.example;

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
    // placeholder for future implementation
}
