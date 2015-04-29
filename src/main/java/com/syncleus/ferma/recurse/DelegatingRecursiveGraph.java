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

import com.syncleus.ferma.DelegatingFramedTransactionalGraph;
import com.syncleus.ferma.FrameFactory;
import com.syncleus.ferma.ReflectionCache;
import com.syncleus.ferma.TypeResolver;
import com.tinkerpop.blueprints.TransactionalGraph;

import java.util.Collection;

public class DelegatingRecursiveGraph<G extends RecursiveTransactionalGraph<?>> extends DelegatingFramedTransactionalGraph implements RecursiveTransactionalGraph<G> {
  private final Object id;
  private final RecursiveGraphFactory<G> parentGraphFactory;

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final FrameFactory builder, final TypeResolver defaultResolver, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate, builder, defaultResolver);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final ReflectionCache reflections, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate, reflections);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final TypeResolver defaultResolver, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate, defaultResolver);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final boolean typeResolution, final boolean annotationsSupported, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate, typeResolution, annotationsSupported);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final ReflectionCache reflections, final boolean typeResolution, final boolean annotationsSupported, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate, reflections, typeResolution, annotationsSupported);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final Collection<? extends Class<?>> types, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate, types);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  public DelegatingRecursiveGraph(final TransactionalGraph delegate, final boolean typeResolution, final Collection<? extends Class<?>> types, final RecursiveGraphFactory parentGraphFactory, final Object id) {
    super(delegate, typeResolution, types);
    this.parentGraphFactory = parentGraphFactory;
    this.id = id;
  }

  @Override
  public RecursiveGraphFactory<G> getParent() {
    return this.parentGraphFactory;
  }

  @Override
  public <N> N getId() {
    return null;
  }

  @Override
  public G subgraph(Object id) {
    if (id == null)
      throw new IllegalArgumentException("id can not be null");

    return this.parentGraphFactory.subgraph(this.id.toString() + "." + id.toString());
  }
}
