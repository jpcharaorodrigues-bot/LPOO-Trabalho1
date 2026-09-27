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

    while (sc.hasNext("composite"))
      readCompositeDefinition();

    while (sc.hasNext())
      scene.add(readRigidBody());

    return scene;
  }

  private void readCompositeDefinition()
    throws IOException
  {
    expect("composite");

    String name = sc.next();
    Composite composite = new Composite(name);

    while (!sc.hasNext("end"))
      composite.add(readShape());

    expect("end");
    composites.put(name, composite);
  }

  private RigidBody readRigidBody()
    throws IOException
  {
    expect("actor");

    String name = sc.next();
    Pose pose = readPose();
    Shape shape = readShape();

    return new RigidBody(name, shape, pose);
  }

  private Shape readShape()
    throws IOException
  {
    String type = sc.next();

    switch (type)
    {
      case "box":
        return readBox();

      case "sphere":
        return readSphere();

      case "cylinder":
        return readCylinder();

      case "capsule":
        return readCapsule();

      case "mesh":
        return readMesh();

      case "composite":
        return readComposite();

      case "instance":
        return readInstance();

      default:
        throw new IllegalArgumentException(
          "unknown shape type: " + type);
    }
  }

  private Box readBox()
  {
    String name = sc.next();
    float sx = sc.nextFloat();
    float sy = sc.nextFloat();
    float sz = sc.nextFloat();
    float density = sc.nextFloat();
    Pose pose = readPose();

    return new Box(name, sx, sy, sz, density, pose);
  }

  private Sphere readSphere()
  {
    String name = sc.next();
    float radius = sc.nextFloat();
    float density = sc.nextFloat();
    Pose pose = readPose();

    return new Sphere(name, radius, density, pose);
  }

  private Cylinder readCylinder()
  {
    String name = sc.next();
    float radius = sc.nextFloat();
    float halfHeight = sc.nextFloat();
    float density = sc.nextFloat();
    Pose pose = readPose();

    return new Cylinder(
      name, radius, halfHeight, density, pose);
  }

  private Capsule readCapsule()
  {
    String name = sc.next();
    float radius = sc.nextFloat();
    float halfHeight = sc.nextFloat();
    float density = sc.nextFloat();
    Pose pose = readPose();

    return new Capsule(
      name, radius, halfHeight, density, pose);
  }

  private Mesh readMesh()
    throws IOException
  {
    String name = sc.next();
    float density = sc.nextFloat();
    String filename = sc.next();
    Pose pose = readPose();

    File file = new File(filename);

    if (!file.isAbsolute())
      file = new File(directory, filename);

    return new Mesh(
      name, density, ObjReader.read(file), pose);
  }

  private Composite readComposite()
    throws IOException
  {
    String name = sc.next();
    Composite composite = new Composite(name);

    while (!sc.hasNext("end"))
      composite.add(readShape());

    expect("end");
    composite.setPose(readPose());

    return composite;
  }

  private CompositeInstance readInstance()
  {
    String name = sc.next();
    String compositeName = sc.next();
    Composite composite = composites.get(compositeName);

    if (composite == null)
      throw new IllegalArgumentException(
        "undefined composite: " + compositeName);

    Pose pose = readPose();

    return new CompositeInstance(name, composite, pose);
  }

  private Pose readPose()
  {
    expect("pose");

    Vector3 translation = readVector3();
    Quaternion rotation = readQuaternion();

    return new Pose(translation, rotation);
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
