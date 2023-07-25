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
            // Создаем объект URL на основе API URL
            URL url = new URL(apiUrl + date);
            
            // Открываем соединение с API URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Устанавливаем метод запроса на GET
            connection.setRequestMethod("GET");
            
            // Получаем ответ от API
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Создаем BufferedReader для чтения ответа
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Читаем ответ построчно и записываем его в StringBuffer
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Закрываем BufferedReader
                reader.close();

                // Записываем ответ XML в файл для дальнейшей обработки
                String xmlFilePath = "src/main/java/temp/response.xml";
                FileWriter writer = new FileWriter(xmlFilePath);
                writer.write(response.toString());
                writer.close();
                
                // System.out.println("Ответ API сохранен в файл " + xmlFilePath);
            } else {
                System.out.println("Ошибка получения ответа от API. Код ответа: " + responseCode);
            }
            
            // Закрываем соединение
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // XMLParser xmlParser = new XMLParser();
        // List<Valute> list = xmlParser.xmlToJavaObject("C:\\programming\\qiwi\\qiwihackathon\\src\\main\\java\\temp\\response.xml");
        // for(Valute valute : list){
        //     if(valute.getCharCode().equals("USD"))
        //         System.out.println(valute.getCharCode() + " " + valute.getValue());
        // }
    }
}