/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.geom;

import lpoo.math.*;

/**
 *
 * @author Paulo Pagliosa
 */
public abstract class MeshBuilder
{  
  protected static TriangleMesh build(Vector3[] vertices, Index3[] triangles)
  {
    return new TriangleMesh(vertices, triangles);
  }

} // MeshBuilder
