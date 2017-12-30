import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author laerteguedes
 *         29/12/17
 */
public class SimuladorMegaSena {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite a quantidade de jogos a fazer: ");
        int qtdJogos = s.nextInt();

        System.out.println("Digite a quantidade de números em cada jogo: ");
        int qtdNumeros = s.nextInt();

        List<Integer> sorteadas = geraSurpresinha(6);
        System.out.println("Dezenas Sorteadas: ");
        sorteadas.forEach(sorteada -> System.out.print(sorteada + " "));
        System.out.println();

        processaJogos(sorteadas, qtdJogos, qtdNumeros);
    }

    private static void processaJogos(List<Integer> sorteadas, int qtdJogos, int qtdNumeros){
        int countQuadras = 0;
        int countQuinas = 0;
        int countSenas = 0;

        for (int i = 0; i < qtdJogos; i++) {
            int countAcertos = fazJogo(sorteadas, qtdNumeros);

            switch (countAcertos){
                case 4:
                    countQuadras++;
                    break;
                case 5:
                    countQuinas++;
                    break;
                case 6:
                    countSenas++;
                    break;
            }
        }

        System.out.println();
        System.out.println("Quantidade de jogos: "+qtdJogos);
        System.out.println("Quadras: "+countQuadras);
        System.out.println("Quinas: "+countQuinas);
        System.out.println("Senas: "+countSenas);
    }

    private static int fazJogo(List<Integer> sorteadas, int qtdNumeros){
        List<Integer> dezenas = geraSurpresinha(qtdNumeros);
        int countAcertos = dezenas.stream().filter(dezena -> sorteadas.contains(dezena)).collect(Collectors.toList()).size();

        System.out.print(countAcertos+" Acertos -> ");
        dezenas.forEach(d -> System.out.print(d+" "));
        System.out.println();

        return countAcertos;
    }

    private static List<Integer> geraSurpresinha(int qtdNumeros){
        Random random = new Random();
        List<Integer> dezenas = new ArrayList<>(qtdNumeros);

        int minNumero = 1;
        int maxNumero = 60;
        int numero = 0;

        while (dezenas.size() < qtdNumeros){
            numero = random.nextInt((maxNumero - minNumero) + 1) + minNumero;

            if (!dezenas.contains(numero))
                dezenas.add(numero);
        }

        return dezenas;
    }

}
