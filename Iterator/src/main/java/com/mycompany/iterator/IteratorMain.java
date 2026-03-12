package com.mycompany.iterator;

import java.util.Iterator;

import Iterator.MatrizContainer;
import Model.Piloto;
import Repository.PilotosRepository;

public class IteratorMain {
    public static void main(String[] args) {
        try {
            // Ajuste o caminho para o seu arquivo CSV carregado
            PilotosRepository persistencia = new PilotosRepository("src/main/java/dados/DadosDosPilotosF1.csv");

            Iterator<Piloto> it = persistencia.getPilhaIterator();

            
            // MatrizContainer colecao = persistencia.carregarPilotos();

            // System.out.println("=== EXIBINDO PILOTOS (Via ArrayList Iterator) ===");
            // exibirGrid(colecao.getArrayListIterator());

            // System.out.println("\n=== EXIBINDO PILOTOS (Via HashSet Iterator - Ordem pode variar) ===");
            // exibirGrid(colecao.getHashSetIterator());

        } catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    // Método que garante a uniformidade: ele não sabe qual é a estrutura original,
    // apenas usa o Iterator
    public static void exibirGrid(Iterator<Piloto> it) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("NOME                 | EQUIPE          | PONTOS");
        System.out.println("-------------------------------------------------------------");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("-------------------------------------------------------------");
    }
}