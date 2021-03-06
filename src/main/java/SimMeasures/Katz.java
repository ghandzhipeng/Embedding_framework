package SimMeasures;

import JudgeTools.Edge;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Katz extends SimBase{
    /**
     * S_j = \beta \cdot A \cdot S_j + \beta \cdot A_j (1)
     * S = \beta \cdot A \cdot S + \beta \cdot A. (2)
     * S = (1 - \beta cdot A)^{-1} * \beta \cdot A, which can be expanded as equation (2).
     * This is a paper.
     */
    double beta = 0.1;
    LinkedList<Edge> reverseGraph[];
    LinkedList<Edge> graph[];
    int ITER_NUM = 4;
    int node_num;
    public Katz(LinkedList<Edge> graph[], int node_num){
        this.graph = graph;
        this.reverseGraph = genReverseGraph(graph, node_num);
        this.node_num = node_num;
    }
    public Katz(LinkedList<Edge> graph[], int node_num, double beta, int iter_num){
        this(graph, node_num);
        this.beta = beta;
        this.ITER_NUM = iter_num;
    }
    @Override
    public double calculateSim(int from, int to){return 0;}

    @Override
    public double[] singleSourceSim(int qv){
        double rs[][] = new double[2][node_num];
        for(int kk = 0; kk < ITER_NUM; kk++){
            Arrays.fill(rs[kk & 1], 0);
            for(int i = 0; i< node_num; i++){
                Iterator iter = reverseGraph[i].iterator();

                while(iter.hasNext()) {
                    Edge tmp = (Edge) iter.next();
                    int neigh = tmp.getTo();
                    rs[kk & 1][i] += rs[1 - (kk & 1)][neigh];
                }
                rs[kk & 1][i] *= beta;
            }
            Iterator iterator = graph[qv].iterator();
            /**
             * if you use the result of reverse graph, the link predication result is very high.
             * Similar to that of Rooted PageRank, maybe 0.9 recall.
             * But it is a bug. May be we can add a issue that if there is an edge from u to v, then there
             * is possibly an edge from v to u.
             */
            while(iterator.hasNext()){
                Edge tmp = (Edge)iterator.next();
                int neigh = tmp.getTo();
                rs[kk & 1][neigh] += 1 * beta;
            }

        }
//        for(int i=0;i< node_num; i++)
//            rs[ITER_NUM & 1][i] -= 1;
        return rs[ITER_NUM &1];
    }

}