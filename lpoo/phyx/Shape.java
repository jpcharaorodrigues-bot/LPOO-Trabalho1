/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import lpoo.geom.*;
import lpoo.math.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Guilherme Peres Pinto
 */
public abstract class Shape
{
  public final String name()
  {
    return name;
  }

  public final Vector3 translation()
  {
    return translation;
  }

  public final Quaternion rotation()
  {
    return rotation;
  }

  public abstract float area();

  public abstract float volume();

  public abstract float mass();

  public abstract Vector3 centerOfMass();

  public abstract Matrix3 inertia();

  public abstract Bounds3 bounds();

  protected Shape(String name, Vector3 translation, Quaternion rotation)
  {
    this.name = name;
    this.translation = translation;
    this.rotation = rotation;
  }

  private final String name;
  private final Vector3 translation;
  private final Quaternion rotation;

} // Shape