/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

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

    // O eixo de simetria do cilindro coincide com o eixo y.
    float i = m * (4 * halfHeight * halfHeight +
      3 * radius * radius) / 12;
    float iy = m * radius * radius / 2;

    return Matrix3.diagonal(i, iy, i);
  }

  @Override
  public Bounds3 bounds()
  {
    // A altura total se estende de -halfHeight a +halfHeight.
    return new Bounds3(
      new Vector3(-radius, -halfHeight, -radius),
      new Vector3(radius, halfHeight, radius));
  }

  public Cylinder(String name, float radius, float halfHeight,
    float density, Pose pose)
  {
    super(name, pose, density);
    this.radius = radius;
    this.halfHeight = halfHeight;
  }

  private final float radius;
  private final float halfHeight;

} // Cylinder