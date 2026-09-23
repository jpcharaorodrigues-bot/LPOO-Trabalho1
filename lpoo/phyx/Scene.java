/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.phyx;

import java.util.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public final class Scene
{
  public String name()
  {
    return name;
  }

  public void add(RigidBody actor)
  {
    actors.add(actor);
  }

  public Iterable<RigidBody> actors()
  {
    return actors;
  }

  public Scene(String name)
  {
    this.name = name;
    actors = new ArrayList<>();
  }

  private final String name;
  private final List<RigidBody> actors;

} // Scene
