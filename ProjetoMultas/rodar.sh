#!/bin/bash
# Script para compilar e executar o projeto ProjetoMultas

echo "======================================"
echo "  COMPILANDO PROJETO MULTAS"
echo "======================================"
echo ""

# Navega para o diretório src
cd src

# Remove arquivos .class antigos
echo "1. Removendo arquivos compilados antigos..."
find . -name "*.class" -delete
echo "   ✓ Limpeza concluída"
echo ""

# Compila todos os arquivos
echo "2. Compilando arquivos Java..."
javac models/Multa.java \
      services/SistemaMultas.java \
      ui/TelaRadar.java \
      ui/TelaCentral.java \
      ui/MultasFrame.java \
      Main.java

if [ $? -eq 0 ]; then
    echo "   ✓ Compilação bem-sucedida!"
else
    echo "   ✗ ERRO na compilação!"
    exit 1
fi

echo ""
echo "======================================"
echo "  ESCOLHA UMA OPÇÃO:"
echo "======================================"
echo ""
echo "1) MainDemo  - 3 janelas (RECOMENDADO)"
echo "2) Main      - Janela única completa"
echo "3) Testes    - Executar testes unitários"
echo ""
read -p "Escolha (1 ou 2): " opcao

echo ""

if [ "$opcao" = "1" ]; then
    echo "Executando Main (janela única)..."
    java Main
elif [ "$opcao" = "2" ]; then
    echo "Executando testes unitários..."
    javac tests/SistemaMultasTest.java
    java tests.SistemaMultasTest
else
    echo "Opção inválida!"
    exit 1
fi
