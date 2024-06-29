package meteo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClasseComMaisThreads {

    private int numeroDeThreads;
    private int capitaisPorThread;

    public ClasseComMaisThreads(int numeroDeThreads, int capitaisPorThread){
        this.numeroDeThreads = numeroDeThreads;
        this.capitaisPorThread = capitaisPorThread;
    }
    public void executar() throws Exception {
        List<String[]> coordenadas = lerCoordenadas();

        int numeroDeThreads = this.numeroDeThreads;
        int capitaisPorThread = this.capitaisPorThread;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numeroDeThreads; i++) {
            int inicio = i * capitaisPorThread;
            int fim = Math.min(inicio + capitaisPorThread, coordenadas.size());
            List<String[]> subLista = coordenadas.subList(inicio, fim);

            Thread thread = new Thread(new Worker(subLista));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    private List<String[]> lerCoordenadas() throws Exception {
        return Files.lines(Paths.get("C:\\Users\\c00104\\Desktop\\api-java\\meteo\\src\\main\\java\\meteo\\coordenadas.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .collect(Collectors.toList());
    }
}
