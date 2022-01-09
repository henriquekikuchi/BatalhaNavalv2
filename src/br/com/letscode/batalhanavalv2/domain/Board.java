package br.com.letscode.batalhanavalv2.domain;

public class Board {

    // marcações das coordenadas
    private final char POSICAO_VAZIA = ' ';
    private final char NAVIO_POSICIONADO = 'N';
    private final char TIRO_CERTEIRO = '*';
    private final char TIRO_NA_AGUA = '-';
    private final char TIRO_CERTEIRO_NAVIO_POSICIONADO = 'X';
    private final char TIRO_NA_AGUA_NAVIO_POSICIONADO = 'n';

    // configurações das embarcações
    private final int NUM_TOTAL_EMBARCACOES = 10;

    // configurações do tabuleiro
    private final int NUM_LINHAS = 10;
    private final int NUM_COLUNAS = 10;

    // atributos
    private char[][] posicoes;
    private int numeroDeAcertos;
    private int numeroDeEmbarcacoes;

    public Board(){
        posicoes = new char[NUM_LINHAS][NUM_COLUNAS];
        numeroDeAcertos = 0;
        numeroDeEmbarcacoes = NUM_TOTAL_EMBARCACOES;
        for(int linha = 0; linha < NUM_LINHAS; linha++){
            for(int coluna = 0; coluna < NUM_COLUNAS; coluna++ ){
                posicoes[linha][coluna] = POSICAO_VAZIA;
            }
        }
    }

    public int getNumTotalEmbarcacoes(){
        return NUM_TOTAL_EMBARCACOES;
    }

    public int getNumeroDeNaufragios(){
        return NUM_TOTAL_EMBARCACOES - numeroDeEmbarcacoes;
    }

    public int getNumeroDeAcertos(){
        return numeroDeAcertos;
    }

    public boolean posicionarNavio(String coordenadas){
        int[] posicao = getPosicao(coordenadas);
        if (posicao[0] > -1 && posicao[0] > -1){
            int linha = posicao[0];
            int coluna = posicao[1];
            char valorDaPosicao = this.posicoes[linha][coluna];
            if (valorDaPosicao == POSICAO_VAZIA){
                this.posicoes[linha][coluna] = NAVIO_POSICIONADO;
                return true;
            } else {
                System.out.println("Não foi possivel posicionar um navio nessa posicao, porque já tem um posicionado.");
                return false;
            }
        }
        return false;
    }

    public boolean posicionarNavio(int linha, int coluna){
        if (linha > -1 && coluna > -1){
            char valorDaPosicao = this.posicoes[linha][coluna];
            if (valorDaPosicao == POSICAO_VAZIA){
                this.posicoes[linha][coluna] = NAVIO_POSICIONADO;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void receberTiro(String coordenadas){
        int[] posicao = getPosicao(coordenadas);
        if (posicao[0] > -1 && posicao[0] > -1){
            int linha = posicao[0];
            int coluna = posicao[1];
            char valorDaPosicao = this.posicoes[linha][coluna];
            if (valorDaPosicao == NAVIO_POSICIONADO){
                this.posicoes[linha][coluna] = POSICAO_VAZIA;
                numeroDeEmbarcacoes--;
            } else if (valorDaPosicao == TIRO_CERTEIRO_NAVIO_POSICIONADO){
                this.posicoes[linha][coluna] = TIRO_CERTEIRO;
                numeroDeEmbarcacoes--;
            } else if (valorDaPosicao == TIRO_NA_AGUA_NAVIO_POSICIONADO){
                this.posicoes[linha][coluna] = TIRO_NA_AGUA;
                numeroDeEmbarcacoes--;
            }
        } else {
            System.out.println("Posicao invalida!");
        }
    }

    public void receberTiro(int linha, int coluna){
        if (linha > -1 && coluna > -1){
            char valorDaPosicao = this.posicoes[linha][coluna];
            if (valorDaPosicao == NAVIO_POSICIONADO){
                this.posicoes[linha][coluna] = POSICAO_VAZIA;
                numeroDeEmbarcacoes--;
            } else if (valorDaPosicao == TIRO_CERTEIRO_NAVIO_POSICIONADO){
                this.posicoes[linha][coluna] = TIRO_CERTEIRO;
                numeroDeEmbarcacoes--;
            } else if (valorDaPosicao == TIRO_NA_AGUA_NAVIO_POSICIONADO){
                this.posicoes[linha][coluna] = TIRO_NA_AGUA;
                numeroDeEmbarcacoes--;
            }
        } else {
            System.out.println("Posicao invalida!");
        }
    }

    public boolean dispararTiro(Board otherBoard, String coordenadas){
        int[] posicao = getPosicao(coordenadas);
        if (posicao[0] > -1 && posicao[0] > -1){
            int linha = posicao[0];
            int coluna = posicao[1];
            char valorDaPosicaoDoInimigo = otherBoard.posicoes[linha][coluna];
            char valorDaPosicao = this.posicoes[linha][coluna];

            if (valorDaPosicao != TIRO_CERTEIRO
                    && valorDaPosicao != TIRO_NA_AGUA
                    && valorDaPosicao != TIRO_CERTEIRO_NAVIO_POSICIONADO
                    && valorDaPosicao != TIRO_NA_AGUA_NAVIO_POSICIONADO
            ) {
                if (valorDaPosicaoDoInimigo == NAVIO_POSICIONADO
                        || valorDaPosicaoDoInimigo == TIRO_NA_AGUA_NAVIO_POSICIONADO
                        || valorDaPosicaoDoInimigo == TIRO_CERTEIRO_NAVIO_POSICIONADO
                ) {
                    otherBoard.receberTiro(coordenadas);
                    if (valorDaPosicao == NAVIO_POSICIONADO) this.posicoes[linha][coluna] = TIRO_CERTEIRO_NAVIO_POSICIONADO;
                    else if (valorDaPosicao == POSICAO_VAZIA) this.posicoes[linha][coluna] = TIRO_CERTEIRO;
                    numeroDeAcertos++;
                } else if (valorDaPosicaoDoInimigo == POSICAO_VAZIA
                        || valorDaPosicaoDoInimigo == TIRO_CERTEIRO
                        || valorDaPosicaoDoInimigo == TIRO_NA_AGUA
                ) {
                    if (valorDaPosicao == NAVIO_POSICIONADO) this.posicoes[linha][coluna] = TIRO_NA_AGUA_NAVIO_POSICIONADO;
                    else if (valorDaPosicao == POSICAO_VAZIA) this.posicoes[linha][coluna] = TIRO_NA_AGUA;
                }
                return true;
            } else {
                System.out.println("Você ja atirou nessa coordenada!");
                return false;
            }
        }
        System.out.println("Coordenada invalida!");
        return false;
    }

    public boolean dispararTiro(Board otherBoard, int linha, int coluna){
        if (linha > -1 && coluna > -1){
            char valorDaPosicaoDoInimigo = otherBoard.posicoes[linha][coluna];
            char valorDaPosicao = this.posicoes[linha][coluna];

            if (valorDaPosicao != TIRO_CERTEIRO
                    && valorDaPosicao != TIRO_NA_AGUA
                    && valorDaPosicao != TIRO_CERTEIRO_NAVIO_POSICIONADO
                    && valorDaPosicao != TIRO_NA_AGUA_NAVIO_POSICIONADO
            ) {
                if (valorDaPosicaoDoInimigo == NAVIO_POSICIONADO
                        || valorDaPosicaoDoInimigo == TIRO_NA_AGUA_NAVIO_POSICIONADO
                        || valorDaPosicaoDoInimigo == TIRO_CERTEIRO_NAVIO_POSICIONADO
                ) {
                    otherBoard.receberTiro(linha, coluna);
                    if (valorDaPosicao == NAVIO_POSICIONADO) this.posicoes[linha][coluna] = TIRO_CERTEIRO_NAVIO_POSICIONADO;
                    else if (valorDaPosicao == POSICAO_VAZIA) this.posicoes[linha][coluna] = TIRO_CERTEIRO;
                    numeroDeAcertos++;
                } else if (valorDaPosicaoDoInimigo == POSICAO_VAZIA
                        || valorDaPosicaoDoInimigo == TIRO_CERTEIRO
                        || valorDaPosicaoDoInimigo == TIRO_NA_AGUA
                ) {
                    if (valorDaPosicao == NAVIO_POSICIONADO) this.posicoes[linha][coluna] = TIRO_NA_AGUA_NAVIO_POSICIONADO;
                    else if (valorDaPosicao == POSICAO_VAZIA) this.posicoes[linha][coluna] = TIRO_NA_AGUA;
                }
                return true;
            } else {
                System.out.println("Você ja atirou nessa coordenada!");
                return false;
            }
        }
        System.out.println("Coordenada invalida!");
        return false;
    }

    public int[] getPosicao(String coordenadas){
        // espera-se que receba uma string no padrão "A1" sendo "A" a linha e "1" a coluna
        int linha = -1, coluna;

        if (coordenadas.length() == 2){
            char letraLinha = coordenadas.charAt(0);
            if (linhaEhValida(letraLinha)){
                char[] letras = "ABCDEFGHIJ".toCharArray();
                for (int i=0; i < letras.length; i++){
                    if (String.valueOf(letraLinha).equalsIgnoreCase(String.valueOf(letras[i]))){
                        linha = i;
                    }
                }
                int possivelColuna = Integer.parseInt(coordenadas.substring(1));
                if (possivelColuna > -1 && possivelColuna < NUM_COLUNAS) {
                    coluna = possivelColuna;
                    if ((linha > -1 && linha < NUM_LINHAS) && (coluna > -1 && coluna < NUM_COLUNAS)){
                        return new int[]{linha, coluna};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    private boolean linhaEhValida(char linhaLetra){
        char[] letras = "ABCDEFGHIJ".toCharArray();
        for(char letra : letras){
            if (String.valueOf(letra).equalsIgnoreCase(String.valueOf(linhaLetra))) {
                return String.valueOf(letra).equalsIgnoreCase(String.valueOf(linhaLetra));
            }
        }
        return false;
    }

    public void renderizar() {
        char[] letras = "ABCDEFGHIJ".toCharArray();
        System.out.println("---------------------------------------------");
        System.out.printf("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |%n");
        System.out.println("---------------------------------------------");
        for (int linha = 0; linha < NUM_LINHAS; linha++){
            System.out.printf("| %s ", letras[linha]);
            for (int coluna = 0; coluna < NUM_COLUNAS; coluna++){
                if (coluna == NUM_COLUNAS - 1) {
                    System.out.printf("| %s |%n", posicoes[linha][coluna]);
                } else {
                    System.out.printf("| %s ", posicoes[linha][coluna]);
                }
            }
            System.out.println("---------------------------------------------");
        }
    }
}
