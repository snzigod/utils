package com.sn.utils.solr;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.alibaba.fastjson.JSON;

public class TestSolr {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String solrUrl = "http://172.16.24.114:8983/solr/james";
		SolrjUtil solrjUtil=new SolrjUtil(solrUrl);
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "test");
		docs.add(doc);
		
		solrjUtil.addDocs(docs);
		
		solrjUtil.deleteById("test");
		
		SolrQuery params = new SolrQuery();
		params.set("q", "id:t*");
		// params.set("q", "*:*");
		QueryResponse rsp = solrjUtil.queryDocs(params);
		SolrDocumentList sdocs = rsp.getResults();
		String json=JSON.toJSONString(sdocs);
		System.out.println(json);
	}

}
