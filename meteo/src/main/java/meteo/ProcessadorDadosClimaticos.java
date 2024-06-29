/*package meteo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class ProcessadorDadosClimaticos {

    private String jsonString;
    private String capital;

    // Construtor que recebe o JSON dos dados climáticos
    public ProcessadorDadosClimaticos(String jsonString, String capital) {
        this.jsonString = jsonString;
        this.capital = capital;
    }

    // Método para processar os dados climáticos
    public List<Object[]> processarDados() {
        try {
            // Parse do JSON
            JSONObject jsonObj = new JSONObject(this.jsonString);
            JSONObject hourly = jsonObj.getJSONObject("hourly");

            // Extraindo os arrays de tempo e temperatura
            JSONArray tempos = hourly.getJSONArray("time");
            JSONArray temperaturas = hourly.getJSONArray("temperature_2m");

            // Map para agrupar as temperaturas por dia
            Map<String, List<Double>> agrupamentoPorDia = new HashMap<>();

            // Processando cada registro
            for (int i = 0; i < tempos.length(); i++) {
                String tempoCompleto = tempos.getString(i);
                double temperatura = temperaturas.getDouble(i);

                // Extraindo a data (dia) da string de tempo
                String dia = tempoCompleto.split("T")[0];

                // Agrupando as temperaturas pelo dia
                agrupamentoPorDia
                        .computeIfAbsent(dia, k -> new ArrayList<>())
                        .add(temperatura);
            }

            // Convertendo o map para uma lista de arrays [dia, [temperaturas]]
            List<Object[]> resultado = new ArrayList<>();
            for (Map.Entry<String, List<Double>> entrada : agrupamentoPorDia.entrySet()) {
                resultado.add(new Object[]{
                        entrada.getKey(), entrada.getValue()
                });
            }

            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Método para calcular a média, máxima e mínima das temperaturas diárias
    public void imprimirEstatisticasDiarias() {
        List<Object[]> dadosAgrupados = processarDados();

        System.out.println(this.capital);
        for (Object[] diaTemperaturas : dadosAgrupados) {

            String dia = (String) diaTemperaturas[0];
            List<Double> temperaturas = (List<Double>) diaTemperaturas[1];

            double max = Collections.max(temperaturas);
            double min = Collections.min(temperaturas);
            double media = temperaturas.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            System.out.printf("Dia: %s, Máxima: %.2f, Mínima: %.2f, Média: %.2f\n", dia, max, min, media);
        }
    }
}
*/

package meteo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class ProcessadorDadosClimaticos {

    private String jsonString;
    private String capital;

    // Construtor que recebe o JSON dos dados climáticos
    public ProcessadorDadosClimaticos(String jsonString, String capital) {
        this.jsonString = jsonString;
        this.capital = capital;
    }

    // Método para processar os dados climáticos
    public List<Object[]> processarDados() {
        try {
            // Parse do JSON
            JSONObject jsonObj = new JSONObject(this.jsonString);
            JSONObject hourly = jsonObj.getJSONObject("hourly");

            // Extraindo os arrays de tempo e temperatura
            JSONArray tempos = hourly.getJSONArray("time");
            JSONArray temperaturas = hourly.getJSONArray("temperature_2m");

            // Map para agrupar as temperaturas por dia
            Map<String, List<Double>> agrupamentoPorDia = new HashMap<>();

            // Processando cada registro
            for (int i = 0; i < tempos.length(); i++) {
                String tempoCompleto = tempos.getString(i);
                double temperatura = temperaturas.getDouble(i);

                // Extraindo a data (dia) da string de tempo
                String dia = tempoCompleto.split("T")[0];

                // Agrupando as temperaturas pelo dia
                agrupamentoPorDia
                        .computeIfAbsent(dia, k -> new ArrayList<>())
                        .add(temperatura);
            }

            // Ordenando o mapa por dia antes de converter para lista
            List<Object[]> resultado = new ArrayList<>();
            agrupamentoPorDia.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entrada -> resultado.add(new Object[]{entrada.getKey(), entrada.getValue()}));

            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Método para calcular a média, máxima e mínima das temperaturas diárias
    public void imprimirEstatisticasDiarias() {
        List<Object[]> dadosAgrupados = processarDados();

        System.out.println(this.capital);
        for (Object[] diaTemperaturas : dadosAgrupados) {

            String dia = (String) diaTemperaturas[0];
            List<Double> temperaturas = (List<Double>) diaTemperaturas[1];

            double max = Collections.max(temperaturas);
            double min = Collections.min(temperaturas);
            double media = temperaturas.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            System.out.printf("Dia: %s, Máxima: %.2f, Mínima: %.2f, Média: %.2f\n", dia, max, min, media);
        }
    }
}
