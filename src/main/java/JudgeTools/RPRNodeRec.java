package JudgeTools;


import SimMeasures.RootedPageRank;

import java.io.IOException;

public class RPRNodeRec extends NodeRec {
    RootedPageRank rootedPageRank;
    public RPRNodeRec(String []argv) throws IOException{
        super(argv);
        rootedPageRank = new RootedPageRank(train_graph, node_num);
    }


    @Override
    double calculateScore(int i, int j) {
        return 0;
    }

    @Override
    double[] singleSourceScore(int qv){

//        double rs[] = ppr.singleSourceSim(qv);
//        int out_deg = train_graph[qv].size();
//        for(int i=0; i<rs.length; i++)
//            rs[i] *= Math.pow(out_deg, 1.05);
//        return rs;
//        // this did not improve the effect.
        return rootedPageRank.singleSourceSim(qv);
    }

    public static void main(String[] args) throws IOException {
        String argv[] = {"--path_train_data", "data/arxiv_adj_train.edgelist",
                "--path_test_data", "data/arxiv_adj_test.edgelist",
                "--topk", "10",
                "--debug",
                "--node_num", "5242",
                "--thread_num", "1",
        };

        if(JudgeBase.TEST_MODE)
            new RPRNodeRec(argv).run();
        else
            new RPRNodeRec(args).run();
    }

}