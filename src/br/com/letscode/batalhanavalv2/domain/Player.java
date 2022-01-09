package br.com.letscode.batalhanavalv2.domain;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private int score;
    private String nome;
    private Board board;

    public Player(String nome, Board board){
        this.nome = nome;
        this.board = board;
        this.score = 0;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void dispararTiro(Player otherPlayer, boolean automatico){
        boolean resposta = false;
        if (!automatico) {
            do {
                System.out.printf("%s, digite a coordenada que deseja atirar%n", getNome());
                System.out.printf("#~: ");
                String coordenadas = new Scanner(System.in).nextLine();
                resposta = this.board.dispararTiro(otherPlayer.board, coordenadas);
            } while (!resposta);
        } else {
            do {
                int linha = new Random().nextInt(10);
                int coluna = new Random().nextInt(10);
                resposta = this.board.dispararTiro(otherPlayer.board, linha, coluna);
            } while (!resposta);
        }
        score = board.getNumeroDeAcertos();
    }

    public void posicionarNavios(boolean automatico){
        int total = this.board.getNumTotalEmbarcacoes();
        for (int i = 0; i < total; i++){
            if (!automatico) {
                boolean resposta = false;
                do {
                    renderizar();
                    System.out.printf("Restam %d embarcações%n", total - i);
                    System.out.printf("%s, onde deseja posicionar sua embarcação?%n", getNome());
                    String coordenadas = new Scanner(System.in).nextLine();
                    resposta = board.posicionarNavio(coordenadas);
                } while (!resposta);
            } else {
                boolean resposta = false;
                do {
                    int linha = new Random().nextInt(10);
                    int coluna = new Random().nextInt(10);
                    resposta = board.posicionarNavio(linha, coluna);
                } while (!resposta);
            }
        }
        System.out.println("TODAS AS EMBARCAÇÕES FORAM POSICIONADAS!");
    }

    public void renderizar(){
        System.out.println("---------------------------------------------");
        System.out.printf("Nome: %s \t\t\t\t%n", getNome().toUpperCase());
        System.out.printf("Score: %d \t\t\t\t%n", getScore());
        System.out.printf("Naufragios: %d \t\t\t\t%n", board.getNumeroDeNaufragios());
        board.renderizar();
    }
}
