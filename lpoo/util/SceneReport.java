/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.util;

import lpoo.phyx.*;
import java.io.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public final class SceneReport
{
  public static void write(Scene scene, PrintWriter out)
  {
    out.println("Scene: " + scene.name());

    for (RigidBody body : scene.actors())
    {
      out.println("Body: " + body.name());
      out.println("Area: " + body.area());
      out.println("Volume: " + body.volume());
      out.println("Mass: " + body.mass());
      out.println("Center of mass: " + body.centerOfMass());
      out.println("Inertia: " + body.inertia());
      out.println("AABB: " + body.bounds());
      out.println("Shape:");

      writeShape(body.shape(), out, "  ");
    }

    out.flush();
  }

  private static void writeShape(Shape shape, PrintWriter out,
    String indent)
  {
    out.println(indent + "Type: " +
      shape.getClass().getSimpleName());
    out.println(indent + "Name: " + shape.name());
    out.println(indent + "Area: " + shape.area());
    out.println(indent + "Volume: " + shape.volume());
    out.println(indent + "Mass: " + shape.mass());
    out.println(indent + "Center of mass: " +
      shape.centerOfMass());
    out.println(indent + "Inertia: " + shape.inertia());
    out.println(indent + "AABB: " + shape.bounds());

    // Percorre recursivamente a hierarquia das formas compostas.
    if (shape instanceof Composite)
    {
      Composite composite = (Composite)shape;

      for (Shape child : composite.shapes())
        writeShape(child, out, indent + "  ");
    }
    else if (shape instanceof CompositeInstance)
    {
      CompositeInstance instance = (CompositeInstance)shape;

      for (Shape child : instance.composite().shapes())
        writeShape(child, out, indent + "  ");
    }
  }

} // SceneReport