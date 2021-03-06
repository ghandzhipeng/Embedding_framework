#! /bin/bash
# sampling based methods
echo "*********************************************************"
path_source_vec=$katz_source_vec
path_dest_vec=$katz_dest_vec
echo "katz embedding"
katz_sampling --debug

path_dest_vec="no_input_dest"
echo "katz embedding node_rec"
embedding_node_rec --debug

echo "kat node_rec"
katz_node_rec --debug
rkatz_node_rec --debug

echo "*********************************************************"
path_source_vec=$app_source_vec
path_dest_vec=$app_dest_vec
echo "app embedding"
app_embedding --debug

path_dest_vec="no_input_dest"
echo "app embedding node_rec"
embedding_node_rec --debug

path_source_vec=$rpr_sampling_source_vec
path_dest_vec=$rpr_sampling_dest_vec
echo "rpr embedding"
rpr_sampling --debug

path_dest_vec="no_input_dest"
echo "rpr sampling node_rec"
embedding_node_rec --debug

echo "rpr node_rec"
rpr_node_rec --debug
echo "rrpr node_rec"
rrpr_node_rec --debug

echo "*********************************************************"
path_source_vec=$wrpr_sampling_source_vec
path_dest_vec=$wrpr_sampling_dest_vec
echo "wrpr embedding"
wrpr_sampling --debug

path_dest_vec="no_input_dest"
echo "wrpr embedding node_rec"
embedding_node_rec --debug

echo "wrpr node_rec"
wrpr_node_rec --debug

echo "*********************************************************"
path_source_vec=$simrank_source_vec
path_dest_vec=$simrank_dest_vec
echo "simrank embedding"
simrank_sampling --debug

path_dest_vec="no_input_dest"
echo "simrank embedding node_rec"
embedding_node_rec --debug

echo "simrank node_rec"
simrank_node_rec --debug


echo "*********************************************************"
path_source_vec=$common_neighbors_source_vec
path_dest_vec=$common_neighbors_dest_vec
echo "common neighbors embedding"
common_neighbors_sampling --debug

path_dest_vec="no_input_dest"
echo "common_neighbors embedding node_rec"
embedding_node_rec --debug

echo "common neighbors node_rec"
common_neighbors_node_rec --debug

echo "*********************************************************"
path_source_vec=$jaccard_source_vec
path_dest_vec=$jaccard_dest_vec
echo "jaccard embedding"
jaccard_sampling --debug

path_dest_vec="no_input_dest"
echo "jaccard embedding node_rec"
embedding_node_rec --debug

echo "jaccard node_rec"
jaccard_node_rec --debug

echo "*********************************************************"
path_source_vec=$aa_source_vec
path_dest_vec=$aa_dest_vec
echo "aa embedding"
aa_sampling --debug

path_dest_vec="no_input_dest"
echo "aa embedding node_rec"
embedding_node_rec --debug

echo "aa node_rec"
aa_node_rec --debug
echo "*********************************************************"

# matrix factorization based methods
echo "*********************************************************"
path_source_vec=$rpr_mf_source_vec
path_dest_vec=$rpr_mf_dest_vec
rpr_mf
path_dest_vec="no_input_dest"
embedding_node_rec
echo "*********************************************************"
echo "*********************************************************"
path_source_vec=$jaccard_mf_source_vec
path_dest_vec=$jaccard_mf_dest_vec
jaccard_mf
path_dest_vec="no_input_dest"
embedding_node_rec
echo "*********************************************************"
