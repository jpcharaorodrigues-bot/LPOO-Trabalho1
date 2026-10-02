/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.math;

/**
 *
 * @author Paulo Pagliosa
 */
public class Real
{
  // Tolerância usada nas comparações entre valores reais.
  public static final float EPS = 1e-6f;

  private float value;

  public static boolean isZero(float a)
  {
    return Math.abs(a) <= EPS;
  }

  public static boolean isEqual(float a, float b)
  {
    return isZero(a - b);
  }

  public Real(float value)
  {
    this.value = value;
  }

} // Real