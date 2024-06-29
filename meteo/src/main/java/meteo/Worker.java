package meteo;

import java.util.List;

public class Worker implements Runnable {
    private List<String[]> coordenadas;

    public Worker(List<String[]> coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public void run() {
        RequisicaoAPI requisicaoAPI = new RequisicaoAPI();
        for (String[] coordenada : coordenadas) {
            double latitude = Double.parseDouble(coordenada[1]);
            double longitude = Double.parseDouble(coordenada[2]);

            try {
                String dadosClimaticos = requisicaoAPI.getDadosClimaticos(latitude, longitude);
                ProcessadorDadosClimaticos processadorDados = new ProcessadorDadosClimaticos(dadosClimaticos,coordenada[0]);
                processadorDados.imprimirEstatisticasDiarias();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}