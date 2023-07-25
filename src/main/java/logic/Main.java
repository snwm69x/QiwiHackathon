package logic;

import java.io.File;
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
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.startsWith("currency_rates")) {
            String[] inputArgs = input.split(" ");
            for (String arg : inputArgs) {
                if (arg.startsWith("--code=")) {
                    code = arg.substring(7);
                } else if (arg.startsWith("--date=")) {
                    date = arg.substring(7);
                }
            }
        } else {
            System.out.println("Invalid input. Please enter a valid command.");
            scanner.close();
            return;
        }

        if (code.isEmpty() || date.isEmpty()) {
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

        // delete file if exists to avoid answer from previous request
        File file = new File("C:\\programming\\qiwi\\qiwihackathon\\src\\main\\java\\temp\\response.xml");
        if (file.exists()) {
            file.delete();
        }

        APIClient apiClient = new APIClient();
        apiClient.requestToApi(date);
        
        XMLParser xmlParser = new XMLParser();
        List<Valute> ans = xmlParser.xmlToJavaObject("C:\\programming\\qiwi\\qiwihackathon\\src\\main\\java\\temp\\response.xml");

        FindAndFormatCout findAndFormatCout = new FindAndFormatCout();
        findAndFormatCout.answerToUser(code, ans);
        }
    }
