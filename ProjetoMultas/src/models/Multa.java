package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma multa de trânsito.
 * Contém as informações sobre uma infração.
 */
public class Multa {

    // Contador para id com auto-incremento
    private static int contador = 1;

    private int id;
    private String origem;
    private String placa;
    private String tipoInfracao;
    private double valor;
    private LocalDate data;
    private String local;
    private boolean paga = false;

    /**
     * Construtor completo da Multa
     */
    public Multa(String placa, String tipoInfracao, double valor, LocalDate data, String local, String origem) {
        this.id = contador++;
        setPlaca(placa);
        setTipoInfracao(tipoInfracao);
        setValor(valor);
        setData(data);
        setLocal(local);
        setOrigem(origem);
    }

    // Setters
    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public void setTipoInfracao(String tipoInfracao) {
        this.tipoInfracao = tipoInfracao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    // Getters
    public String getPlaca() {
        return placa;
    }

    public String getTipoInfracao() {
        return tipoInfracao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public String getOrigem() {
        return origem;
    }

    /**
     * Método toString formatado para exibição
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Multa [Placa: %s | Infração: %s | Valor: R$ %.2f | Data: %s | Local: %s]",
                placa, tipoInfracao, valor, data.format(formatter), local);
    }
}
