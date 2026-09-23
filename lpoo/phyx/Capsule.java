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
public final class Capsule
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
    return 4 * (float)Math.PI * radius *
      (halfHeight + radius);
  }

  @Override
  public float volume()
  {
    return 2 * (float)Math.PI * radius * radius * halfHeight +
      4 * (float)Math.PI * radius * radius * radius / 3;
  }

  @Override
  public Matrix3 inertia()
  {
    float pi = (float)Math.PI;
    float r2 = radius * radius;

    float cylinderMass =
      density() * 2 * pi * r2 * halfHeight;

    float hemisphereMass =
      density() * 2 * pi * r2 * radius / 3;

    float ixCylinder = cylinderMass *
      (4 * halfHeight * halfHeight + 3 * r2) / 12;

    float iyCylinder = cylinderMass * r2 / 2;

    float d = halfHeight + 3 * radius / 8;

    float ixHemisphere =
      83 * hemisphereMass * r2 / 320 +
      hemisphereMass * d * d;

    float iyHemisphere =
      2 * hemisphereMass * r2 / 5;

    return Matrix3.diagonal(
      ixCylinder + 2 * ixHemisphere,
      iyCylinder + 2 * iyHemisphere,
      ixCylinder + 2 * ixHemisphere);
  }

  @Override
  public Bounds3 bounds()
  {
    return new Bounds3(
      new Vector3(
        -radius,
        -halfHeight - radius,
        -radius),
      new Vector3(
        radius,
        halfHeight + radius,
        radius));
  }

  public Capsule(String name, Vector3 translation, Quaternion rotation,
    float density, float radius, float halfHeight)
  {
    super(name, translation, rotation, density);
    this.radius = radius;
    this.halfHeight = halfHeight;
  }

  private final float radius;
  private final float halfHeight;

} // Capsule
