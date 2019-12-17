package com.atguigu.demogmall2.demogmall2;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.ServletSecurity;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GmallTest {
   @Autowired
   JestClient jestClient;

   @Test
    public void testEs() throws IOException {
        String query="{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"actorList.name\": \"张译\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(query).addIndex("movie_chn").addType("movie_type_chn").build();

        SearchResult result = jestClient.execute(search);

        List<SearchResult.Hit<HashMap, Void>> hits = result.getHits(HashMap.class);

        for (SearchResult.Hit<HashMap, Void> hit : hits) {
            HashMap source = hit.source;
            System.err.println("source = " + source);

        }

    }

}
