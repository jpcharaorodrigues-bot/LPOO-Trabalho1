/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

import lpoo.geom.*;
import lpoo.phyx.*;
import lpoo.util.*;
import java.io.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public final class SceneTest
{
  public static void main(String[] args)
    throws IOException
  {
    if (args.length < 2)
    {
      System.err.println(
        "Use: java SceneTest <scene_filename> <out_filename>");
      return;
    }

    Scene scene = SceneReader.read(new File(args[0]));

    testScene(scene);

    try (PrintWriter out = new PrintWriter(new File(args[1])))
    {
      SceneReport.write(scene, out);
    }
  }

  private static void testScene(Scene scene)
  {
    Bounds3 sceneBounds = null;

    for (RigidBody body : scene.actors())
    {
      body.pose();
      body.translation();

      testShape(body.shape());

      Bounds3 bounds = body.bounds();

      // Acumula os limites dos corpos da cena.
      if (sceneBounds == null)
        sceneBounds = new Bounds3(bounds.min(), bounds.max());
      else
      {
        sceneBounds = sceneBounds.union(bounds);
        sceneBounds.expand(bounds);
      }
    }
  }

  private static void testShape(Shape shape)
  {
    shape.translation();

    // Exercita os dados específicos de cada tipo de forma.
    if (shape instanceof Box box)
    {
      box.sx();
      box.sy();
      box.sz();
    }
    else if (shape instanceof Sphere sphere)
      sphere.radius();
    else if (shape instanceof Cylinder cylinder)
    {
      cylinder.radius();
      cylinder.halfHeight();
    }
    else if (shape instanceof Capsule capsule)
    {
      capsule.radius();
      capsule.halfHeight();
    }
    else if (shape instanceof Composite composite)
    {
      // Percorre recursivamente as formas internas.
      for (Shape child : composite.shapes())
        testShape(child);
    }
    else if (shape instanceof CompositeInstance instance)
      testShape(instance.composite());
  }

} // SceneTest