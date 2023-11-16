import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciar();
    }
}
class Jogo {
    private Baralho baralho;
    private Jogador jogador;
    private Jogador dealer;
    private Scanner scanner;
    public Jogo() {
        baralho = new Baralho();
        jogador = new Jogador("Jogador");
        dealer = new Jogador("Dealer");
        scanner = new Scanner(System.in);
    }
    public void iniciar() {
        String jogarNovamente;
        do {
            jogador.limparMao();
        dealer.limparMao();
        baralho.embaralhar();
        jogador.adicionarCarta(baralho.pegarCarta());
        dealer.adicionarCarta(baralho.pegarCarta());
        jogador.adicionarCarta(baralho.pegarCarta());
        dealer.adicionarCarta(baralho.pegarCarta());
        if (jogador.calcularPontuacao() == 21 && dealer.calcularPontuacao() != 21) {
            System.out.println("Blackjack! Jogador ganha!");
            return;
        } else if (dealer.calcularPontuacao() == 21 && jogador.calcularPontuacao() != 21) {
            System.out.println("Blackjack! Dealer ganha!");
            return;
        }
        boolean jogadorFicou = false;
        boolean dealerFicou = false;
        while (true) {
            System.out.println("Jogador: " + jogador.calcularPontuacao() + " Dealer: " + dealer.calcularPontuacao());
            if (!jogadorFicou) {
                System.out.println("Você quer pegar outra carta? (s/n)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    jogador.adicionarCarta(baralho.pegarCarta());
                    if (jogador.calcularPontuacao() > 21) {
                        break;
                    }
                } else {
                    jogadorFicou = true;
                }
            }
            if (!dealerFicou) {
                if (dealer.calcularPontuacao() <= 17) {
                    dealer.adicionarCarta(baralho.pegarCarta());
                    if (dealer.calcularPontuacao() > 21) {
                        break;
                    }
                } else {
                    dealerFicou = true;
                }
            }
            if (jogadorFicou && dealerFicou) {
                break;
            }
        }
        if (jogador.calcularPontuacao() > 21) {
            System.out.println("Dealer ganha!");
        } else if (dealer.calcularPontuacao() > 21) {
            System.out.println("Jogador ganha!");
        } else if (jogador.calcularPontuacao() > dealer.calcularPontuacao()) {
            System.out.println("Jogador ganha!");
        } else if (dealer.calcularPontuacao() > jogador.calcularPontuacao()) {
            System.out.println("Dealer ganha!");
        } else {
            System.out.println("Empate!");
        }
    
     System.out.println("Gostaria de jogar novamente? (s/n)");
        jogarNovamente = scanner.nextLine();
    } while (jogarNovamente.equalsIgnoreCase("s"));
}
}
class Jogador {
    public void limparMao() {
    mao.clear();
}
    private String nome;
    private ArrayList<Carta> mao;
    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<Carta>();
    }
    public void adicionarCarta(Carta carta) {
        mao.add(carta);
    }
    public int calcularPontuacao() {
        int pontuacao = 0;
        for (Carta carta : mao) {
            pontuacao += carta.getValor();
        }
        return pontuacao;
    }
    public String getNome() {
        return nome;
    }
    public ArrayList<Carta> getMao() {
        return mao;
    }
}
class Baralho {
    private ArrayList<Carta> cartas;
    Baralho() {
        cartas = new ArrayList<Carta>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                cartas.add(new Carta(i, j));
            }
        }
    }
    public void embaralhar() {
        Collections.shuffle(cartas);
    }
Carta pegarCarta() {
        return cartas.remove(cartas.size() - 1);
    }
}
 class Carta {
    private int valor;
    private int naipe;
    public Carta(int valor, int naipe) {
        if (valor > 10) {
            this.valor = 10; // Valete, Dama e Rei valem 10
        } else if (valor == 1) {
            this.valor = 11; // Ás vale 11 (você pode ajustar isso mais tarde se necessário)
        } else {
            this.valor = valor;
        }
        this.naipe = naipe;
    }
    public int getValor() {
        return valor;
    }
    public int getNaipe() {
        return naipe;
    }
}