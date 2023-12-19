package invest.portefeuille;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiGestion {

    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.coinranking.com/v2"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());
            double bitcoinPriceInUSD = jsonNode.get("bitcoin").get("usd").asDouble();

            System.out.println("1 Bitcoin price in USD: " + bitcoinPriceInUSD);
            System.out.println("2 Bitcoin price in USD: " + bitcoinPriceInUSD);
            double bitcoinPriceInUSD2 = jsonNode.get("bitcoin").get("usd").asDouble();
            System.out.println("3 Bitcoin price in USD: " + bitcoinPriceInUSD2);
            System.out.println("4 Bitcoin price in USD: " + bitcoinPriceInUSD2);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error while connecting to API: " + e.getMessage());
        }
    }

   // String apiKey = "Sgmx2G8wNNutr00WSPa88Z4wzbNfGrxV4ueYmZOURZFEuIHBdk8M1Ec92LF4itmk"; //Api key
   // String apiSecret = "167zQCKFy94t0IDMOxDEFXp5QpHtQeGDjXX5v5NENUPAZ240yTDS2yKhrXXeyk96"; //Secret key

   /* BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apiKey, apiSecret);
    BinanceApiRestClient client = factory.newRestClient();*/
}
