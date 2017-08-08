package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by shurik on 07.08.2017.
 */
public class RestTests extends TestBase{

   @Test

   public  void testCreateIssue() throws IOException {
      skipIfNotFixed(1);
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("Test Subj").withDescription("Test Descr");
      int id = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(id));
      assertEquals(newIssues, oldIssues);


   }

   private int createIssue(Issue newIssue) throws IOException {
      Executor ex = getExecutor();
      String json = ex.execute(Request.Post("http://demo.bugify.com/api/issues.json")
              .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),new BasicNameValuePair("description", newIssue.getDescription())))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      return parsed.getAsJsonObject().get("issue_id").getAsInt();

   }

   private Set<Issue> getIssues() throws IOException {
      Executor ex = getExecutor();
      String json = ex.execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
   }


}
