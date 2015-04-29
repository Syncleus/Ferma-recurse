package com.syncleus.recursivegraph.graph;

import com.syncleus.ferma.FrameFactory;
import com.syncleus.ferma.ReflectionCache;
import com.syncleus.ferma.TypeResolver;
import com.tinkerpop.blueprints.TransactionalGraph;

import java.util.Collection;

public abstract class AbstractDelegatingRecursiveGraphFactory<G extends TransactionalGraph> extends AbstractRecursiveGraphFactory<RecursiveGraph<?>> {
  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory) {
    super(new DefaultDelegatingGraphFactory(factory));
  }

  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory, final Collection<? extends Class<?>> annotatedTypes) {
    super(new ReflectionsDelegatingGraphFactory(factory, new ReflectionCache(annotatedTypes)));
  }

  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory, final ReflectionCache reflections) {
    super(new ReflectionsDelegatingGraphFactory(factory, reflections));
  }

  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory, final FrameFactory builder, final TypeResolver defaultResolver) {
    super(new ResolverBuilderDelegatingGraphFactory(factory, builder, defaultResolver));
  }

  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory, final TypeResolver defaultResolver) {
    super(new ResolverDelegatingGraphFactory(factory, defaultResolver));
  }

  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory, final boolean typeResolution, final boolean annotationsSupported) {
    super(new BooleanDelegatingGraphFactory(factory, typeResolution, annotationsSupported));
  }

  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory, final ReflectionCache reflections, final boolean typeResolution, final boolean annotationsSupported) {
    super(new ReflectionsBooleanDelegatingGraphFactory(factory, reflections, typeResolution, annotationsSupported));
  }

  protected AbstractDelegatingRecursiveGraphFactory(final GraphFactory<G> factory, final boolean typeResolution, final Collection<? extends Class<?>> annotatedTypes) {
    super(new TypesBooleanDelegatingGraphFactory(factory, typeResolution, annotatedTypes));
  }

  protected interface GraphFactory<G extends TransactionalGraph> {
    G constructGraph(RecursiveGraphFactory parent, Object id);
  }

  private static class DefaultDelegatingGraphFactory<G extends TransactionalGraph> implements DelegatingGraphFactory<RecursiveGraph<?>> {
    private final GraphFactory<G> factory;

    public DefaultDelegatingGraphFactory(final GraphFactory<G> factory) {
      this.factory = factory;
    }

    @Override
    public RecursiveGraph<?> constructGraph(RecursiveGraphFactory parent, Object id) {
      return new DelegatingRecursiveGraph(this.factory.constructGraph(parent, id), parent, id);
    }
  }

  private static class ReflectionsDelegatingGraphFactory<G extends TransactionalGraph> implements DelegatingGraphFactory<RecursiveGraph<?>> {
    private final GraphFactory<G> factory;
    private final ReflectionCache reflections;

    public ReflectionsDelegatingGraphFactory(final GraphFactory<G> factory, final ReflectionCache reflections) {
      this.factory = factory;
      this.reflections = reflections;
    }

    @Override
    public RecursiveGraph<?> constructGraph(final RecursiveGraphFactory parent, final Object id) {
      return new DelegatingRecursiveGraph(this.factory.constructGraph(parent, id), this.reflections, parent, id);
    }
  }

  private static class ResolverBuilderDelegatingGraphFactory<G extends TransactionalGraph> implements DelegatingGraphFactory<RecursiveGraph<?>> {
    private final GraphFactory<G> factory;
    private final FrameFactory builder;
    private final TypeResolver defaultResolver;

    public ResolverBuilderDelegatingGraphFactory(final GraphFactory<G> factory, final FrameFactory builder, final TypeResolver defaultResolver) {
      this.factory = factory;
      this.builder = builder;
      this.defaultResolver = defaultResolver;
    }

    @Override
    public RecursiveGraph<?> constructGraph(final RecursiveGraphFactory parent, final Object id) {
      return new DelegatingRecursiveGraph(this.factory.constructGraph(parent, id), this.builder, this.defaultResolver, parent, id);
    }
  }

  private static class ResolverDelegatingGraphFactory<G extends TransactionalGraph> implements DelegatingGraphFactory<RecursiveGraph<?>> {
    private final GraphFactory<G> factory;
    private final TypeResolver defaultResolver;

    public ResolverDelegatingGraphFactory(final GraphFactory<G> factory, final TypeResolver defaultResolver) {
      this.factory = factory;
      this.defaultResolver = defaultResolver;
    }

    @Override
    public RecursiveGraph<?> constructGraph(final RecursiveGraphFactory parent, final Object id) {
      return new DelegatingRecursiveGraph(this.factory.constructGraph(parent, id), this.defaultResolver, parent, id);
    }
  }

  private static class BooleanDelegatingGraphFactory<G extends TransactionalGraph> implements DelegatingGraphFactory<RecursiveGraph<?>> {
    private final GraphFactory<G> factory;
    private final boolean typeResolution;
    private final boolean annotationsSupported;

    public BooleanDelegatingGraphFactory(final GraphFactory<G> factory, final boolean typeResolution, final boolean annotationsSupported) {
      this.factory = factory;
      this.typeResolution = typeResolution;
      this.annotationsSupported = annotationsSupported;
    }

    @Override
    public RecursiveGraph<?> constructGraph(final RecursiveGraphFactory parent, final Object id) {
      return new DelegatingRecursiveGraph(this.factory.constructGraph(parent, id), this.typeResolution, this.annotationsSupported, parent, id);
    }
  }

  private static class ReflectionsBooleanDelegatingGraphFactory<G extends TransactionalGraph> implements DelegatingGraphFactory<RecursiveGraph<?>> {
    private final GraphFactory<G> factory;
    private final ReflectionCache reflections;
    private final boolean typeResolution;
    private final boolean annotationsSupported;

    public ReflectionsBooleanDelegatingGraphFactory(final GraphFactory<G> factory, final ReflectionCache reflections, final boolean typeResolution, final boolean annotationsSupported) {
      this.factory = factory;
      this.typeResolution = typeResolution;
      this.annotationsSupported = annotationsSupported;
      this.reflections = reflections;
    }

    @Override
    public RecursiveGraph<?> constructGraph(final RecursiveGraphFactory parent, final Object id) {
      return new DelegatingRecursiveGraph(this.factory.constructGraph(parent, id), this.reflections, this.typeResolution, this.annotationsSupported, parent, id);
    }
  }

  private static class TypesBooleanDelegatingGraphFactory<G extends TransactionalGraph> implements DelegatingGraphFactory<RecursiveGraph<?>> {
    private final GraphFactory<G> factory;
    private final boolean typeResolution;
    private final Collection<? extends Class<?>> annotatedTypes;

    public TypesBooleanDelegatingGraphFactory(final GraphFactory<G> factory, final boolean typeResolution, final Collection<? extends Class<?>> annotatedTypes) {
      this.factory = factory;
      this.typeResolution = typeResolution;
      this.annotatedTypes = annotatedTypes;
    }

    @Override
    public RecursiveGraph<?> constructGraph(final RecursiveGraphFactory parent, final Object id) {
      return new DelegatingRecursiveGraph(this.factory.constructGraph(parent, id), this.typeResolution, annotatedTypes, parent, id);
    }
  }
}
