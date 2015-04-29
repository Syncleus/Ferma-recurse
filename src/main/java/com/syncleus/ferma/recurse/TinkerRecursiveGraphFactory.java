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

import com.syncleus.ferma.FrameFactory;
import com.syncleus.ferma.ReflectionCache;
import com.syncleus.ferma.TypeResolver;
import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import java.util.Collection;

public class TinkerRecursiveGraphFactory extends AbstractDelegatingRecursiveGraphFactory<TransactionalGraph> {
  private static final GraphFactory<TransactionalGraph> FACTORY = new GraphFactory<TransactionalGraph>() {
    @Override
    public TransactionalGraph constructGraph(RecursiveGraphFactory parent, Object id) {
      return new MockTransactionalGraph(new TinkerGraph());
    }
  };

  public TinkerRecursiveGraphFactory() {
    super(FACTORY);
  }

  public TinkerRecursiveGraphFactory(Collection<? extends Class<?>> annotatedTypes) {
    super(FACTORY, annotatedTypes);
  }

  public TinkerRecursiveGraphFactory(ReflectionCache reflections) {
    super(FACTORY, reflections);
  }

  public TinkerRecursiveGraphFactory(FrameFactory builder, TypeResolver defaultResolver) {
    super(FACTORY, builder, defaultResolver);
  }

  public TinkerRecursiveGraphFactory(TypeResolver defaultResolver) {
    super(FACTORY, defaultResolver);
  }

  public TinkerRecursiveGraphFactory(boolean typeResolution, boolean annotationsSupported) {
    super(FACTORY, typeResolution, annotationsSupported);
  }

  public TinkerRecursiveGraphFactory(ReflectionCache reflections, boolean typeResolution, boolean annotationsSupported) {
    super(FACTORY, reflections, typeResolution, annotationsSupported);
  }

  public TinkerRecursiveGraphFactory(boolean typeResolution, Collection<? extends Class<?>> annotatedTypes) {
    super(FACTORY, typeResolution, annotatedTypes);
  }
}
