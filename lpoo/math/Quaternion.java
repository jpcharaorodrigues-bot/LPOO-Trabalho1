/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.math;

/**
 *
 * @author Paulo Pagliosa
 */
public final class Quaternion
{
  public static final Quaternion IDENTITY = new Quaternion(0, 0, 0, 1);

  public final float x;
  public final float y;
  public final float z;
  public final float w;

  public Quaternion(float x, float y, float z, float w)
  {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  public float normSquared()
  {
    return x * x + y * y + z * z + w * w;
  }

  public boolean isUnit()
  {
    return Real.isEqual(normSquared(), 1);
  }

  public Quaternion mul(Quaternion q)
  {
    return new Quaternion(
      w * q.x + x * q.w + y * q.z - z * q.y,
      w * q.y - x * q.z + y * q.w + z * q.x,
      w * q.z + x * q.y - y * q.x + z * q.w,
      w * q.w - x * q.x - y * q.y - z * q.z);
  }

  public Matrix3 toRotationMatrix()
  {
    if (!isUnit())
      throw new IllegalStateException("Quaternion is not unit");

    float xx = x * x, yy = y * y, zz = z * z;
    float xy = x * y, xz = x * z, yz = y * z;
    float wx = w * x, wy = w * y, wz = w * z;
    float[][] r = new float[3][3];

    r[0][0] = 1 - 2 * (yy + zz);
    r[0][1] = 2 * (xy - wz);
    r[0][2] = 2 * (xz + wy);
    r[1][0] = 2 * (xy + wz);
    r[1][1] = 1 - 2 * (xx + zz);
    r[1][2] = 2 * (yz - wx);
    r[2][0] = 2 * (xz - wy);
    r[2][1] = 2 * (yz + wx);
    r[2][2] = 1 - 2 * (xx + yy);

    return new Matrix3(r);
  }

} // Quaternion
