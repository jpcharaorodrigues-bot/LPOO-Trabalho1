/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

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

  public Sphere(String name, float radius, float density, Pose pose)
  {
    super(name, pose, density);
    this.radius = radius;
  }

  private final float radius;

} // Sphere
