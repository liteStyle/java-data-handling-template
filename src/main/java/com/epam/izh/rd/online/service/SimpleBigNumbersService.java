package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        MathContext mathContext = new MathContext(range);
        BigDecimal bigDecimal = new BigDecimal(a).divide(new BigDecimal(b), mathContext);
        return bigDecimal;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int tmp = 0;
        int prime = 2;
        while (true) {
            BigInteger bigInteger = BigInteger.valueOf(prime);
            boolean probablePrime = bigInteger.isProbablePrime(prime);
            if(probablePrime == true) {
                tmp++;
                if(tmp == range + 1) {
                    break;
                }
            }
            prime++;
        }
        BigInteger result = BigInteger.valueOf(prime);

        return result;
    }
}
