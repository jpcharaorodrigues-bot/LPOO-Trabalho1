/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.math.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Guilherme Peres Pinto
 */
public abstract class Primitive
  extends Shape
{
  public final float density()
  {
    return density;
  }

  protected Primitive(String name, Vector3 translation, Quaternion rotation,
    float density)
  {
    super(name, translation, rotation);
    this.density = density;
  }

  private final float density;

} // Primitive
