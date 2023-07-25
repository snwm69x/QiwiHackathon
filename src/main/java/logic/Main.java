package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.Valute;

public class Main {
    public static void main(String[] args) {
        String code = "";
        String date = "";

        // Создаем объект класса Scanner для считывания значений из консоли
        Scanner scanner = new Scanner(System.in);

        // Считываем значения аргументов из консоли
        String input = scanner.nextLine();
        if (input.startsWith("currency_rates")) {
            String[] inputArgs = input.split(" ");
            for (String arg : inputArgs) {
                if (arg.startsWith("--code=")) {
                    code = arg.substring(7); // Extract value after "--code="
                } else if (arg.startsWith("--date=")) {
                    date = arg.substring(7); // Extract value after "--date="
                }
            }
        } else {
            System.out.println("Invalid input. Please enter a valid command.");
            return;
        }

        // If code or date is still empty, prompt user to enter them manually
        if (code.isEmpty() || date.isEmpty()) {
            // Считываем значения аргументов из консоли
            if (code.isEmpty()) {
                System.out.print("Enter code: ");
                code = scanner.nextLine();
            }
            if (date.isEmpty()) {
                System.out.print("Enter date (yyyy-MM-dd): ");
                date = scanner.nextLine();
            }
        }

        scanner.close();

        // Преобразуем дату в нужный формат
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = inputFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                return;
            }
        date = outputFormat.format(parsedDate);

        // Создаем объект класса APIClient
        APIClient apiClient = new APIClient();
        apiClient.requestToApi(date);

        // Создаем объект класса XMLParser
        XMLParser xmlParser = new XMLParser();
        List<Valute> ans = xmlParser.xmlToJavaObject("C:\\programming\\qiwi\\qiwihackathon\\src\\main\\java\\temp\\response.xml");

        // Создаем объект класса FindAndFormatCout
        FindAndFormatCout findAndFormatCout = new FindAndFormatCout();
        findAndFormatCout.answerToUser(code, ans);
        }
    }
