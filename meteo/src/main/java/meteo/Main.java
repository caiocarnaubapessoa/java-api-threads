package meteo;

public class Main {
    public static void main(String[] args) {

        // Instâncias dos aplicativos
        ClasseSemThreads app1 = new ClasseSemThreads();
        ClasseComMaisThreads app3 = new ClasseComMaisThreads(3, 9);
        ClasseComMaisThreads app9 = new ClasseComMaisThreads(9, 3);
        ClasseComMaisThreads app27 = new ClasseComMaisThreads(27, 1);

        // Medição do tempo de execução para cada aplicativo
        try {

            // Medição para app1
            long startTimeApp1 = System.nanoTime();
            app1.executar();
            long endTimeApp1 = System.nanoTime();
            long durationApp1 = endTimeApp1 - startTimeApp1;
            System.out.println("Tempo de execução da (ClasseSemThreads): " + durationApp1 + " nanosegundos");

            // Medição para app3
            long startTimeApp3 = System.nanoTime();
            app3.executar();
            long endTimeApp3 = System.nanoTime();
            long durationApp3 = endTimeApp3 - startTimeApp3;
            System.out.println("Tempo de execução da (ClasseComMaisThreads - 3 threads): " + durationApp3 + " nanosegundos");

            // Medição para app9
            long startTimeApp9 = System.nanoTime();
            app9.executar();
            long endTimeApp9 = System.nanoTime();
            long durationApp9 = endTimeApp9 - startTimeApp9;
            System.out.println("Tempo de execução da (ClasseComMaisThreads - 9 threads): " + durationApp9 + " nanosegundos");

            // Medição para app27
            long startTimeApp27 = System.nanoTime();
            app27.executar();
            long endTimeApp27 = System.nanoTime();
            long durationApp27 = endTimeApp27 - startTimeApp27;
            System.out.println("Tempo de execução da (ClasseComMaisThreads - 27 threads): " + durationApp27 + " nanosegundos");

            System.out.println('\n');
            System.out.println(" ---------- Relatório geral das Threads ------------");
            System.out.println("Tempo de execução da (ClasseSemThreads): " + durationApp1 + " nanosegundos");
            System.out.println("Tempo de execução da (ClasseComMaisThreads - 3 threads - 9 capitais): " + durationApp3 + " nanosegundos");
            System.out.println("Tempo de execução da (ClasseComMaisThreads - 9 threads - 3 capitais ): " + durationApp9 + " nanosegundos");
            System.out.println("Tempo de execução da (ClasseComMaisThreads - 27 threads - 1 capital): " + durationApp27 + " nanosegundos");
            System.out.println(" ---------- Relatório geral das Threads ------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
