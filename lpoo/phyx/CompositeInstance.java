/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public final class CompositeInstance
  extends Shape
{
  public Composite composite()
  {
    return composite;
  }

  @Override
  public float area()
  {
    return composite.area();
  }

  @Override
  public float volume()
  {
    return composite.volume();
  }

  @Override
  public float mass()
  {
    return composite.mass();
  }

  @Override
  public Vector3 centerOfMass()
  {
    return composite.centerOfMass();
  }

  @Override
  public Matrix3 inertia()
  {
    return composite.inertia();
  }

  @Override
  public Bounds3 bounds()
  {
    return composite.bounds();
  }

  public CompositeInstance(String name, Vector3 translation,
    Quaternion rotation, Composite composite)
  {
    super(name, translation, rotation);
    this.composite = composite;
  }

  private final Composite composite;

} // CompositeInstance
