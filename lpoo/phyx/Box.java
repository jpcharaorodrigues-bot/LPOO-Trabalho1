/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

public final class Box
  extends Primitive
{
  public float sx()
  {
    return sx;
  }

  public float sy()
  {
    return sy;
  }

  public float sz()
  {
    return sz;
  }

  @Override
  public float area()
  {
    return 8 * (sx * sy + sx * sz + sy * sz);
  }

  @Override
  public float volume()
  {
    return 8 * sx * sy * sz;
  }

  @Override
  public Matrix3 inertia()
  {
    float m = mass();
    float k = m / 3;

    // Tensor de inércia da caixa em relação ao seu centro.
    return Matrix3.diagonal(
      k * (sy * sy + sz * sz),
      k * (sx * sx + sz * sz),
      k * (sx * sx + sy * sy));
  }

  @Override
  public Bounds3 bounds()
  {
    // sx, sy e sz representam as semidimensões da caixa.
    return new Bounds3(
      new Vector3(-sx, -sy, -sz),
      new Vector3(sx, sy, sz));
  }

  public Box(String name, float sx, float sy, float sz,
    float density, Pose pose)
  {
    super(name, pose, density);
    this.sx = sx;
    this.sy = sy;
    this.sz = sz;
  }

  private final float sx;
  private final float sy;
  private final float sz;

} // Box