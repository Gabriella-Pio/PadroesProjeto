# Sistema de Multas de Trânsito - Padrão Singleton

## 📋 Descrição do Projeto

Sistema de gerenciamento de multas de trânsito desenvolvido em Java com interface gráfica Swing, demonstrando a aplicação do **padrão de projeto Singleton**.

O sistema centraliza todas as multas em um único ponto de controle, garantindo consistência e acesso global através de uma instância única da classe `SistemaMultas`.

---

## 🎯 Objetivo Acadêmico

Este projeto foi desenvolvido para demonstrar:
- **Padrão de Projeto Singleton**: Implementação correta com construtor privado e método `getInstance()`
- **Justificativa de uso**: Centralização de todas as multas em um único sistema
- **Interface Visual**: Demonstração prática e funcional do padrão

---

## 🏗️ Arquitetura

```
ProjetoMultas/
├── src/
│   ├── models/
│   │   └── Multa.java              # Classe que representa uma multa
│   ├── services/
│   │   └── SistemaMultas.java      # Singleton - gerencia todas as multas
│   ├── ui/
│   │   └── MultasFrame.java        # Interface gráfica Swing
│   └── Main.java                    # Classe principal
└── README.md
```

---

## 💡 Padrão Singleton

### O que é?
O Singleton é um padrão de projeto criacional que garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global a essa instância.

### Por que usar neste projeto?
- **Centralização**: Todas as multas são gerenciadas por uma única instância
- **Consistência**: Não há risco de ter listas de multas diferentes
- **Acesso Global**: Qualquer parte do sistema acessa o mesmo conjunto de dados

### Implementação

```java
public class SistemaMultas {
    // Instância única (atributo estático)
    private static SistemaMultas instancia = null;
    
    // Lista de multas
    private List<Multa> multas;
    
    // Construtor PRIVADO - impede criação externa
    private SistemaMultas() {
        this.multas = new ArrayList<>();
    }
    
    // Método estático para acesso único
    public static SistemaMultas getInstance() {
        if (instancia == null) {
            instancia = new SistemaMultas();
        }
        return instancia;
    }
}
```

---

## ✨ Funcionalidades

### 1. Cadastro de Multas
- Formulário com campos: Placa, Tipo de Infração, Valor, Data, Local
- Validação de campos obrigatórios
- Valores pré-definidos para tipos comuns de infrações

### 2. Tipos de Infrações Disponíveis
| Tipo de Infração | Valor (R$) |
|------------------|------------|
| Excesso de Velocidade | 195,23 |
| Estacionamento Irregular | 130,16 |
| Avanço de Sinal Vermelho | 293,47 |
| Dirigir sem CNH | 880,41 |
| Uso de Celular ao Volante | 293,47 |
| Falta de Cinto de Segurança | 195,23 |
| Ultrapassagem Indevida | 195,23 |
| Outras Infrações | (personalizado) |

### 3. Visualização
- Tabela com todas as multas cadastradas
- Atualização automática ao adicionar nova multa

### 4. Filtros
- Filtrar por placa (busca parcial)
- Filtrar por tipo de infração
- Limpar filtros

### 5. Estatísticas
- Total de multas cadastradas
- Valor total de todas as multas
- Atualização em tempo real

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior

### Compilação
```bash
cd ProjetoMultas/src
javac models/Multa.java services/SistemaMultas.java ui/*.java Main*.java
```

### Execução

#### Opção 1: Demonstração Visual com Múltiplas Janelas (⭐ RECOMENDADO!)
```bash
java MainDemo
```

**Abre 3 janelas simultaneamente:**
- 📡 Radar 1 (cadastro de multas)
- 📡 Radar 2 (cadastro de multas)
- 🏢 Central de Controle (visualização de TODAS as multas)

**Demonstração perfeita do Singleton:** Cadastre em um radar → aparece na central. Cadastre no outro radar → também aparece. TODOS os hash codes são iguais!

#### Opção 2: Interface Única Completa
```bash
java Main
```

Abre uma interface integrada com formulário, tabela, filtros e estatísticas.

---

## 🖥️ Interface Gráfica

A interface é dividida em 3 áreas principais:

1. **Painel de Cadastro** (esquerda)
   - Formulário para adicionar novas multas
   - Exibe o hash code da instância Singleton

2. **Painel de Visualização** (direita)
   - Tabela com todas as multas
   - Filtros de busca

3. **Painel de Estatísticas** (rodapé)
   - Total de multas
   - Valor total acumulado

---

## 📝 Demonstração do Singleton

Ao executar o programa, o console exibe:

```
============================================================
    SISTEMA DE MULTAS DE TRÂNSITO - PADRÃO SINGLETON
============================================================

>>> DEMONSTRAÇÃO DO PADRÃO SINGLETON <<<

Primeira instância criada:
  → Hash Code: 123456789

Segunda 'instância' (na verdade retorna a mesma):
  → Hash Code: 123456789

Terceira 'instância' (também retorna a mesma):
  → Hash Code: 123456789

>>> VERIFICAÇÃO <<<
sistema1 == sistema2? true
sistema2 == sistema3? true
sistema1 == sistema3? true

✓ CONCLUSÃO: Todas as referências apontam para a MESMA instância!
✓ Isso garante um único ponto de controle para todas as multas.
```

---

## 🎓 Conceitos Demonstrados

### 1. Singleton
- ✅ Construtor privado
- ✅ Atributo estático para armazenar a instância
- ✅ Método público estático `getInstance()`
- ✅ Verificação de nulidade antes de criar instância

### 2. Programação Orientada a Objetos
- Encapsulamento (atributos privados)
- Getters e Setters
- Separação de responsabilidades

### 3. Interface Gráfica
- Swing Components (JFrame, JPanel, JTable, etc.)
- Event Handling
- Layouts (BorderLayout, GridBagLayout)

### 4. Boas Práticas
- Código organizado em packages
- Comentários e documentação
- Validação de entrada
- Feedback ao usuário

---

## 👨‍🎓 Autor

Gabriella  
Disciplina: Padrões de Projeto  
Projeto: Aplicação do Padrão Singleton

---

## 📅 Entrega

**Prazo**: Sexta-feira  
**Objetivo**: Demonstrar aplicação prática do padrão Singleton com interface visual

---

## 🔍 Observações para Apresentação

**Pontos a destacar ao professor:**

1. ✅ **Singleton implementado corretamente** - Construtor privado + getInstance()
2. ✅ **Utilidade clara** - Centralização justificada (conforme sugestão do professor)
3. ✅ **Demonstração prática** - Console mostra que múltiplas referências = mesma instância
4. ✅ **Interface funcional** - Sistema completo e utilizável
5. ✅ **Código organizado** - Separação em packages (models, services, ui)
