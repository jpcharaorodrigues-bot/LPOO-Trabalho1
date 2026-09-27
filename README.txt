Trabalho 1 - LPOO 2026

Universidade Federal de Mato Grosso do Sul
Faculdade de Computação
Linguagem de Programação Orientada a Objetos

Autores:
João Pedro Rodrigues Charão
Pedro Henrique da Silva Mendes
Guilherme Peres Pinto


Objetivo

Implementação, em Java, de um modelo de corpos rígidos tridimensionais.
O programa representa cenas, atores e formas geométricas e permite
calcular área superficial, volume, massa, centro de massa, tensor de
inércia e caixa limitante alinhada aos eixos (AABB).

As formas implementadas incluem primitivas e formas compostas.
Também foi implementado o suporte opcional a formas definidas por
malhas de triângulos.


Atividades

A1 - Concluída.
Implementação da hierarquia de classes para representação de cenas,
corpos rígidos, formas primitivas e formas compostas.

A2 - Concluída.
Implementação do leitor de arquivos de cena, incluindo poses,
primitivas, formas compostas e instâncias de formas compostas.

A3 - Concluída.
Implementação do gerador de relatório contendo as propriedades dos
corpos rígidos e a descrição recursiva de suas formas.

A4 - Concluída.
Foram preparados arquivos de entrada e classes de teste para verificar
as classes e os métodos implementados.

A5 - Concluída.
Implementação do bônus de formas definidas por malhas de triângulos,
incluindo cálculo de área, volume, massa, centro de massa, tensor de
inércia e AABB.

A6 - Pendente.
O vídeo será produzido com a explicação do código-fonte, compilação,
execução e apresentação dos resultados.


Arquivos de teste

scene-test.txt
  Teste das formas primitivas, formas compostas e instâncias.

scene-rotation.txt
  Teste de poses e rotações.

scene-mesh.txt
  Teste de cena contendo malha de triângulos.

cube.obj
  Malha utilizada nos testes da A5.

MeshTest.java
  Teste de leitura de arquivos OBJ e da estrutura TriangleMesh.

MeshShapeTest.java
  Teste das propriedades físicas de uma forma definida por malha.

SceneTest.java
  Teste da leitura de cenas e geração do relatório.

PhysicsTest.java
  Teste das operações de Pose, Quaternion e das propriedades das
  formas e corpos rígidos.


Compilação

javac $(find . -name "*.java")


Execução dos testes

java PhysicsTest
java MeshShapeTest cube.obj
java SceneTest scene-test.txt saida.txt
java SceneTest scene-rotation.txt saida-rotation.txt
java SceneTest scene-mesh.txt saida-mesh.txt


Vídeo

Link:
PENDENTE
