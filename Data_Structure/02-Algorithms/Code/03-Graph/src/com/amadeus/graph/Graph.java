package com.amadeus.graph;

public interface Graph<V, E> {

    public abstract void addVertex(V v);
    public abstract void addEdge(V from, V to);
    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeVertex(V v);
    public abstract void removeEdge(V from, V to);

    public abstract int edgesSize();
    public abstract int verticesSize();

    public abstract void bfs(V begin);
    public abstract void dfs(V begin);
}
