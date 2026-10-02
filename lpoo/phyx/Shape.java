/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

public abstract class Shape
{
  public final String name()
  {
    return name;
  }

  public final Pose pose()
  {
    return pose;
  }

  public final Vector3 translation()
  {
    return pose.translation();
  }

  public final Quaternion rotation()
  {
    return pose.rotation();
  }

  public final void setPose(Pose pose)
  {
    this.pose = pose;
  }

  public abstract float area();

  public abstract float volume();

  public abstract float mass();

  public abstract Vector3 centerOfMass();

  public abstract Matrix3 inertia();

  public abstract Bounds3 bounds();

  protected Shape(String name, Pose pose)
  {
    this.name = name;
    this.pose = pose;
  }

  private final String name;

  // Pose da forma em relação ao seu referencial pai.
  private Pose pose;

} // Shape