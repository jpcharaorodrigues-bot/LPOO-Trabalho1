/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

public final class Mesh
  extends Primitive
{
  public Mesh(String name, float density, TriangleMesh mesh, Pose pose)
  {
    super(name, pose, density);
    this.mesh = mesh;
  }

  @Override
  public float area()
  {
    float area = 0;

    for (int i = 0; i < mesh.triangleCount(); i++)
    {
      Index3 triangle = mesh.triangle(i);
      Vector3 a = mesh.vertex(triangle.i);
      Vector3 b = mesh.vertex(triangle.j);
      Vector3 c = mesh.vertex(triangle.k);

      area += b.sub(a).cross(c.sub(a)).norm() * 0.5f;
    }

    return area;
  }

  @Override
  public float volume()
  {
    float volume = 0;

    for (int i = 0; i < mesh.triangleCount(); i++)
    {
      Index3 triangle = mesh.triangle(i);
      Vector3 a = mesh.vertex(triangle.i);
      Vector3 b = mesh.vertex(triangle.j);
      Vector3 c = mesh.vertex(triangle.k);

      volume += a.dot(b.cross(c)) / 6.0f;
    }

    return Math.abs(volume);
  }

  @Override
  public Vector3 centerOfMass()
  {
    float volume = 0;
    Vector3 moment = Vector3.NULL;

    for (int i = 0; i < mesh.triangleCount(); i++)
    {
      Index3 triangle = mesh.triangle(i);
      Vector3 a = mesh.vertex(triangle.i);
      Vector3 b = mesh.vertex(triangle.j);
      Vector3 c = mesh.vertex(triangle.k);
      float v = a.dot(b.cross(c)) / 6.0f;

      volume += v;
      moment = moment.add(a.add(b).add(c).mul(v / 4.0f));
    }

    if (Real.isZero(volume))
      return Vector3.NULL;

    return moment.mul(1.0f / volume);
  }

  @Override
  public Matrix3 inertia()
  {
    float signedVolume = 0;
    Vector3 moment = Vector3.NULL;
    Matrix3 q = Matrix3.zero();

    for (int i = 0; i < mesh.triangleCount(); i++)
    {
      Index3 triangle = mesh.triangle(i);
      Vector3 a = mesh.vertex(triangle.i);
      Vector3 b = mesh.vertex(triangle.j);
      Vector3 c = mesh.vertex(triangle.k);
      float v = a.dot(b.cross(c)) / 6.0f;
      Vector3 s = a.add(b).add(c);
      float k = v / 20.0f;

      signedVolume += v;
      moment = moment.add(s.mul(v / 4.0f));

      q = q.add(Matrix3.outer(s, k))
        .add(Matrix3.outer(a, k))
        .add(Matrix3.outer(b, k))
        .add(Matrix3.outer(c, k));
    }

    if (Real.isZero(signedVolume))
      return Matrix3.zero();

    Vector3 center = moment.mul(1.0f / signedVolume);
    float orientation = signedVolume < 0 ? -1.0f : 1.0f;
    float volume = signedVolume * orientation;

    q = q.mul(orientation);

    float trace = q.get(0, 0) + q.get(1, 1) + q.get(2, 2);
    float rho = density();
    float m = rho * volume;

    Matrix3 inertia = Matrix3.identity().mul(rho * trace)
      .add(q.mul(-rho));

    return inertia
      .add(Matrix3.identity().mul(-m * center.normSquared()))
      .add(Matrix3.outer(center, m));
  }

  @Override
  public Bounds3 bounds()
  {
    Bounds3 bounds = new Bounds3();

    for (int i = 0; i < mesh.vertexCount(); i++)
      bounds.expand(mesh.vertex(i));

    return bounds;
  }

  private final TriangleMesh mesh;

} // Mesh
