package com.sn.utils.solr;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

/**
 * solrj工具类
 * @author xlwang9
 */
public class SolrjUtil {
	
	private SolrClient client;//solr客户端

	public SolrjUtil(String solrUrl) {
		this.client = createNewSolrClient(solrUrl);
	}
	/**
	 * 创建solr客户端
	 * @param solrUrl
	 * @return
	 */
	private SolrClient createNewSolrClient(String solrUrl) {
		try {
			HttpSolrClient client = new HttpSolrClient(solrUrl);
			client.setConnectionTimeout(30000);
			client.setDefaultMaxConnectionsPerHost(100);
			client.setMaxTotalConnections(100);
			client.setSoTimeout(30000);
			return client;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
    /**
     * 关闭客户端
     */
	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新或增加索引数据
	 * @param docs
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public int addDocs(Collection<SolrInputDocument> docs) throws SolrServerException, IOException {
		client.add(docs);
		client.commit();
		return  docs.size();
	}
	/**
	 * 更新或增加单个索引数据
	 * @param doc
	 */
	public void addDoc(SolrInputDocument doc) {
		try {
			client.add(doc);
			client.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 搜索
	 * @param params
	 * @return
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public QueryResponse queryDocs(SolrQuery params) throws SolrServerException, IOException {
			return client.query(params);
	}
	/**
	 * 根据id删除索引数据
	 * @param id 
	 */
	public void deleteById(String id) throws SolrServerException, IOException {
		client.deleteById(id);
		client.commit();
	}
	/**
	 * 根据条件删除索引数据
	 * @param queryCon
	 */
	public void deleteByQuery(String queryCon) {
		try {
			client.deleteByQuery(queryCon);
			client.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
