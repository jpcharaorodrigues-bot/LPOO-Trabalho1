Trabalho 1 - LPOO 2026

Universidade Federal de Mato Grosso do Sul
Faculdade de Computação
Linguagem de Programação Orientada a Objetos


Autores

João Pedro Rodrigues Charão
Pedro Henrique da Silva Mendes
Guilherme Peres Pinto


Objetivo

Implementação, em Java, de um modelo de corpos rígidos tridimensionais.

O programa representa cenas compostas por corpos rígidos e formas
geométricas, permitindo calcular:

- área superficial;
- volume;
- massa;
- centro de massa;
- tensor de inércia em relação ao centro de massa;
- caixa limitante alinhada aos eixos (AABB).

São suportadas formas primitivas e formas compostas. Também foi
implementado o suporte opcional a formas definidas por malhas de
triângulos.


Organização

As classes do projeto estão organizadas em pacotes de acordo com suas
responsabilidades.

lpoo.geom
  Classes relacionadas à geometria e às estruturas utilizadas pelas
  formas.

lpoo.math
  Classes utilizadas nas operações matemáticas, vetores, matrizes e
  rotações.

lpoo.phyx
  Classes responsáveis pela representação de poses, formas, corpos
  rígidos e cenas.

Os programas de teste e os arquivos de entrada estão localizados na
raiz do projeto.


Atividades

A1 - Concluída.

Implementação da hierarquia de classes para representação de cenas,
corpos rígidos, formas primitivas, formas compostas e instâncias de
formas compostas.


A2 - Concluída.

Implementação do leitor de arquivos de cena a partir de um arquivo
informado pela linha de comando.

O leitor trata poses, formas primitivas, formas compostas, instâncias
de formas compostas e as informações necessárias para construir os
corpos rígidos da cena.


A3 - Concluída.

Implementação do gerador de relatório da cena.

Para cada corpo rígido, o relatório apresenta nome, área, volume,
massa, centro de massa, tensor de inércia, AABB e a descrição
recursiva de sua forma.


A4 - Concluída.

Foram preparados arquivos de entrada e classes de teste para
exercitar as classes e os métodos utilizados na implementação.

Os testes abrangem operações matemáticas, poses, formas primitivas,
formas compostas, corpos rígidos, leitura de cenas, geração de
relatórios e leitura de malhas.


A5 - Concluída.

Implementação do suporte a formas definidas por malhas de triângulos.

A implementação inclui leitura de arquivos OBJ e cálculo de área,
volume, massa, centro de massa, tensor de inércia e AABB da malha.


A6 - Pendente.

Será produzido um vídeo com a apresentação do código-fonte,
compilação, execução dos programas e apresentação dos resultados.


Arquivos de teste

scene-test.txt
  Cena utilizada para testar formas primitivas, formas compostas,
  instâncias e composição de formas.

scene-rotation.txt
  Cena utilizada para testar poses e rotações.

scene-mesh.txt
  Cena utilizada para testar uma forma definida por malha de
  triângulos.

cube.obj
  Malha cúbica utilizada nos testes da A5.

PhysicsTest.java
  Testa operações de Pose e Quaternion e métodos relacionados às
  formas, limites e corpos rígidos.

MeshTest.java
  Testa a leitura de arquivos OBJ e a estrutura TriangleMesh.

MeshShapeTest.java
  Testa as propriedades físicas calculadas para uma forma definida
  por malha.

SceneTest.java
  Testa a leitura de uma cena e a geração de seu relatório.


Compilação

A partir da raiz do projeto:

javac $(find . -name "*.java")


Execução dos testes

Teste das operações e propriedades básicas:

java PhysicsTest

Teste da leitura da malha OBJ:

java MeshTest cube.obj

Teste das propriedades físicas da malha:

java MeshShapeTest cube.obj

Teste da cena principal:

java SceneTest scene-test.txt saida.txt

Teste da cena com rotações:

java SceneTest scene-rotation.txt saida-rotation.txt

Teste da cena contendo malha:

java SceneTest scene-mesh.txt saida-mesh.txt


Resultados

Os programas SceneTest geram os relatórios nos arquivos de saída
informados pela linha de comando.

Os arquivos de saída são utilizados para verificar as propriedades
calculadas para os corpos rígidos e suas formas.


Vídeo

Link:
PENDENTE
