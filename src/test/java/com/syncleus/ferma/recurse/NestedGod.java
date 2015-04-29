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
package com.syncleus.ferma.recurse;


import com.syncleus.ferma.AbstractVertexFrame;
import com.syncleus.ferma.FramedGraph;

public abstract class NestedGod extends AbstractVertexFrame implements God {
  public void createSubgraph() {
    if (this.countSubnodes() == 0) {
      final FramedGraph graph = this.getGraph();
      if (graph instanceof RecursiveGraph) {
        final RecursiveGraph<?> recursiveGraph = (RecursiveGraph<?>) graph;
        recursiveGraph.subgraph(this.getId()).addVertex(God.class);
        recursiveGraph.subgraph(this.getId()).addVertex(God.class);
        recursiveGraph.subgraph(this.getId()).addVertex(God.class);
      } else
        throw new IllegalStateException("Graph is not a RecursiveGraph");
    }
  }

  public long countSubnodes() {
    final FramedGraph graph = this.getGraph();
    if (graph instanceof RecursiveGraph)
      return ((RecursiveGraph<?>)graph).subgraph(this.getId()).v().aggregate().count();
    else
      throw new IllegalStateException("Graph is not a RecursiveGraph");
  }
}
