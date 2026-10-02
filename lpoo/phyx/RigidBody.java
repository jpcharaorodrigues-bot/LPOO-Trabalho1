/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

public final class RigidBody
{
  public String name()
  {
    return name;
  }

  public Pose pose()
  {
    return pose;
  }

  public Vector3 translation()
  {
    return pose.translation();
  }

  public Quaternion rotation()
  {
    return pose.rotation();
  }

  public Shape shape()
  {
    return shape;
  }

  public float area()
  {
    return shape.area();
  }

  public float volume()
  {
    return shape.volume();
  }

  public float mass()
  {
    return shape.mass();
  }

  public Vector3 centerOfMass()
  {
    // Leva o centro de massa da forma ao sistema global.
    Vector3 c = shape.pose().transform(shape.centerOfMass());

    return pose.transform(c);
  }

  public Matrix3 inertia()
  {
    // Combina as rotações da forma e do corpo rígido.
    Matrix3 rb = rotation().toRotationMatrix();
    Matrix3 rs = shape.rotation().toRotationMatrix();
    Matrix3 r = rb.mul(rs);

    return r.mul(shape.inertia()).mul(r.transpose());
  }

  public Bounds3 bounds()
  {
    Bounds3 b = shape.bounds();
    Vector3 min = b.min();
    Vector3 max = b.max();

    // Compõe a pose da forma com a pose global do corpo.
    Pose globalPose = pose.compose(shape.pose());
    Bounds3 result = new Bounds3();

    // A nova AABB envolve os oito vértices transformados.
    for (int x = 0; x < 2; x++)
      for (int y = 0; y < 2; y++)
        for (int z = 0; z < 2; z++)
        {
          Vector3 p = new Vector3(
            x == 0 ? min.x : max.x,
            y == 0 ? min.y : max.y,
            z == 0 ? min.z : max.z);

          result.expand(globalPose.transform(p));
        }

    return result;
  }

  public RigidBody(String name, Shape shape, Pose pose)
  {
    this.name = name;
    this.shape = shape;
    this.pose = pose;
  }

  private final String name;
  private final Shape shape;
  private final Pose pose;

} // RigidBody