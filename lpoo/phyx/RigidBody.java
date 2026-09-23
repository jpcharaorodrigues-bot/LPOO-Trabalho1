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
public final class RigidBody
{
  public String name()
  {
    return name;
  }

  public Vector3 translation()
  {
    return translation;
  }

  public Quaternion rotation()
  {
    return rotation;
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
    Matrix3 rb = rotation.toRotationMatrix();
    Matrix3 rs = shape.rotation().toRotationMatrix();

    Vector3 c = rs.mul(shape.centerOfMass())
      .add(shape.translation());

    return rb.mul(c).add(translation);
  }

  public Matrix3 inertia()
  {
    Matrix3 rb = rotation.toRotationMatrix();
    Matrix3 rs = shape.rotation().toRotationMatrix();
    Matrix3 r = rb.mul(rs);

    return r.mul(shape.inertia()).mul(r.transpose());
  }

  public Bounds3 bounds()
  {
    Bounds3 b = shape.bounds();
    Vector3 min = b.min();
    Vector3 max = b.max();

    Matrix3 rb = rotation.toRotationMatrix();
    Matrix3 rs = shape.rotation().toRotationMatrix();
    Matrix3 r = rb.mul(rs);

    Vector3 t = rb.mul(shape.translation()).add(translation);

    Bounds3 result = new Bounds3();

    for (int x = 0; x < 2; x++)
      for (int y = 0; y < 2; y++)
        for (int z = 0; z < 2; z++)
        {
          Vector3 p = new Vector3(
            x == 0 ? min.x : max.x,
            y == 0 ? min.y : max.y,
            z == 0 ? min.z : max.z);

          result.expand(r.mul(p).add(t));
        }

    return result;
  }

  public RigidBody(String name, Vector3 translation, Quaternion rotation,
    Shape shape)
  {
    this.name = name;
    this.translation = translation;
    this.rotation = rotation;
    this.shape = shape;
  }

  private final String name;
  private final Vector3 translation;
  private final Quaternion rotation;
  private final Shape shape;

} // RigidBody
