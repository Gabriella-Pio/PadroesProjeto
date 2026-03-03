package tests;

import models.Multa;
import services.SistemaMultas;
import java.time.LocalDate;

public class SistemaMultasTest {

  public static void main(String[] args) {
    System.out.println("=".repeat(70));
    System.out.println("Testes - Padrão Singleton");
    System.out.println("=".repeat(70));
    System.out.println();

    int totalTestes = 0;
    int testesPassados = 0;

    System.out.println("Teste 1: Verifica Instância Única");
    totalTestes++;
    SistemaMultas s1 = SistemaMultas.getInstance();
    SistemaMultas s2 = SistemaMultas.getInstance();
    SistemaMultas s3 = SistemaMultas.getInstance();

    if (s1 == s2 && s2 == s3 && s1.hashCode() == s2.hashCode()) {
      System.out.println("Todas as referências apontam para a mesma instância");
      System.out.println("  Hash s1: " + s1.hashCode());
      System.out.println("  Hash s2: " + s2.hashCode());
      System.out.println("  Hash s3: " + s3.hashCode());
      testesPassados++;
    } else {
      System.out.println("Erro: Instâncias diferentes detectadas.");
    }
    System.out.println();

    // Limpa o sistema para testes isolados
    s1.limparTodas();


    System.out.println("Teste 2: Validar placas no formato ABC1234");
    totalTestes++;
    if (s1.validarPlaca("ABC1234") && s1.validarPlaca("XYZ9876")) {
      System.out.println("Placas válidas");
      testesPassados++;
    } else {
      System.out.println("Erro: Validação de placa incorreta");
    }
    System.out.println();

    
    
    System.out.println("Teste 3: Rejeitar placas inválidas");
    totalTestes++;
    if (!s1.validarPlaca("ABC") && !s1.validarPlaca("1234567") &&
        !s1.validarPlaca("") && !s1.validarPlaca(null)) {
      System.out.println("Placas inválidas");
      testesPassados++;
    } else {
      System.out.println("Erro: Placas inválidas foram aceitas");
    }
    System.out.println();

    
    System.out.println("Teste 4: Adicionar multa com placa válida");
    totalTestes++;
    try {
      Multa m1 = new Multa("ABC1234", "Excesso de Velocidade", 195.23,
          LocalDate.now(), "Av. Paulista", false);
      s1.adicionarMulta(m1);

      if (s1.getTotalMultas() == 1) {
        System.out.println("Multa adicionada com sucesso");
        testesPassados++;
      } else {
        System.out.println("Erro: Total de multas incorreto");
      }
    } catch (Exception e) {
      System.out.println("Erro: Exceção inesperada: " + e.getMessage());
    }
    System.out.println();

    
    System.out.println("Teste 5: Rejeitar multa com placa inválida");
    totalTestes++;
    try {
      Multa m2 = new Multa("INVALIDA", "Estacionamento Irregular", 130.16,
          LocalDate.now(), "Rua Augusta", false);
      s1.adicionarMulta(m2);
      System.out.println("Erro: Multa inválida foi aceita!");
    } catch (IllegalArgumentException e) {
      System.out.println("Multa com placa inválida foi rejeitada");
      System.out.println("  Mensagem: " + e.getMessage());
      testesPassados++;
    }
    System.out.println();

    
    System.out.println("Teste 6: Listar todas as multas");
    totalTestes++;
    try {
      Multa m3 = new Multa("XYZ5678", "Avanço de Sinal Vermelho", 293.47,
          LocalDate.now(), "Av. Rebouças", false);
      s1.adicionarMulta(m3);

      if (s1.listarTodas().size() == 2) {
        System.out.println("Lista de multas retornada corretamente");
        System.out.println("  Total: " + s1.getTotalMultas() + " multas");
        testesPassados++;
      } else {
        System.out.println("Erro: Tamanho da lista incorreto");
      }
    } catch (Exception e) {
      System.out.println("Erro: Exceção: " + e.getMessage());
    }
    System.out.println();

    
    System.out.println("Teste 7: Calcular valor total de multas");
    totalTestes++;
    double valorTotal = s1.getValorTotal();
    double valorEsperado = 195.23 + 293.47; // 2 multas
    if (Math.abs(valorTotal - valorEsperado) < 0.01) {
      System.out.println("Valor total calculado corretamente");
      System.out.println("  Valor: R$ " + String.format("%.2f", valorTotal));
      testesPassados++;
    } else {
      System.out.println("Erro: Valor incorreto - esperado R$ " +
          String.format("%.2f", valorEsperado) + ", obtido R$ " +
          String.format("%.2f", valorTotal));
    }
    System.out.println();

  
    // Resumo
    System.out.println("=".repeat(70));
    System.out.println("RESUMO DOS TESTES");
    System.out.println("=".repeat(70));
    System.out.println("Total de testes: " + totalTestes);
    System.out.println("Testes passados: " + testesPassados);
    System.out.println("Testes falhados: " + (totalTestes - testesPassados));
    System.out.println();

    double percentual = (testesPassados * 100.0) / totalTestes;
    System.out.println("Taxa de sucesso: " + String.format("%.1f%%", percentual));

    if (testesPassados == totalTestes) {
      System.out.println();
      System.out.println("Singleton implementado corretamente");
    } else {
      System.out.println();
      System.out.println("Revise o código e execute novamente.");
    }

    System.out.println("=".repeat(70));
  }
}
