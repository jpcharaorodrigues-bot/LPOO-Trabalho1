/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.math.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public abstract class Primitive
  extends Shape
{
  public final float density()
  {
    return density;
  }

  @Override
  public float mass()
  {
    return density * volume();
  }

  @Override
  public Vector3 centerOfMass()
  {
    return Vector3.NULL;
  }

  protected Primitive(String name, Vector3 translation, Quaternion rotation,
    float density)
  {
    super(name, translation, rotation);
    this.density = density;
  }

  private final float density;

} // Primitive
