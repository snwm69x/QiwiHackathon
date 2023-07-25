package logic;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.HttpURLConnection;
    import java.net.URL;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.logging.Level;
    import java.util.logging.Logger;

public class APIClient {
    final static String apiUrl = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    private static final Logger logger = Logger.getLogger(APIClient.class.getName());

        public void requestToApi(String date) {
            try {
                URL url = new URL(apiUrl + date);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        String xmlFilePath = "src/main/java/temp/response.xml";
                        Files.write(Paths.get(xmlFilePath), response.toString().getBytes());
                    }
                } else {
                    logger.log(Level.SEVERE, "Error getting answer from API. CODE: " + responseCode);
                }
                connection.disconnect();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error with request to API", e);
            }
        }
}