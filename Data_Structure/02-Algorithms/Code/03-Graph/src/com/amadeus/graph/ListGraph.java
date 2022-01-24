package com.amadeus.graph;

import com.amadeus.tools.MinHeap;
import com.amadeus.tools.UnionFind;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    public ListGraph() {
        super();
    }
    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }




    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {return;}

        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }
    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> formVertex = vertices.get(from);
        if (formVertex == null) {
            formVertex = new Vertex<>(from);
            vertices.put(from, formVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        //直接调用Set的add()若edge存在则更新weight,不存在则创建。
        edges.add(new Edge<>(formVertex, toVertex, weight));

    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);

        if (vertex == null) {return;}

        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            // 将当前遍历到的元素edge从集合vertex.outEdges中删掉
            iterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {return;}
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {return;}

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();

            if (visitor.visit(vertex.value)) {return;}

            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {return;}
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {return;}

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> vertexStack = new Stack<>();
        vertexStack.push(beginVertex);
        visitedVertices.add(beginVertex);

        while (!vertexStack.isEmpty()) {
            Vertex<V, E> vertex = vertexStack.pop();

            if (visitor.visit(vertex.value)) {return;}

            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                vertexStack.push(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }

    @Override
    public List<V> topologicalSort() {
        List<V> sortedList = new ArrayList<>();
        Queue<Vertex<V, E>> vertexQueue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> ins = new HashMap<>();

        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inEdges.size();
            if (in == 0) {
                vertexQueue.offer(vertex);
            } else {
                ins.put(vertex, in);
            }
        });

        while (!vertexQueue.isEmpty()) {
            Vertex<V, E> vertex = vertexQueue.poll();
            sortedList.add(vertex.value);

            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = ins.get(edge.to) - 1;

                if (toIn == 0) {
                    vertexQueue.offer(edge.to);
                }else {
                    ins.put(edge.to, toIn);
                }
            }
        }

        if (sortedList.size() != verticesSize()) {
//            return null;
            throw new RuntimeException("此图不是AOV网，无法使用拓扑排序");
        }else {
            return sortedList;
        }
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return Math.random() > 0.5 ? prim() : kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> it = vertices.values().iterator();
        if (!it.hasNext()) {return null;}

        Vertex<V, E> vertex = it.next();
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Set<Vertex<V, E>> addedVertices = new HashSet<>();
        addedVertices.add(vertex);

        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);

        while (addedVertices.size() < verticesSize()){
            Edge<V ,E> edge = heap.remove();
            if (addedVertices.contains(edge.to)) {continue;}

            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    private Set<EdgeInfo<V, E>> kruskal() {
        int edeesize = vertices.size() - 1;
        if (edeesize < 1) return null;

        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });

        while (!heap.isEmpty() && edgeInfos.size() < edeesize) {
            Edge<V, E> edge = heap.remove();
            if (uf.isSame(edge.from, edge.to)) {continue;}

            uf.union(edge.from, edge.to);
            edgeInfos.add(edge.info());
        }
        return edgeInfos;
    }


    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        Vertex(V value) {
            this.value = value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this(from, to, null);
        }

        EdgeInfo info() {
            return new EdgeInfo<V, E>(from.value, to.value, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
//            return Objects.hash(from, to);
            return from.hashCode() * 31 + to.hashCode();
        }
    }

}
