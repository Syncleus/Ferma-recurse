package com.syncleus.recursivegraph.graph;

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
