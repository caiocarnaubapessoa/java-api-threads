package meteo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequisicaoAPI {
    public String getDadosClimaticos(double latitude, double longitude) throws Exception {

        String url = "https://api.open-meteo.com/v1/forecast?latitude="+ latitude + "&longitude=" + longitude + "&hourly=temperature_2m&start_date=2024-04-01&end_date=2024-05-01&timezone=America/Sao_Paulo";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
