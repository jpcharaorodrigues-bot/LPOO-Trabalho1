/*
 * Autores:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

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

    try (PrintWriter out = new PrintWriter(new File(args[1])))
    {
      SceneReport.write(scene, out);
    }
  }

} // SceneTest
