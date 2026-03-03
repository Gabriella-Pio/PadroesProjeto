/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classededados;

/**
 *
 * @author eugeniojulio
 */
public class Aluno {
    //Atributos
    private String matricula;
    private String nome;
    //Metodos
    public Aluno(String matricula, String nome){
        this.matricula = matricula;
        this.nome = nome;
    }
    public Aluno(String str){
        String[] vetor = str.split(";");
        this.matricula = vetor[0];
        this.nome = vetor[1];
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
