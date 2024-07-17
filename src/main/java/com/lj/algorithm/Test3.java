package com.lj.algorithm;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计算 A、B、C 产品分摊金额
 */
public class Test3 {
    
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>(
                Arrays.asList(
                        Product.builder().unitPrice(new BigDecimal("300")).quantity(20).build()
                        , Product.builder().unitPrice(new BigDecimal("400")).quantity(10).build()
                        , Product.builder().unitPrice(new BigDecimal("200")).quantity(10).build()
                )
        );
        
        new Test3().calcAmount(new BigDecimal("10000"), list);
    
        System.out.println(list);
    }
    
    /**
     * 计算摊销
     * @param totalAmount 订单的总价
     * @param list 订单的产品列表
     */
    public void calcAmount(BigDecimal totalAmount, List<Product> list) {
        // 每个产品的总价
        BigDecimal[] totalPrice = new BigDecimal[list.size()];
        // 产品的总价之和
        BigDecimal sum = new BigDecimal("0");
        for (int i = 0; i < list.size(); i++) {
            totalPrice[i] = list.get(i).getUnitPrice().multiply(new BigDecimal(list.get(i).getQuantity()));
            sum = sum.add(totalPrice[i]);
        }
    
        // 根据产品总价，设置每个产品的分摊：amount = totalAmount * (totalPrice[i] / sum)， 结果四舍五入
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setAmount( totalAmount.multiply(totalPrice[i]).divide(sum, 4, BigDecimal.ROUND_HALF_UP));
        }
    }
    
    
    @Data
    @Builder
    public static class Product{
        /**
         * 单价
         */
        private BigDecimal unitPrice;
        /**
         * 数量
         */
        private int quantity;
        /**
         * 分摊金额
         */
        private BigDecimal amount;
    }
    
    
  
    
}
