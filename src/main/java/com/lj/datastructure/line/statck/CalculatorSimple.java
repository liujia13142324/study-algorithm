package com.lj.datastructure.line.statck;

import cn.hutool.core.lang.Assert;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

/**
 * 实现简易版的计算器 ， 加减乘除
 * 要实现负数，只能通过 String[] 数组的入参来实现了
 * 中缀计算
 * @author liujia
 */
public class CalculatorSimple {
    
    private Stack<Number> numbers;
    private Stack<Symbols> symbols;
    private Stack<String> polyStack;
    Pattern numCheck = Pattern.compile("^\\d*\\.?\\d+$");
    
    public CalculatorSimple() {
        numbers = new Stack<>(100);
        symbols = new Stack<>(100);
        polyStack = new Stack<>(100);
    }
    
    
    /**
     * 支持小数，中缀计算
     * @param expression
     * @return
     */
    public Value cal(String expression){
        StringBuffer num = new StringBuffer();
        List<Character> symbolChars = Symbols.getAllSymbols();
        
        for(Character c : expression.toCharArray()){
            if(!symbolChars.contains(c)){
                // 数字
                num.append(c);
            }else{
                // 符号
                pushData(num);
                Symbols s = Symbols.parse(c);
                Assert.notNull(s,"错误的符号->" + c);

//                if( s == Symbols.BRACES_LEFT ){
//                    this.symbols.push(s);
//                    continue;
//                }
    
//                if(s == Symbols.BRACES_RIGHT){
//                    while( symbols.peek() != Symbols.BRACES_LEFT ){
//                        cal();
//                    }
//                    // 左边括号pop出去
//                    symbols.pop();
//                }else{
                
//                    Symbols peek = symbols.peek();
//                    if(peek == null){
//                        this.symbols.push(s);
//                        continue;
//                    }
    
                    Symbols peek;
                    Symbols tmp = s;
                    while( (peek = symbols.peek()) != null && tmp.lt(peek) ){
                        cal();
                        // 左括号的玩法是真的骚，这么干应该就不用做别的额外的东西了
                        tmp = peek;
                    }
                    if(s != Symbols.BRACES_RIGHT){
                        symbols.push(s);
                    }
//                }
            }
        }
    
        pushData(num);
    
        while(!symbols.isEmpty()){
           cal();
        }
    
        return new Value(numbers.pop().doubleValue());
    }
    
    
    /**
     * TODO 使用List实现后缀表达式
     * @param expression
     * @return
     */
    public String suffix2(String expression){
        return null;
    }
    
    
    /**
     * 逆波兰表达式(后缀表达式)。中缀转后缀
     * @return
     */
    public String suffix(String expression){
        StringBuffer num = new StringBuffer();
        List<Character> symbolChars = Symbols.getAllSymbols();
    
        for(Character c : expression.toCharArray()){
            if(!symbolChars.contains(c)){
                // 数字
                num.append(c);
            }else{
                // 符号
                pushString(num);
                Symbols s = Symbols.parse(c);
                Assert.notNull(s,"错误的符号->" + c);
    
//                if( s == Symbols.BRACES_LEFT ){
//                    this.symbols.push(s);
//                    continue;
//                }

                /*Symbols peek = symbols.peek();
                if(peek == null){
                    this.symbols.push(s);
                    continue;
                }*/
    
                Symbols peek;
                Symbols tmp = s;
                while( (peek = symbols.peek()) != null && tmp.lt(peek) ){
                    concatExpression();
                    // 在遍历的时候就已经把左括号消除了，这么干应该就不用做单独的判断了
                    tmp = peek;
                }
                
                // 不是右括号，才能压栈
                if(s != Symbols.BRACES_RIGHT){
                    symbols.push(s);
                }
            }
        }
    
        pushString(num);
    
        while(!symbols.isEmpty()){
            concatExpression();
        }
    
        return polyStack.pop();
    }
    
    public Value calSuffixExpression(String expression){
    
        List<String> allSymbols = Symbols.getAllSymbolsStrings();
        String[] es = expression.split(",");
        for(String e:es){
            if(!allSymbols.contains(e)){
                numbers.push( Double.valueOf(e) );
            }else{
                // 符号
                Symbols symbols = Symbols.parse(e);
                Number n1 = numbers.pop();
                Number n2 = numbers.pop();
                numbers.push( symbols.calc(n1,n2) );
            }
        }
        
        return new Value(numbers.pop());
    }
    
    public void clear(){
        numbers.clear();
        symbols.clear();
    }

    private void cal() {
        Symbols pop = symbols.pop();
        if(pop == null){
            return;
        }

        if(pop.calcfunction!=null){
            Number n1 = numbers.pop();
            Number n2 = numbers.pop();
            numbers.push(pop.calc(n1,n2));
        }
    }
    
    private void concatExpression() {
        Symbols symbol = symbols.pop();
        if(symbol == null){
            return;
        }
        
        if(symbol.suffixFunction!=null){
            String e1 = polyStack.pop();
            String e2 = polyStack.pop();
            polyStack.push(symbol.concatExpression(e1,e2));
        }
    }
    
    private void pushData(StringBuffer num) {
        if(num.length()>0){
            Assert.isTrue(numCheck.matcher(num).find(),"错误的数字->" + num);
            numbers.push(Double.valueOf(num.toString()));
            num.delete(0,num.length());
        }
    }
    
    private void pushString(StringBuffer num) {
        if(num.length()>0){
            Assert.isTrue(numCheck.matcher(num).find(),"错误的数字->" + num);
            polyStack.push(num.toString());
            num.delete(0,num.length());
        }
    }
    
    @AllArgsConstructor
    class Value{
        Number v;
        public Double get(){
            return v.doubleValue();
        }
        public String format(int bit){
            return String.format("%."+bit+"f",v.doubleValue());
        }
    }
    
    /**
     * 可以扩展 小括号，等等
     */
    @AllArgsConstructor
    public enum Symbols{
        PLUS('+',0, (n1,n2)->n1.doubleValue()+n2.doubleValue(), (e1,e2)->e2+","+e1+",+")
        ,MINUS('-',0,(n1,n2)->n2.doubleValue()-n1.doubleValue(), (e1,e2)->e2+","+e1+",-")
        ,MULTI('*',1,(n1,n2)->n1.doubleValue()*n2.doubleValue(), (e1,e2)->e2+","+e1+",*")
        ,DIV('/',1,(n1,n2)->n2.doubleValue()/n1.doubleValue() , (e1,e2)-> e2+","+e1+",/")
        // 比任何符号的优先级都要高，直接压栈
        ,BRACES_LEFT('(',99,null,null)
        // 比任何符号的优先级都要低，直接出栈
        ,BRACES_RIGHT(')',-1,null,null)
        ;

        private Character symbol;
        private Integer weight;
        private BiFunction<Number,Number,Number> calcfunction;
        private BiFunction<String,String,String> suffixFunction;
        private static List<Character> allSymbols;
        private static List<String> allSymbolsStrings;
    
        public static List<Character> getAllSymbols(){
            if(allSymbols == null){
                Symbols[] values = values();
                allSymbols = new ArrayList<>(values.length);
                for(Symbols s : values){
                    allSymbols.add(s.symbol);
                }
            }
            return allSymbols;
        }
        public static List<String> getAllSymbolsStrings(){
            if(allSymbolsStrings == null){
                Symbols[] values = values();
                allSymbolsStrings = new ArrayList<>(values.length);
                for(Symbols s : values){
                    allSymbolsStrings.add(s.symbol+"");
                }
            }
            return allSymbolsStrings;
        }
        
        public static Symbols parse(char c){
            return parse(c + "");
        }
    
        public static Symbols parse(String c){
            for(Symbols s : values()){
                if((s.symbol+"").equals(c)){
                    return s;
                }
            }
            return null;
        }
        
        public boolean lt(Symbols one) {
            if(this.weight < one.weight){
                return true;
            }
            return false;
        }
    
        public Number calc(Number n1 , Number n2){
            return this.calcfunction.apply(n1,n2);
        }
    
        public String concatExpression(String e1 , String e2){
            return this.suffixFunction.apply(e1,e2);
        }
    }
    

    public static void main(String[] args) {
//        String express = "((10*(6/((9+3)*11)))+17)+5";
        String express = "1+((2+3)*4)-5".replace(" ","");
        
        CalculatorSimple calculatorSimple = new CalculatorSimple();
        System.out.println(express+"="+calculatorSimple.cal(express).get());
        System.out.println(express+"="+calculatorSimple.cal(express).format(1));
        
        String suffix = calculatorSimple.suffix(express);
        System.out.println(express+"->"+suffix);
        System.out.println("cal suffix->"+calculatorSimple.calSuffixExpression(suffix).get());
    
        System.out.println("1+1=" + calculatorSimple.cal("1+1").get());
    }
    
    @Test
    public void testSymbols(){
        Stack<Symbols> symbolsStack = new Stack<>(3);
        symbolsStack.push(Symbols.PLUS);
        symbolsStack.push(Symbols.PLUS);
        symbolsStack.show();
        
        System.out.println(numCheck.matcher("123..1").find());
        System.out.println(numCheck.matcher("123.1").find());
        System.out.println(numCheck.matcher("1231.").find());
        System.out.println(numCheck.matcher("121").find());
    }
    
    
}
