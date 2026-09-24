/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.util;

import lpoo.math.*;
import lpoo.phyx.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public final class SceneReader
{
  public static Scene read(File file)
    throws IOException
  {
    try (Scanner sc = new Scanner(file))
    {
      sc.useLocale(Locale.US);
      File directory = file.getAbsoluteFile().getParentFile();

      return new SceneReader(
        sc, sceneName(file), directory).readFile();
    }
  }

  private static String sceneName(File file)
  {
    String name = file.getName();
    int i = name.lastIndexOf('.');

    return i > 0 ? name.substring(0, i) : name;
  }

  private Scene readFile()
    throws IOException
  {
    Scene scene = new Scene(name);

    String section = sc.next();

    if (section.equals("composites"))
    {
      int count = sc.nextInt();

      for (int i = 0; i < count; i++)
        readCompositeDefinition();

      section = sc.next();
    }

    if (!section.equals("actors"))
      throw new IllegalArgumentException("actors expected");

    int count = sc.nextInt();

    for (int i = 0; i < count; i++)
      scene.add(readRigidBody());

    return scene;
  }

  private void readCompositeDefinition()
    throws IOException
  {
    expect("composite");

    String name = sc.next();
    int count = sc.nextInt();

    Composite composite = new Composite(
      name, Vector3.NULL, Quaternion.IDENTITY);

    for (int i = 0; i < count; i++)
      composite.add(readShape());

    composites.put(name, composite);
  }

  private RigidBody readRigidBody()
    throws IOException
  {
    expect("body");

    String name = sc.next();
    Vector3 translation = readVector3();
    Quaternion rotation = readQuaternion();
    Shape shape = readShape();

    return new RigidBody(name, translation, rotation, shape);
  }

  private Shape readShape()
    throws IOException
  {
    String type = sc.next();
    String name = sc.next();
    Vector3 translation = readVector3();
    Quaternion rotation = readQuaternion();

    if (type.equals("box"))
    {
      float density = sc.nextFloat();
      float sx = sc.nextFloat();
      float sy = sc.nextFloat();
      float sz = sc.nextFloat();

      return new Box(
        name, translation, rotation, density, sx, sy, sz);
    }

    if (type.equals("sphere"))
    {
      float density = sc.nextFloat();
      float radius = sc.nextFloat();

      return new Sphere(
        name, translation, rotation, density, radius);
    }

    if (type.equals("cylinder"))
    {
      float density = sc.nextFloat();
      float radius = sc.nextFloat();
      float halfHeight = sc.nextFloat();

      return new Cylinder(
        name, translation, rotation, density, radius, halfHeight);
    }

    if (type.equals("capsule"))
    {
      float density = sc.nextFloat();
      float radius = sc.nextFloat();
      float halfHeight = sc.nextFloat();

      return new Capsule(
        name, translation, rotation, density, radius, halfHeight);
    }

    if (type.equals("mesh"))
    {
      float density = sc.nextFloat();
      String filename = sc.next();
      File file = new File(filename);

      if (!file.isAbsolute())
        file = new File(directory, filename);

      return new Mesh(
        name, translation, rotation, density, ObjReader.read(file));
    }

    if (type.equals("composite"))
    {
      int count = sc.nextInt();
      Composite composite =
        new Composite(name, translation, rotation);

      for (int i = 0; i < count; i++)
        composite.add(readShape());

      return composite;
    }

    if (type.equals("instance"))
    {
      String compositeName = sc.next();
      Composite composite = composites.get(compositeName);

      if (composite == null)
        throw new IllegalArgumentException(
          "undefined composite: " + compositeName);

      return new CompositeInstance(
        name, translation, rotation, composite);
    }

    throw new IllegalArgumentException(
      "unknown shape type: " + type);
  }

  private void expect(String value)
  {
    String token = sc.next();

    if (!token.equals(value))
      throw new IllegalArgumentException(value + " expected");
  }

  private Vector3 readVector3()
  {
    float x = sc.nextFloat();
    float y = sc.nextFloat();
    float z = sc.nextFloat();

    return new Vector3(x, y, z);
  }

  private Quaternion readQuaternion()
  {
    float x = sc.nextFloat();
    float y = sc.nextFloat();
    float z = sc.nextFloat();
    float w = sc.nextFloat();

    return new Quaternion(x, y, z, w);
  }

  private final Scanner sc;
  private final String name;
  private final File directory;
  private final Map<String, Composite> composites;

  private SceneReader(Scanner sc, String name, File directory)
  {
    this.sc = sc;
    this.name = name;
    this.directory = directory;
    composites = new HashMap<>();
  }

} // SceneReader
