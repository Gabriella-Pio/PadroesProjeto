# Padrões de Projeto - Engenharia de Software

Repositório com implementações de padrões de projeto para a disciplina do 5º período de Engenharia de Software.

Este repositório contém diversos projetos práticos que demonstram a aplicação dos principais padrões de projeto definidos pela "Gang of Four" (GoF), com implementações em **Java** utilizando **Maven** e interfaces gráficas com **Swing**.

---

## Projetos Implementados

### 1. **Adapter** 
- **Pastas**: `ConversorUnidades`, `MusicPlayer`, `PlayerMusical`, `SinalMorse`
- **Descrição**: Padrão estrutural que converte a interface de uma classe em outra interface esperada pelos clientes
- **Aplicações**:
  - Conversor de Unidades (Adapter entre sistemas de medida)
  - Player Musical (Adaptação de reprodutores)

### 2. **Decorator**
- **Pasta**: `Decorator`
- **Descrição**: Padrão estrutural que permite adicionar comportamentos dinamicamente a um objeto sem afetar sua estrutura
- **Aplicação**: Sistema de vendas com funcionalidades adicionáveis (embrulho, cartão, etc.)

### 3. **Factory**
- **Pasta**: `Factory`
- **Descrição**: Padrão criacional que define uma interface para criar objetos, mas deixa as subclasses decidirem qual classe instanciar
- **Aplicação**: Criação de produtos diversos com lógica centralizada

### 4. **Iterator**
- **Pasta**: `Iterator`
- **Descrição**: Padrão comportamental que fornece uma forma de acessar sequencialmente os elementos de um objeto composto sem expor sua representação subjacente
- **Aplicação**: Iteração sobre coleções de dados

### 5. **Template Method**
- **Pasta**: `Template`
- **Descrição**: Padrão comportamental que define o esqueleto de um algoritmo em uma classe base, deixando que as subclasses preencham os detalhes
- **Aplicação**: Relatório de ênfases com estrutura padronizada

### 6. **Singleton**
- **Pasta**: `ProjetoMultas`
- **Descrição**: Padrão criacional que garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global
- **Aplicação**: Sistema centralizado de gerenciamento de multas de trânsito

---

## Tecnologias Utilizadas

- **Linguagem**: Java 21
- **Build**: Maven 3.x
- **Interface**: Swing (AWT)
- **IDE Recomendada**: NetBeans, IntelliJ IDEA ou VS Code com extensões Java
- **Sistema Operacional**: Compatível com Linux, macOS e Windows

---

## Como Executar

### Pré-requisitos
- Java Development Kit (JDK) 21 ou superior
- Apache Maven 3.6 ou superior

### Instruções de Execução

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/MyCorp-test/PadroesProjeto.git
   cd PadroesProjeto
   ```

2. **Navegue até o projeto desejado**:
   ```bash
   cd PadroesProjeto
   ```

3. **Compile com Maven**:
   ```bash
   mvn clean compile
   ```

4. **Execute o projeto**:
   ```bash
   mvn exec:java
   ```

   Ou, se houver um script específico:
   ```bash
   ./rodar.sh
   ```

5. **Execute os testes** (se houver):
   ```bash
   mvn test
   ```

---

## Estrutura dos Projetos

Cada projeto segue uma estrutura Maven padrão:

```
projeto/
├── src/
│   ├── main/
│   │   └── java/                # Código-fonte principal
│   │       ├── Model/           # Classes de modelo
│   │       ├── View/            # Interface gráfica
│   │       ├── Controller/       # Lógica de controle
│   │       └── ...
│   └── test/
│       └── java/                # Testes unitários
├── pom.xml                      # Configuração Maven
└── README.md                    # Documentação específica
```

---

## Conceitos Fundamentais

### Design Patterns (Padrões de Projeto)

**Padrões de Criação** (Creational):
- Lidam com a criação de objetos de forma genérica
- Factory, Singleton, Builder, Prototype, Abstract Factory

**Padrões Estruturais** (Structural):
- Lidam com a composição de classes e objetos
- Adapter, Decorator, Facade, Proxy, Bridge, Flyweight, Composite

**Padrões Comportamentais** (Behavioral):
- Lidam com comunicação entre objetos e responsabilidades
- Iterator, Template Method, Strategy, Observer, Command, State, Visitor, etc.

---

## Referências e Recursos

### Livros Base

1. **"Design Patterns: Elements of Reusable Object-Oriented Software"**
   - Autores: Gang of Four (GoF)
   - Publicação: 1994
   - Descrição: O livro mais influente sobre padrões de projeto. Define 23 padrões fundamentais.
   - [Comprar na Amazon](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)

2. **"Head First Design Patterns"**
   - Autores: Freeman & Freeman
   - Descrição: Abordagem visual e didática dos padrões GoF com exemplos práticos
   - [Site oficial](https://www.oreilly.com/library/view/head-first-design/0596007124/)

3. **"Refactoring: Improving the Design of Existing Code"**
   - Autor: Martin Fowler
   - Descrição: Técnicas para melhorar código e aplicar padrões em código legado

4. **"Clean Code: A Handbook of Agile Software Craftsmanship"**
   - Autor: Robert C. Martin (Uncle Bob)
   - Descrição: Boas práticas de código e como padrões contribuem para qualidade

### Sites e Recursos Online

#### Tutoriais e Documentação

- **Refactoring.Guru** - Design Patterns
  - https://refactoring.guru/design-patterns/
  - Excelente recurso com exemplos em múltiplas linguagens

- **TutorialsPoint - Design Patterns**
  - https://www.tutorialspoint.com/design_pattern/index.htm
  - Tutoriais claros com diagramas e exemplos

- **GeeksforGeeks - Design Patterns**
  - https://www.geeksforgeeks.org/design-patterns-in-java/
  - Foco específico em Java com exemplos práticos

- **Oracle Java Documentation**
  - https://docs.oracle.com/en/java/
  - Documentação oficial da plataforma Java

#### Vídeos e Cursos

- **YouTube - Design Patterns in Java**
  - Canal: Tech With Tim, Code With Mosh, Traversy Media
  - Buscas: "Design Patterns Java", "Gang of Four Patterns"

#### Comunidades

- **Stack Overflow**
  - https://stackoverflow.com/questions/tagged/design-patterns
  - Para dúvidas sobre implementação

- **GitHub - Design Patterns Examples**
  - https://github.com/iluwatar/java-design-patterns
  - Repositório completo com exemplos de todos os 23 padrões GoF em Java

- **Baeldung - Design Patterns**
  - https://www.baeldung.com/design-patterns-post
  - Artigos detalhados sobre cada padrão

#### Ferramentas

- **Draw.io** - Para diagramas UML
  - https://draw.io/

- **PlantUML** - Geração de diagramas de código
  - https://plantuml.com/

- **Maven Repository**
  - https://mvnrepository.com/
  - Para encontrar dependências

---

## Objetivos de Aprendizado

Ao estudar este repositório, você vai entender:

- Os padrões definidos pela Gang of Four
- Quando e onde aplicar cada padrão
- Como implementar padrões em Java
- Princípios SOLID e suas relações com padrões
- Trade-offs entre diferentes soluções de design
- Como estruturar código para melhor manutenibilidade  

---

## Ideias para Expandir

- Adicionar mais exemplos de teste unitário
- Complementar com anti-padrões
- Criar diagramas UML para cada padrão
- Documentar mais aplicações práticas reais
- Criar wiki com explicações mais detalhadas

---

## Licença

Este projeto é desenvolvido para fins educacionais.

---

## Autor

Gabriella - Engenharia de Software (5º período)  
Disciplina: Padrões de Projeto  
Ano: 2026

---

## Dúvidas

Para dúvidas sobre os projetos ou padrões de projeto, dá uma olhada primeiro na documentação dos projetos individuais. Se não encontrar, consultar o Stack Overflow ou a comunidade do GitHub.
