/******************************************************************************
 *                                                                            *
 * Copyright: (c) Syncleus, Inc.                                              *
 *                                                                            *
 * You may redistribute and modify this source code under the terms and       *
 * conditions of the Open Source Community License - Type C version 1.0       *
 * or any later version as published by Syncleus, Inc. at www.syncleus.com.   *
 * There should be a copy of the license included with this file. If a copy   *
 * of the license is not included you are granted no right to distribute or   *
 * otherwise use this file except through a legal and valid license. You      *
 * should also contact Syncleus, Inc. at the information below if you cannot  *
 * find a license:                                                            *
 *                                                                            *
 * Syncleus, Inc.                                                             *
 * 2604 South 12th Street                                                     *
 * Philadelphia, PA 19148                                                     *
 *                                                                            *
 ******************************************************************************/
package com.syncleus.ferma.recurse.titangraph;

import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.attribute.Geoshape;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.ElementHelper;


/**
 * Example Graph factory that creates a {@link TitanGraph} based on roman mythology.
 * Used in the documentation examples and tutorials on the TitanGraph site. This is
 * An acyclic graph with a tree structure.
 */
public class GodGraphLoader {

  public static void load(final Graph graph) {

    // vertices

    Vertex saturn = graph.addVertex(null);
    saturn.setProperty("name", "saturn");
    saturn.setProperty("age", 10000);
    saturn.setProperty("type", "titan");
    saturn.setProperty("classType", "God");

    Vertex sky = graph.addVertex(null);
    ElementHelper.setProperties(sky, "name", "sky", "type", "location", "other", "more useless info");

    Vertex sea = graph.addVertex(null);
    ElementHelper.setProperties(sea, "name", "sea", "type", "location");

    Vertex jupiter = graph.addVertex(null);
    ElementHelper.setProperties(jupiter, "name", "jupiter", "age", 5000, "type", "god", "classType", "God");

    Vertex neptune = graph.addVertex(null);
    ElementHelper.setProperties(neptune, "name", "neptune", "age", 4500, "type", "god", "classType", "God");

    Vertex hercules = graph.addVertex(null);
    ElementHelper.setProperties(hercules, "name", "hercules", "age", 30, "type", "demigod", "classType", "GodExtended");

    Vertex alcmene = graph.addVertex(null);
    ElementHelper.setProperties(alcmene, "name", "alcmene", "age", 45, "type", "human", "classType", "God");

    Vertex pluto = graph.addVertex(null);
    ElementHelper.setProperties(pluto, "name", "pluto", "age", 4000, "type", "god", "classType", "God");

    Vertex nemean = graph.addVertex(null);
    ElementHelper.setProperties(nemean, "name", "nemean", "type", "monster", "classType", "God");

    Vertex hydra = graph.addVertex(null);
    ElementHelper.setProperties(hydra, "name", "hydra", "type", "monster", "classType", "God");

    Vertex cerberus = graph.addVertex(null);
    ElementHelper.setProperties(cerberus, "name", "cerberus", "type", "monster", "classType", "God");

    Vertex tartarus = graph.addVertex(null);
    ElementHelper.setProperties(tartarus, "name", "tartarus", "type", "location", "classType", "God");

    // edges

    ElementHelper.setProperties(jupiter.addEdge("father", saturn), "classType", "Father");
    jupiter.addEdge("lives", sky).setProperty("reason", "loves fresh breezes");
    jupiter.addEdge("brother", neptune);
    jupiter.addEdge("brother", pluto);

    ElementHelper.setProperties(neptune.addEdge("father", saturn), "classType", "Father");
    neptune.addEdge("lives", sea).setProperty("reason", "loves waves");
    neptune.addEdge("brother", jupiter);
    neptune.addEdge("brother", pluto);

    ElementHelper.setProperties(hercules.addEdge("father", jupiter), "classType", "FatherEdgeExtended");
    hercules.addEdge("lives", sky).setProperty("reason", "loves heights");
    ElementHelper.setProperties(hercules.addEdge("battled", nemean), "time", 1, "place", Geoshape.point(38.1f, 23.7f));
    ElementHelper.setProperties(hercules.addEdge("battled", hydra), "time", 2, "place", Geoshape.point(37.7f, 23.9f));
    ElementHelper.setProperties(hercules.addEdge("battled", cerberus), "time", 12, "place", Geoshape.point(39f, 22f));

    ElementHelper.setProperties(pluto.addEdge("father", saturn), "classType", "Father");
    pluto.addEdge("brother", jupiter);
    pluto.addEdge("brother", neptune);
    pluto.addEdge("lives", tartarus).setProperty("reason", "no fear of death");
    pluto.addEdge("pet", cerberus);

    cerberus.addEdge("lives", tartarus);
    ElementHelper.setProperties(cerberus.addEdge("battled", alcmene), "time", 5, "place", Geoshape.point(68.1f, 13.3f));

    // commit the transaction to disk
    if (graph instanceof TransactionalGraph)
      ((TransactionalGraph) graph).commit();
  }
}