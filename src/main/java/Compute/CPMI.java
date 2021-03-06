package Compute;

import JudgeTools.MyBase;
import SimMeasures.PMI;
import com.beust.jcommander.Parameter;


import java.io.IOException;

public class CPMI extends ComputeBase{
    @Parameter(names = "--cds")
    protected float cds;
    PMI pmi;
    public CPMI(String []argv) throws IOException{
        super(argv);
        pmi = new PMI(weighted_graph, node_num, cds);
    }
    public double[] singleSourceSim(int qv){
        return pmi.singleSourceSim(qv);
    }

    public static void main(String []args) throws IOException{
        String argv[] = {"--path_train_data", "data/toy.edgelist",
                "--path_simi", "data/rpr.simi",
                "--node_num", "6",
                "--thread_num", "1",
                "--cds", "1",
        };

        if(MyBase.TEST_MODE)
            new CPMI(argv).run();
        else
            new CPMI(args).run();
//        double []res = new CPMI(argv).singleSourceSim(0);
//        for(int i = 0; i< res.length; i++)
//            System.out.print(res[i] + " ");
//        System.out.print("\n");
    }
}