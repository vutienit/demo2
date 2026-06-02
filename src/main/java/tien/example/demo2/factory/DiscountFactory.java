package tien.example.demo2.factory;

import tien.example.demo2.strategy.DiscountStrategy;
import tien.example.demo2.strategy.impl.FlashsaleDiscountServiceImpl;
import tien.example.demo2.strategy.impl.WholesaleDiscountServiceImpl;

public class DiscountFactory {
    public DiscountStrategy getDiscountStrategy(String type){
        switch(type){
            case "Wholesale": return new WholesaleDiscountServiceImpl();
            case "Flashsale": return new FlashsaleDiscountServiceImpl();
            default: return null;
        }
    }
}
