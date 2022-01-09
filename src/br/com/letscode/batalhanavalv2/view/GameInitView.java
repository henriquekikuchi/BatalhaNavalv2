package br.com.letscode.batalhanavalv2.view;

import br.com.letscode.batalhanavalv2.domain.Board;
import br.com.letscode.batalhanavalv2.domain.Player;

import java.util.Scanner;

public class GameInitView {

    public static String solicitarNome(){
        System.out.println("Digite seu nickname: ");
        System.out.printf("~#: ");
        String nome = new Scanner(System.in).nextLine();
        return nome;
    }

    public static Player criarPlayer(Board board){
        String nome = solicitarNome();
        return new Player(nome, board);
    }

    public static Player criarPlayerEConfigurarBoard(){
        Player player = criarPlayer(new Board());

        System.out.printf("Deseja posicionar os navios automaticamente? [S/N]%n");
        System.out.printf("~#: ");
        String resposta = new Scanner(System.in).next();
        if (resposta.equalsIgnoreCase("S")) player.posicionarNavios(true);
        else if (resposta.equalsIgnoreCase("N")) player.posicionarNavios(false);
        else criarPlayerEConfigurarBoard();

        return player;
    }

}
