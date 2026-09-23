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
public final class Cylinder
  extends Primitive
{
  public float radius()
  {
    return radius;
  }

  public float halfHeight()
  {
    return halfHeight;
  }

  @Override
  public float area()
  {
    return 2 * (float)Math.PI * radius * (radius + 2 * halfHeight);
  }

  @Override
  public float volume()
  {
    return 2 * (float)Math.PI * radius * radius * halfHeight;
  }

  @Override
  public Matrix3 inertia()
  {
    float m = mass();
    float i = m * (4 * halfHeight * halfHeight +
      3 * radius * radius) / 12;
    float iy = m * radius * radius / 2;

    return Matrix3.diagonal(i, iy, i);
  }

  @Override
  public Bounds3 bounds()
  {
    return new Bounds3(
      new Vector3(-radius, -halfHeight, -radius),
      new Vector3(radius, halfHeight, radius));
  }

  public Cylinder(String name, Vector3 translation, Quaternion rotation,
    float density, float radius, float halfHeight)
  {
    super(name, translation, rotation, density);
    this.radius = radius;
    this.halfHeight = halfHeight;
  }

  private final float radius;
  private final float halfHeight;

} // Cylinder
