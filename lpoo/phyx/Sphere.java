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
public final class Sphere
  extends Primitive
{
  public float radius()
  {
    return radius;
  }

  @Override
  public float area()
  {
    return 4 * (float)Math.PI * radius * radius;
  }

  @Override
  public float volume()
  {
    return 4 * (float)Math.PI * radius * radius * radius / 3;
  }

  @Override
  public Matrix3 inertia()
  {
    float i = 2 * mass() * radius * radius / 5;

    return Matrix3.diagonal(i);
  }

  @Override
  public Bounds3 bounds()
  {
    return new Bounds3(
      new Vector3(-radius, -radius, -radius),
      new Vector3(radius, radius, radius));
  }

  public Sphere(String name, Vector3 translation, Quaternion rotation,
    float density, float radius)
  {
    super(name, translation, rotation, density);
    this.radius = radius;
  }

  private final float radius;

} // Sphere
