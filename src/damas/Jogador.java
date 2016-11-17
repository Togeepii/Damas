/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.ArrayList;

/**
 *
 * @author tsuzukayama
 */
public class Jogador {

    char corPeca;
    public int finalLinha;
    ArrayList<Pecas> pecas = new ArrayList<>();
    public int jogada=0;
    public Jogador(char corPeca, int finalLinha) {
        this.corPeca = corPeca;
        this.finalLinha = finalLinha;
    }

    public void addPeca(Pecas peca) {
        pecas.add(peca);
    }

    public void removePeca(int linha, int coluna) {
        try {
            for (Pecas peca : pecas) {
                if (peca.coluna == coluna && peca.linha == linha) {
                    pecas.remove(peca);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Pecas getPeca(int linha, int coluna) {
        for (Pecas peca : pecas) {
            if (peca.coluna == coluna && peca.linha == linha) {
                return peca;
            }
        }
        return null;
    }

    public boolean temPeca(int linha, int coluna) {
        for (Pecas peca : pecas) {
            if (peca.coluna == coluna && peca.linha == linha) {
                return true;
            }
        }
        return false;
    }

    public boolean jogadorPerdeu() {
        return this.pecas.size() <= 0;
    }
}
