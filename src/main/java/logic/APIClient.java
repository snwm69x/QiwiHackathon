package logic;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {
    final static String apiUrl = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    public void requestToApi(String date) {
        try {
            URL url = new URL(apiUrl + date);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String xmlFilePath = "src/main/java/temp/response.xml";
                FileWriter writer = new FileWriter(xmlFilePath);
                writer.write(response.toString());
                writer.close();

            } else {
                System.out.println("Ошибка получения ответа от API. Код ответа: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}