package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

/**
 * Created by shurik on 08.08.2017.
 */
public class TestBase {


   protected Executor getExecutor() {
      return  Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
   }

   protected boolean isIssueOpen(int id) throws IOException {
      Executor ex = getExecutor();
      String json = ex.execute(Request.Get("http://demo.bugify.com/api/issues/"+id+".json"))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      String status =  parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
      if (status.equals("resolved")) return  false;
      else return true;
   }

   public void skipIfNotFixed(int issueId) throws IOException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

}
