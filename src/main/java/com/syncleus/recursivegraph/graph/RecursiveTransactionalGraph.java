package com.syncleus.recursivegraph.graph;

import com.syncleus.ferma.FramedTransactionalGraph;

public interface RecursiveTransactionalGraph<G extends RecursiveTransactionalGraph<?>> extends RecursiveGraph<G>, FramedTransactionalGraph {
}
