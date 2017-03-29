package SimMeasures;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import EmbeddingTools.EmbeddingBase;
public class Distributions{
    public static void main(String []args) throws IOException {
        String path_aa_dis = "simi/aa";
        String path_cocitation_dis = "simi/cocitation";
        String path_katz_dis = "simi/katz";
        String path_ppr_dis = "simi/ppr";
        String path_simrank_dis = "simi/simrank";
        String path_combine_dis = "simi/combine";
        String path_preferAttach = "simi/preferAttach";

        String path_train_data = "data/arxiv_adj_train.edgelist";
        int node_num = 5242;

        LinkedList<Integer> train_graph[] = EmbeddingBase.readEdgeListFromDisk(path_train_data, node_num);

        Random random = new Random(System.currentTimeMillis());
        int cnt = 0;
        final int qv_num = 50;
        int qvs[] = new int[qv_num];
        while (cnt < qv_num) {
            qvs[cnt++] = random.nextInt(node_num);
        }

        write_dis_to_disk(new AA(train_graph, node_num), qvs, path_aa_dis);
        write_dis_to_disk(new CoCitation(train_graph, node_num), qvs, path_cocitation_dis);
        write_dis_to_disk(new Katz(train_graph, node_num),qvs, path_katz_dis);
        write_dis_to_disk(new PersonalizedPageRank(train_graph, node_num), qvs, path_ppr_dis);
        write_dis_to_disk(new SimRank(train_graph, node_num), qvs, path_simrank_dis);
        write_dis_to_disk(new PreferAttach(train_graph, node_num), qvs, path_preferAttach);
    }
    static void write_dis_to_disk(SimBase sm, int []qvs, String path) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
        int cnt = 0, qv_num = qvs.length;
        while(cnt < qv_num){
            writeArrayToDisk(bw, sm.singleSourceSim(qvs[cnt ++]));
        }
        bw.close();
    }
    static void writeArrayToDisk(BufferedWriter bw, double []score) throws IOException{
        for(int i=0; i < score.length; i++){
            bw.write(Double.toString(score[i]) + " ");
        }
        bw.write("\n");
    }
}