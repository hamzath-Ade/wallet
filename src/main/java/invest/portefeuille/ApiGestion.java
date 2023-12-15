package invest.portefeuille;


import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;

public class ApiGestion {

    String apiKey = "Sgmx2G8wNNutr00WSPa88Z4wzbNfGrxV4ueYmZOURZFEuIHBdk8M1Ec92LF4itmk"; //Api key
    String apiSecret = "167zQCKFy94t0IDMOxDEFXp5QpHtQeGDjXX5v5NENUPAZ240yTDS2yKhrXXeyk96"; //Secret key

    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apiKey, apiSecret);
    BinanceApiRestClient client = factory.newRestClient();
}
