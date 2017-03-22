package EmbeddingTools;


import SimMeasures.Katz;
import SimMeasures.PersonalizedPageRank;

import java.io.IOException;

public class KatzSampling extends SamplingFrameWork{
    Katz katz;
    public KatzSampling(String []argv) throws IOException{
        super(argv);
        katz = new Katz(train_graph, node_num);
    }

    double[] singleSourceSim(int qv){
        return katz.singleSourceSim(qv);
    }


    public static void main(String []args) throws IOException{
        String argv[] = {"--path_train_data", "data/arxiv_adj_train.edgelist",
                "--path_source_vec", "res/arxiv_trainout_ppr_embedding_source_vec",
                "--path_dest_vec", "res/arxiv_trainout_ppr_embedding_dest_vec",
                "--node_num", "5242",
                "--layer_size", "64",
                "--neg_sample", "5",
                "--iter", "10",
                "--learning_rate", "0.02f",
                "--threshold", "0.01",
                "--debug",
        };

        if(EmbeddingBase.TEST_MODE)
            new KatzSampling(argv).run();
        else
            new KatzSampling(args).run();
    }
}