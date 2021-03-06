package br.com.letscode.batalhanavalv2.controller;

import br.com.letscode.batalhanavalv2.domain.Board;
import br.com.letscode.batalhanavalv2.domain.Player;
import br.com.letscode.batalhanavalv2.view.GameInitView;

import java.util.Scanner;

public class Game {

    public static void menuOptions(String opcao){

        switch (opcao){
            case "I":
                iniciarJogo();
                break;
            case "Q":
                encerrarJogo();
                break;
            default:
                printMenu();
        }

    }

    private static void iniciarJogo()  {
        String jogadorAtual = "Player";
        Player player = GameInitView.criarPlayerEConfigurarBoard();
        Player robo = new Player("robo", new Board());
        robo.posicionarNavios(true);

        int maiorScore = 0;
        String jogadorMaiorScore = "player";

        while (maiorScore < 10){
            player.renderizar();
            System.out.println();
            if (jogadorAtual.equalsIgnoreCase("player")) {
                player.dispararTiro(robo, false);
                jogadorAtual = "robo";
            } else {
                robo.dispararTiro(player, true);
                jogadorAtual = "player";
            }
            maiorScore = Math.max(player.getScore(), robo.getScore());
            jogadorMaiorScore = player.getScore() > robo.getScore() ? player.getNome() : robo.getNome();
        }
        System.out.println();
        System.out.printf("O vencedor da partida foi o %s!!!%n%n", jogadorMaiorScore);
        aguardarPressionarQualquerTecla();
        robo.renderizar();
        System.out.println();
        player.renderizar();

        aguardarPressionarQualquerTecla();
        new Scanner(System.in).next();
        printMenu();
    }

    private static void encerrarJogo() {
        System.out.println("Saindo do jogo!");
        System.exit(0);
    }

    public static void printMenu(){
        System.out.println("\t\t\t BATALHA NAVAL \t\t\t");
        System.out.println("[I] \t\t INICIAR JOGO \t\t");
        System.out.println("[Q] \t\t SAIR \t\t");
        System.out.println("Op????o escolhida: ");
        System.out.print("~#: ");
        String opcao = new Scanner(System.in).next().toUpperCase();
        menuOptions(opcao);
    }

    public static void aguardarPressionarQualquerTecla(){
        System.out.printf("Pressione qualquer tecla para continuar!%n");
        System.out.print("#~: ");
        new Scanner(System.in).nextLine();
    }
}
