package EmbeddingTools;

import SimMeasures.PPR;

import java.io.IOException;

public class RPR_FrameWork extends FrameWork{
    PPR ppr;
    public RPR_FrameWork(){
        ppr = new PPR(train_graph, node_num);
    }

    double[] singleSourceSim(int qv){
        return ppr.singleSourceSim(qv);
    }


    public static void main(String []args) throws IOException{
        String argv[] = {"--path_train_data", "data/arxiv_adj_train.edgelist",
                "--path_source_vec", "res/arxiv_trainout_ppr_embedding_source_vec",
                "--path_dest_vec", "res/arxiv_trainout_ppr_embedding_dest_vec",
                "--node_num", "5242",
                "--layer_size", "64",
                "--neg_sample", "5",
                "--iter", "3",
        };
        RPR_FrameWork rpr = new RPR_FrameWork();
        if(rpr.TEST_MODE)
            rpr.run(argv);
        else
            rpr.run(args);
    }
}