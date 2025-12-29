import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    
    public static String getWeather() {
        try {
            String apiURL = "https://api.data.gov.my/weather/forecast/?contains=WP%20Kuala%20Lumpur@location__location_name&sort=date&limit=1";
            
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() != 200) {
                return "Weather unavailable";
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            conn.disconnect();
            
            // Parse the JSON response to extract weather info
            String response = sb.toString();
            
            // Simple parsing (you can improve this)
            String forecast = extractValue(response, "summary_forecast");
            String minTemp = extractValue(response, "min_temp");
            String maxTemp = extractValue(response, "max_temp");
            
            return forecast + " (" + minTemp + "°C - " + maxTemp + "°C)";
            
        } catch (Exception e) {
            System.out.println("Error fetching weather: " + e.getMessage());
            return "Weather unavailable";
        }
    }
    
    // Helper method to extract values from JSON
    private static String extractValue(String json, String key) {
        try {
            int startIndex = json.indexOf("\"" + key + "\":");
            if (startIndex == -1) return "";
            
            startIndex = json.indexOf(":", startIndex) + 1;
            
            // Skip whitespace
            while (startIndex < json.length() && json.charAt(startIndex) == ' ') {
                startIndex++;
            }
            
            // Check if value is a string (starts with ")
            if (json.charAt(startIndex) == '"') {
                startIndex++; // Skip opening quote
                int endIndex = json.indexOf("\"", startIndex);
                return json.substring(startIndex, endIndex);
            } else {
                // It's a number
                int endIndex = startIndex;
                while (endIndex < json.length() && 
                       (Character.isDigit(json.charAt(endIndex)) || json.charAt(endIndex) == '.')) {
                    endIndex++;
                }
                return json.substring(startIndex, endIndex);
            }
        } catch (Exception e) {
            return "";
        }
    }
}