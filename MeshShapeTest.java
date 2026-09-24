/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

import lpoo.geom.*;
import lpoo.math.*;
import lpoo.phyx.*;
import lpoo.util.*;
import java.io.*;

public final class MeshShapeTest
{
  public static void main(String[] args)
    throws IOException
  {
    if (args.length < 1)
    {
      System.err.println("Use: java MeshShapeTest <obj_filename>");
      return;
    }

    TriangleMesh mesh = ObjReader.read(args[0]);
    Mesh shape = new Mesh("mesh", Vector3.NULL,
      Quaternion.IDENTITY, 1, mesh);

    System.out.printf("Area: %g\n", shape.area());
    System.out.printf("Volume: %g\n", shape.volume());
    System.out.printf("Mass: %g\n", shape.mass());
    System.out.printf("Center of mass: %s\n", shape.centerOfMass());
    System.out.printf("Inertia:\n%s", shape.inertia());
    System.out.printf("Bounds: %s\n", shape.bounds());
  }

} // MeshShapeTest
