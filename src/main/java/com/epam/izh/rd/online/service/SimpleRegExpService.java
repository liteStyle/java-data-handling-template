package com.epam.izh.rd.online.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {

        String result = "";
        try (InputStream file = this.getClass().getResourceAsStream("/sensitive_data.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(file));) {
             result += reader.readLine();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("IO Exception");
        }

        Pattern pattern = Pattern.compile("\\d{4} (\\d{4} \\d{4}) \\d{4}");
        Matcher matcher = pattern.matcher(result);
        while(matcher.find()) {
            result = result.replace(matcher.group(1), "**** ****");
        }
        return result;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/sensitive_data.txt")))) {
            result = reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        Pattern pattern = Pattern.compile("\\$\\{[\\w]*\\}");
        Matcher matcher = pattern.matcher(result);
        matcher.find();
            result = result.replace(matcher.group(), String.valueOf((int)paymentAmount));
        matcher.find();
        result = result.replace(matcher.group(), String.valueOf((int)balance));

        return result;
    }
}
