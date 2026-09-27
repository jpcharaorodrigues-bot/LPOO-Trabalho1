/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

import lpoo.math.*;
import lpoo.phyx.*;

public final class PhysicsTest
{
  public static void main(String[] args)
  {
    Pose pose = new Pose(
      new Vector3(1, 2, 3),
      Quaternion.IDENTITY);

    System.out.println("Pose translation: " + pose.translation());
    System.out.println("Pose rotation unit: " + pose.rotation().isUnit());
    System.out.println("Pose transform: " +
      pose.transform(new Vector3(1, 1, 1)));

    Pose composed = pose.compose(Pose.IDENTITY);
    System.out.println("Composed translation: " +
      composed.translation());

    Quaternion q =
      Quaternion.IDENTITY.mul(Quaternion.IDENTITY);

    System.out.println("Quaternion multiplication unit: " +
      q.isUnit());

    Box box = new Box(
      "box", 1, 2, 3, 4, Pose.IDENTITY);

    System.out.println("Box name: " + box.name());
    System.out.println("Box density: " + box.density());
    System.out.println("Box sx: " + box.sx());
    System.out.println("Box sy: " + box.sy());
    System.out.println("Box sz: " + box.sz());

    Sphere sphere = new Sphere(
      "sphere", 2, 3, Pose.IDENTITY);

    System.out.println("Sphere radius: " + sphere.radius());

    Cylinder cylinder = new Cylinder(
      "cylinder", 2, 3, 4, Pose.IDENTITY);

    System.out.println("Cylinder radius: " + cylinder.radius());
    System.out.println("Cylinder half height: " +
      cylinder.halfHeight());

    Capsule capsule = new Capsule(
      "capsule", 1, 2, 5, Pose.IDENTITY);

    System.out.println("Capsule radius: " + capsule.radius());
    System.out.println("Capsule half height: " +
      capsule.halfHeight());

    Composite composite = new Composite("composite");
    composite.add(box);
    composite.add(sphere);

    System.out.println("Composite mass: " + composite.mass());

    CompositeInstance instance = new CompositeInstance(
      "instance", composite, Pose.IDENTITY);

    System.out.println("Instance composite: " +
      instance.composite().name());

    RigidBody body = new RigidBody(
      "body", instance, pose);

    System.out.println("Body name: " + body.name());
    System.out.println("Body translation: " + body.translation());
    System.out.println("Body rotation unit: " +
      body.rotation().isUnit());
    System.out.println("Body shape: " + body.shape().name());
    System.out.println("Body area: " + body.area());
    System.out.println("Body volume: " + body.volume());
    System.out.println("Body mass: " + body.mass());
    System.out.println("Body center of mass: " +
      body.centerOfMass());
    System.out.println("Body inertia:");
    System.out.print(body.inertia());
    System.out.println("Body bounds: " + body.bounds());
  }

} // PhysicsTest