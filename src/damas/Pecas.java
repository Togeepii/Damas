/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

/**
 *
 * @author tsuzukayama
 */
public class Pecas {

    Jogador jogador;
    boolean isDama = false;
    int linha, coluna;

    public Pecas(Jogador j, int linha, int coluna) {
        jogador = j;
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean checarDama() {
        return isDama;
    }

    public void makeDama(int finalLinha) {
        if (linha == finalLinha) {
            isDama = true;
            System.out.println("deu certo, virou dama");
        }
    }

    public void modPeca(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
}
