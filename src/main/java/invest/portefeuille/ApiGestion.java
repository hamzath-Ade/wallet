package invest.portefeuille;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class ApiGestion {



        public static void main(String[] args) {
            try {


                String apiKey = "coinranking47b4fd6e60c7f67669e1e6a0eb59c257e12baf5e9bec70a3";
                URL url = new URL("https://api.coinranking.com/v2/coin/Qwsogvtv82FCd/history?apiKey=" + apiKey);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                JSONObject jsonObject = new JSONObject();
                while ((line = reader.readLine()) != null) {
                    response.append(line);

                }
                jsonObject = new JSONObject(response.toString());



                JSONObject data = jsonObject.getJSONObject("data");

                System.out.println(response.toString());


                    System.out.println(jsonObject);


                JSONArray historyArray = data.getJSONArray("history");
                //System.out.println(donneesConvertJson.get("status"));

           for (int i = 0; i < historyArray.length(); i++) {
                JSONObject historyObject = historyArray.getJSONObject(i);
                String price = historyObject.getString("price");
                long timestamp = historyObject.getLong("timestamp");
                System.out.println("Price: " + price + ", Timestamp: " + timestamp);
            }



            } catch (Exception e) {
                e.printStackTrace();
            }



        }

}
