package JudgeTools;
import com.beust.jcommander.Parameter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * In this package, train_graph is read as a HashSet[], because we need frequently ask:
 * if(train_graph[i].contains(j))
 */
abstract public class JudgeBase extends MyBase{
    public JudgeBase(String []argv) throws IOException{
        super(argv);
        train_graph = readEdgeListFromDisk(path_train_data, node_num);
        test_graph = readEdgeListFromDisk(path_test_data, node_num);

        /* generate the hashset of adjacency lists*/
        train_graph_ids = linkedList2hashSet(train_graph);
        test_graph_ids = linkedList2hashSet(test_graph);
    }
    public JudgeBase(){}

    @Parameter(names = "--path_train_data", description = "path of train_graph.edgelist")
    protected String path_train_data;

    @Parameter(names = "--path_test_data", description = "path of the test_graph.edgelist")
    protected String path_test_data;

    @Parameter(names = "--node_num", description = "number of nodes.")
    protected int node_num;

    @Parameter(names = "--thread_num", description = "number of threads.")
    protected int THREAD_NUM;

    LinkedList<Edge> train_graph[], test_graph[];
    HashSet<Integer> train_graph_ids[], test_graph_ids[];

    public double vec_multi_vec(double[] vi, double[] vj) {
        int len = vi.length;
        double score = 0;
        for (int kk = 0; kk < len; kk++) {
            score += vi[kk] * vj[kk];
        }
        return score;
    }

    HashSet<Integer>[] linkedList2hashSet(LinkedList<Edge> [] list){
        int ll = list.length;
        HashSet<Integer> [] rs = new HashSet[ll];
        int idx = 0;
        while(idx < ll){
            rs[idx] = new HashSet<Integer>(10);
            Iterator iter = list[idx].iterator();
            while(iter.hasNext()){
                Edge tmp = (Edge)iter.next();
                rs[idx].add(tmp.getTo());
            }
            idx ++;
        }
        return rs;
    }
    public static HashSet<Integer> getQueryNodes(String path) throws IOException {
        /**
         * return the nodes contained in the test_data.edgelist.
         * NOTE: only "from" nodes are contained.
         */
        HashSet<Integer> result = new HashSet<Integer>();
        BufferedReader bw = new BufferedReader(new FileReader(new File(path)));
        String line;
        String words[];
        while((line = bw.readLine()) != null){
            words = line.split("\\s+");
            result.add(Integer.parseInt(words[0]));
        }
        return result;
    }

    public static double[][] read_embeddings(String path)
            throws IOException {
        /**
         * the length of the embedding has to cover all the indices.
         * the embeddings files has the form of:
         *  Node_num layer_size
         *  node_id embedding_vectors\n
         *  ...
         */
        BufferedReader reader = new BufferedReader(new FileReader
                (new File(path)));
        String line;
        line = reader.readLine();
        String words[] = line.split(" ");
        int node_num = Integer.parseInt(words[0]);
        int layer_size = Integer.parseInt(words[1]);
        double [][]embedding = new double[node_num][layer_size];

        while ((line = reader.readLine()) != null) {
            words = line.split(" ");
            int id = Integer.parseInt(words[0]);
            for (int i = 1; i < words.length; ++i) {
                embedding[id][i - 1] = Double.parseDouble(words[i]);
            }
        }
        reader.close();
        return embedding;
    }
}