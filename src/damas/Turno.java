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
public class Turno {

    public Jogador jb, jp;
    public static char currentTurn;
    public boolean isFirstClicked = true;
    public boolean movimentou = false;
    public static int umLinha, umColuna;

    public Turno(Jogador jb, Jogador jp, char currentTurn) {
        this.jb = jb;
        this.jp = jp;
        Turno.currentTurn = currentTurn;
    }

    int[] jogada(int linha, int coluna) {
        int[] ret = new int[3];
        try {
            if (currentTurn == 'b') {
                if (isFirstClicked) {
                    if (!jb.temPeca(linha, coluna)) {
                        throw new NullPointerException("num tem peça branco");
                    }
                    isFirstClicked = false;
                    umLinha = linha;
                    umColuna = coluna;
                    return null;
                } else {
                    isFirstClicked = true;
                    System.out.println(umLinha + " " + umColuna);
                    System.out.println(linha + " " + coluna);
                    //checar se é dama
                    if (!jb.getPeca(umLinha, umColuna).checarDama()) {
                        //existe peça no local?
                        if (jb.temPeca(linha, coluna)) {
                            throw new RuntimeException("Já tem peça no lugar branco");
                        }
                        if (linha == (umLinha - 1) && coluna == (umColuna - 1)) {
                            //tem peça na posição?
                            if (jp.temPeca(linha, coluna)) {
                                //se tiver peça atras
                                if (jp.temPeca(linha - 1, coluna - 1) || jb.temPeca(linha - 1, coluna - 1)) {
                                    throw new RuntimeException("Lugar inválido");
                                }
                                //come peça adversaria
                                jp.removePeca(linha, coluna);
                                linha--;
                                coluna--;
                            }
                            System.out.println("superior esquerdo");
                        } else if (linha == (umLinha - 1) && coluna == (umColuna + 1)) {
                            //tem peça na posição?
                            if (jp.temPeca(linha, coluna)) {
                                //se tiver peça atras
                                if (jp.temPeca(linha - 1, coluna + 1) || jb.temPeca(linha - 1, coluna + 1)) {
                                    throw new RuntimeException("Lugar inválido");
                                }
                                jp.removePeca(linha, coluna);
                                linha--;
                                coluna++;
                            }
                            System.out.println("superior direito");
                        } else {
                            throw new RuntimeException("lugar invalido 1");
                        }
                        ret[0] = linha;
                        ret[1] = coluna;
                        ret[2] = 2;
                        jb.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                        jb.getPeca(linha, coluna).makeDama(jb.finalLinha);
                        currentTurn = 'p';
                        //caso for dama
                    } else {
                        //existe peça no local?
                        if (jb.temPeca(linha, coluna)) {
                            throw new RuntimeException("Já tem peça no lugar branco");
                        }

                        int n = 1;

                        while (n < 9) {
                            if (linha == (umLinha + n) && coluna == (umColuna + n) && coluna != 8 && linha != 8) {
                                if (jp.temPeca(linha, coluna)) {
                                    if (jp.temPeca(linha + 1, coluna + 1) || jb.temPeca(linha + 1, coluna + 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jp.removePeca(linha, coluna);
                                        linha++;
                                        coluna++;
                                        break;
                                    }
                                }
                            } else if (linha == (umLinha - n) && coluna == (umColuna + n) && coluna != 8 && linha != 0) {
                                if (jp.temPeca(linha, coluna)) {
                                    if (jp.temPeca(linha - 1, coluna + 1) || jb.temPeca(linha - 1, coluna + 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jp.removePeca(linha, coluna);
                                        linha--;
                                        coluna++;
                                        break;
                                    }
                                }
                            } else if (linha == (umLinha - n) && coluna == (umColuna - n) && coluna != 0 && linha != 0) {
                                if (jp.temPeca(linha, coluna)) {
                                    if (jp.temPeca(linha - 1, coluna - 1) || jb.temPeca(linha - 1, coluna - 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jp.removePeca(linha, coluna);
                                        linha--;
                                        coluna--;
                                        break;
                                    }
                                }
                            } else if (linha == (umLinha + n) && coluna == (umColuna - n) && coluna != 0 && linha != 8) {
                                if (jp.temPeca(linha, coluna)) {
                                    if (jp.temPeca(linha + 1, coluna - 1) || jb.temPeca(linha + 1, coluna - 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jp.removePeca(linha, coluna);
                                        linha++;
                                        coluna--;
                                        break;
                                    }
                                }
                            }
                            n++;
                            System.out.println(n + " " + umLinha + " " + umColuna);
                            System.out.println(linha + " " + coluna);
                        }

                        ret[0] = linha;
                        ret[1] = coluna;
                        ret[2] = 2;

                        n = 1;

                        while (!movimentou) {
                            while (n < 9) {
                                if (linha == (umLinha + n) && coluna == (umColuna + n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jb.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'p';
                                    break;
                                } else if (linha == (umLinha - n) && coluna == (umColuna + n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jb.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'p';
                                    break;
                                } else if (linha == (umLinha - n) && coluna == (umColuna - n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jb.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'p';
                                    break;
                                } else if (linha == (umLinha + n) && coluna == (umColuna - n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jb.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'p';
                                    break;
                                }
                                n++;
                                System.out.println(n + " " + umLinha + " " + umColuna);
                                System.out.println(linha + " " + coluna);

                            }
                            if (!movimentou) {
                                System.out.println("Movimento invalido");
                                isFirstClicked = true;
                                break;
                            }
                        }

                        movimentou = false;

                    }
                }
                jb.jogada++;
            } else {
                if (isFirstClicked) {
                    if (!jp.temPeca(linha, coluna)) {
                        throw new NullPointerException("num tem peça");
                    }
                    isFirstClicked = false;
                    umLinha = linha;
                    umColuna = coluna;
                    return null;
                } else {
                    isFirstClicked = true;
                    System.out.println(umLinha + " " + umColuna);
                    //checar se é dama
                    if (!jp.getPeca(umLinha, umColuna).checarDama()) {
                        //existe peça no local?
                        if (jp.temPeca(linha, coluna)) {
                            throw new RuntimeException("Já tem peça no lugar");
                        }
                        if (linha == (umLinha + 1) && coluna == (umColuna - 1)) {
                            //tem peça na posição?
                            if (jb.temPeca(linha, coluna)) {
                                //se tiver peça atras
                                System.out.println("tem peca 1");
                                if (jp.temPeca(linha + 1, coluna - 1) || jb.temPeca(linha + 1, coluna - 1)) {
                                    throw new RuntimeException("Lugar inválido");
                                }

                                jb.removePeca(linha, coluna);

                                linha++;
                                coluna--;
                            }
                            System.out.println("inferior esquerdo");
                        } else if (linha == (umLinha + 1) && coluna == (umColuna + 1)) {
                            //tem peça na posição?
                            if (jb.temPeca(linha, coluna)) {
                                //se tiver peça atras
                                System.out.println("tem peca 1");
                                if (jp.temPeca(linha + 1, coluna + 1) || jb.temPeca(linha + 1, coluna + 1)) {
                                    throw new RuntimeException("Lugar inválido");
                                }

                                jb.removePeca(linha, coluna);
                                linha++;
                                coluna++;
                            }
                            System.out.println("inferior direito");
                        } else {
                            throw new RuntimeException("lugar invalido 6");
                        }
                        ret[0] = linha;
                        ret[1] = coluna;
                        ret[2] = 1;
                        jp.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                        jp.getPeca(linha, coluna).makeDama(jp.finalLinha);
                        currentTurn = 'b';

                        //caso for dama
                    } else {
                        //existe peça no local?
                        if (jp.temPeca(linha, coluna)) {
                            throw new RuntimeException("Já tem peça no lugar branco");
                        }

                        int n = 1;

                        while (n < 9) {
                            if (linha == (umLinha + n) && coluna == (umColuna + n) && coluna != 8 && linha != 8) {
                                if (jb.temPeca(linha, coluna)) {
                                    if (jb.temPeca(linha + 1, coluna + 1) || jp.temPeca(linha + 1, coluna + 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jb.removePeca(linha, coluna);
                                        linha++;
                                        coluna++;
                                        break;
                                    }
                                }
                            } else if (linha == (umLinha - n) && coluna == (umColuna + n) && coluna != 8 && linha != 0) {
                                if (jb.temPeca(linha, coluna)) {
                                    if (jb.temPeca(linha - 1, coluna + 1) || jp.temPeca(linha - 1, coluna + 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jb.removePeca(linha, coluna);
                                        linha--;
                                        coluna++;
                                        break;
                                    }
                                }
                            } else if (linha == (umLinha - n) && coluna == (umColuna - n) && coluna != 0 && linha != 0) {
                                if (jb.temPeca(linha, coluna)) {
                                    if (jp.temPeca(linha - 1, coluna - 1) || jb.temPeca(linha - 1, coluna - 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jb.removePeca(linha, coluna);
                                        linha--;
                                        coluna--;
                                        break;
                                    }
                                }
                            } else if (linha == (umLinha + n) && coluna == (umColuna - n) && coluna != 0 && linha != 8) {
                                if (jb.temPeca(linha, coluna)) {
                                    if (jb.temPeca(linha + 1, coluna - 1) || jb.temPeca(linha + 1, coluna - 1)) {
                                        System.out.println("Lugar invalido");
                                        isFirstClicked = true;
                                    } else {
                                        jb.removePeca(linha, coluna);
                                        linha++;
                                        coluna--;
                                        break;
                                    }
                                }
                            }
                            n++;
                            System.out.println(n + " " + umLinha + " " + umColuna);
                            System.out.println(linha + " " + coluna);
                        }

                        ret[0] = linha;
                        ret[1] = coluna;
                        ret[2] = 2;

                        n = 1;

                        while (!movimentou) {
                            while (n < 9) {
                                if (linha == (umLinha + n) && coluna == (umColuna + n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jp.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'b';
                                    break;
                                } else if (linha == (umLinha - n) && coluna == (umColuna + n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jp.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'b';
                                    break;
                                } else if (linha == (umLinha - n) && coluna == (umColuna - n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jp.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'b';
                                    break;
                                } else if (linha == (umLinha + n) && coluna == (umColuna - n) && !jp.temPeca(linha, coluna) && !jb.temPeca(linha, coluna)) {
                                    jp.getPeca(umLinha, umColuna).modPeca(linha, coluna);
                                    movimentou = true;
                                    System.out.println("inferior esquerdo");
                                    currentTurn = 'b';
                                    break;
                                }
                                n++;
                                System.out.println(n + " " + umLinha + " " + umColuna);
                                System.out.println(linha + " " + coluna);

                            }
                            if (!movimentou) {
                                System.out.println("Movimento invalido");
                                isFirstClicked = true;
                                break;
                            }
                        }
                        movimentou = false;

                    }
                }
            }
            jp.jogada++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (jb.jogadorPerdeu() || jp.jogadorPerdeu()) {
            ret[0] = 100;
            ret[1] = 100;
            ret[2] = 100;
        }

        return ret;
    }
}
