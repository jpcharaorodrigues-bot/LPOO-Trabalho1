/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

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

  public CompositeInstance(String name, Composite composite, Pose pose)
  {
    super(name, pose);
    this.composite = composite;
  }

  private final Composite composite;

} // CompositeInstance
