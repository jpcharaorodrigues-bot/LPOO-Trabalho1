/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.math.*;

public final class Pose
{
  public static final Pose IDENTITY =
    new Pose(Vector3.NULL, Quaternion.IDENTITY);

  public Vector3 translation()
  {
    return translation;
  }

  public Quaternion rotation()
  {
    return rotation;
  }

  public Vector3 transform(Vector3 p)
  {
    return rotation.toRotationMatrix().mul(p).add(translation);
  }

  public Pose compose(Pose pose)
  {
    Matrix3 r = rotation.toRotationMatrix();

    return new Pose(
      r.mul(pose.translation).add(translation),
      rotation.mul(pose.rotation));
  }

  public Pose(Vector3 translation, Quaternion rotation)
  {
    this.translation = translation;
    this.rotation = rotation;
  }

  private final Vector3 translation;
  private final Quaternion rotation;

} // Pose
