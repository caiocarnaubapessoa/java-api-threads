package meteo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClasseSemThreads {
    public void executar() throws Exception {
        try {
            List<String[]> coordenadas = lerCoordenadas();
            RequisicaoAPI requisicaoAPI = new RequisicaoAPI();
            for (String[] coordenada : coordenadas) {
                double latitude = Double.parseDouble(coordenada[1]);
                double longitude = Double.parseDouble(coordenada[2]);

                // Recebe os dados climáticos da API
                String dadosClimaticos = requisicaoAPI.getDadosClimaticos(latitude, longitude);

                // Processa os dados climáticos recebidos
                ProcessadorDadosClimaticos processadorDados = new ProcessadorDadosClimaticos(dadosClimaticos,coordenada[0]);
                processadorDados.imprimirEstatisticasDiarias();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String[]> lerCoordenadas() throws Exception {
        return Files.lines(Paths.get("C:\\Users\\c00104\\Desktop\\api-java\\meteo\\src\\main\\java\\meteo\\coordenadas.csv"))
                .skip(1) //pular o cabecalho
                .map(line -> line.split(","))
                .collect(Collectors.toList());
    }
}
