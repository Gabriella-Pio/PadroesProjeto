/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;
import classededados.Aluno;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 *
 * @author eugeniojulio
 */
public class AlunoPersistencia {
    //Atributos
    private String nomeDoArquivo;
    //Metodos
    public AlunoPersistencia(String nome){
        nomeDoArquivo = nome;
    }
    private int quantidadeDeAlunosNoArquivo()throws Exception{
        try{
            int quantidade = 0;
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine())!=null){
                quantidade++;
            }
            br.close();
            return quantidade; 
        }catch(Exception erro){
            throw erro;
        } 
    }
    public Aluno[] listagemDeAlunosVetor()throws Exception{
        try{
            int quantidade = this.quantidadeDeAlunosNoArquivo();
            Aluno[] array = new Aluno[quantidade];
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            int pos = 0;
            while((linha=br.readLine())!=null){
                Aluno aux = new Aluno(linha);
                array[pos] = aux;
                pos++;
            }
            br.close();
            return array;
        }catch(Exception erro){
            throw erro;
        } 
    }
    public ArrayList<Aluno> listagemDeAlunosArrayList()throws Exception{
        try{
            ArrayList<Aluno> array = new ArrayList<Aluno>();
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            int pos = 0;
            while((linha=br.readLine())!=null){
                Aluno aux = new Aluno(linha);
                array.add(aux);
                pos++;
            }
            br.close();
            return array;
        }catch(Exception erro){
            throw erro;
        } 
    }
}
