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
 ******************************************************************************/package com.syncleus.ferma.recurse;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRecursiveGraphFactory<G extends RecursiveGraph<?>> implements RecursiveGraphFactory<G> {
  private final Map<Object, G> graphs = new HashMap<>();
  private final DelegatingGraphFactory<G> factory;

  protected AbstractRecursiveGraphFactory(DelegatingGraphFactory<G> factory) {
    this.factory = factory;
  }

  @Override
  public RecursiveGraphFactory<G> getParent() {
    return null;
  }

  @Override
  public <N> N getId() {
    return null;
  }

  @Override
  public G subgraph(Object id) {
    G graph = this.graphs.get(id);
    if (graph == null) {
      graph = this.factory.constructGraph(this, id);
      this.graphs.put(id, graph);
    }
    return graph;
  }

  protected interface DelegatingGraphFactory<G extends RecursiveGraph<?>> {
    G constructGraph(RecursiveGraphFactory parent, Object id);
  }

  ;
}
