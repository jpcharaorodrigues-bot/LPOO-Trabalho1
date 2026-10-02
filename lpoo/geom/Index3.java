/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.geom;

/**
 *
 * @author Paulo Pagliosa
 */
public final class Index3
{
  // Índices dos três vértices que formam o triângulo.
  public final int i;
  public final int j;
  public final int k;

  public Index3(int i, int j, int k)
  {
    this.i = i;
    this.j = j;
    this.k = k;
  }

  @Override
  public String toString()
  {
    return String.format("(%d,%d,%d)", i, j, k);
  }

} // Index3