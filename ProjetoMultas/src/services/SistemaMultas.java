package services;

import models.Multa;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton:
 * - Construtor privado
 * - Instância estática única
 * - Método getInstance() público
 */
public class SistemaMultas {
  // Instância única do Singleton
  private static SistemaMultas instancia = null;

  // Lista que armazena todas as multas cadastradas
  private List<Multa> multas;

  // Construtor privado
  private SistemaMultas() {
    this.multas = new ArrayList<>();
    System.out.println("Sistema de Multas inicializado (Singleton)");
    System.out.println("Instância única criada: " + this.hashCode());
  }

  /**
   * Método estático para obter a instância única do sistema.
   * Se não existir, cria. Se existir, retorna a mesma instância.
   * 
   * @return A instância única do SistemaMultas
   */
  public static SistemaMultas getInstance() {
    if (instancia == null) {
      instancia = new SistemaMultas();
    }
    return instancia;
  }

  /**
   * Valida se a placa está no formato: ABC1234
   * (3 letras + 4 números)
   * 
   * @param placa A placa a ser validada
   * @return true se a placa é válida, false caso contrário
   */
  public boolean validarPlaca(String placa) {
    if (placa == null || placa.trim().isEmpty()) {
      return false;
    }

    String placaLimpa = placa.trim().toUpperCase().replaceAll("[^A-Z0-9]", "");

    // Formato padrão: ABC1234 (3 letras + 4 números)
    return placaLimpa.matches("[A-Z]{3}[0-9]{4}");
  }

  /**
   * Adiciona uma nova multa ao sistema.
   * Valida a placa antes de adicionar.
   * 
   * @param multa A multa a ser adicionada
   * @throws IllegalArgumentException se a placa for inválida
   */
  public void adicionarMulta(Multa multa) {
    if (!validarPlaca(multa.getPlaca())) {
      throw new IllegalArgumentException("Placa inválida: " + multa.getPlaca() +
          ". Use formato ABC1234 (3 letras + 4 números)");
    }

    multas.add(multa);
    System.out.println("Multa cadastrada: " + multa.getPlaca());
  }

  /**
   * Retorna todas as multas cadastradas.
   * 
   * @return Lista com todas as multas
   */
  public List<Multa> listarTodas() {
    return new ArrayList<>(multas);
  }

  /**
   * Retorna o total de multas cadastradas
   * 
   * @return Número total de multas
   */
  public int getTotalMultas() {
    return multas.size();
  }

  /**
   * Calcula o valor total de todas as multas
   * 
   * @return Valor total em reais
   */
  public double getValorTotal() {
    return multas.stream()
        .mapToDouble(Multa::getValor)
        .sum();
  }

  /**
   * Limpa todas as multas (útil para testes)
   */
  public void limparTodas() {
    multas.clear();
    System.out.println("Todas as multas foram removidas");
  }
}
