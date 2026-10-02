/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import java.util.*;
import lpoo.geom.*;
import lpoo.math.*;

public final class Composite
  extends Shape
{
  public void add(Shape shape)
  {
    shapes.add(shape);
  }

  public Iterable<Shape> shapes()
  {
    return shapes;
  }

  @Override
  public float area()
  {
    float a = 0;

    for (Shape shape : shapes)
      a += shape.area();

    return a;
  }

  @Override
  public float volume()
  {
    float v = 0;

    for (Shape shape : shapes)
      v += shape.volume();

    return v;
  }

  @Override
  public float mass()
  {
    float m = 0;

    for (Shape shape : shapes)
      m += shape.mass();

    return m;
  }

  @Override
  public Vector3 centerOfMass()
  {
    float m = mass();

    if (Real.isZero(m))
      return Vector3.NULL;

    Vector3 c = Vector3.NULL;

    for (Shape shape : shapes)
    {
      // Leva o centro de massa da forma ao referencial do composto.
      Vector3 p = shape.pose().transform(shape.centerOfMass());

      c = c.add(p.mul(shape.mass()));
    }

    return c.mul(1 / m);
  }

  @Override
  public Matrix3 inertia()
  {
    Vector3 c = centerOfMass();
    Matrix3 result = Matrix3.zero();

    for (Shape shape : shapes)
    {
      // Expressa o tensor da forma no referencial do composto.
      Matrix3 r = shape.rotation().toRotationMatrix();
      Matrix3 i = r.mul(shape.inertia()).mul(r.transpose());

      Vector3 p = shape.pose().transform(shape.centerOfMass());
      Vector3 d = p.sub(c);
      float m = shape.mass();

      // Transfere o tensor para o centro de massa do composto.
      Matrix3 shift = Matrix3.identity()
        .mul(m * d.normSquared())
        .add(Matrix3.outer(d, -m));

      result = result.add(i).add(shift);
    }

    return result;
  }

  @Override
  public Bounds3 bounds()
  {
    Bounds3 result = new Bounds3();

    for (Shape shape : shapes)
    {
      Bounds3 b = shape.bounds();
      Vector3 min = b.min();
      Vector3 max = b.max();

      // Transforma os oito vértices da caixa local da forma.
      for (int x = 0; x < 2; x++)
        for (int y = 0; y < 2; y++)
          for (int z = 0; z < 2; z++)
          {
            Vector3 p = new Vector3(
              x == 0 ? min.x : max.x,
              y == 0 ? min.y : max.y,
              z == 0 ? min.z : max.z);

            result.expand(shape.pose().transform(p));
          }
    }

    return result;
  }

  public Composite(String name)
  {
    super(name, Pose.IDENTITY);
    shapes = new ArrayList<>();
  }

  private final List<Shape> shapes;

} // Composite