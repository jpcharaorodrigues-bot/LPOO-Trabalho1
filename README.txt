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

O programa representa cenas formadas por corpos rígidos e formas
geométricas, permitindo calcular área superficial, volume, massa,
centro de massa, tensor de inércia e caixa limitante alinhada aos
eixos (AABB).

São suportadas formas primitivas, formas compostas e formas definidas
por malhas de triângulos.


Organização

lpoo.geom
  Estruturas geométricas, limites e representação de malhas.

lpoo.math
  Vetores, matrizes, quaternions e operações matemáticas.

lpoo.phyx
  Formas, poses, corpos rígidos e cenas.

lpoo.util
  Leitura de arquivos OBJ e de cenas e geração de relatórios.

Os programas de teste e os arquivos de entrada estão na raiz do
projeto.


Estado das atividades

A1 - Concluída.
Hierarquia de classes para formas, corpos rígidos e cenas.

A2 - Concluída.
Leitura de cenas a partir de arquivo informado pela linha de comando.

A3 - Concluída.
Geração de relatório com as propriedades dos corpos rígidos e suas
formas.

A4 - Concluída.
Arquivos de entrada e programas de teste para as classes e métodos da
solução.

A5 - Concluída.
Suporte a formas definidas por malhas de triângulos e leitura de
arquivos OBJ.

A6 - Pendente.
Apresentação em vídeo do código-fonte, compilação, execução e
resultados.


Arquivos de teste

scene-test.txt
  Teste de formas primitivas, formas compostas e instâncias.

scene-rotation.txt
  Teste de poses e rotações.

scene-mesh.txt
  Teste de cena contendo uma forma definida por malha.

cube.obj
  Malha cúbica utilizada nos testes da A5.

PhysicsTest.java
  Teste das operações e propriedades físicas básicas.

MeshTest.java
  Teste da leitura de arquivos OBJ e da estrutura TriangleMesh.

MeshShapeTest.java
  Teste das propriedades físicas de uma forma definida por malha.

SceneTest.java
  Teste da leitura de cenas e geração de relatórios.


Compilação

A partir da raiz do projeto:

javac $(find . -name "*.java")


Execução

java PhysicsTest

java MeshTest cube.obj

java MeshShapeTest cube.obj

java SceneTest scene-test.txt saida.txt

java SceneTest scene-rotation.txt saida-rotation.txt

java SceneTest scene-mesh.txt saida-mesh.txt


Vídeo

Link:
PENDENTE
