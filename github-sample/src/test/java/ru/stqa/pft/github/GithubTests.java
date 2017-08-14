package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by shurik on 08.08.2017.
 */
public class GithubTests {

   @Test

   public void testCommits() throws IOException {


      //4f4d18d62d8de638a1787c1354e6584ea93c35e9  token
      Github github = new RtGithub("4f4d18d62d8de638a1787c1354e6584ea93c35e9");
      RepoCommits commits = github.repos().get(new Coordinates.Simple("y-ozza", "courses")).commits();
//      ImmutableMap<String, String> iterator = new ImmutableMap.Builder<String, String>().build();
      HashMap<String, String > iterator = new HashMap<String, String>();
      for (RepoCommit commit : commits.iterate(iterator)) {
         System.out.println(new RepoCommit.Smart(commit).message());
      }
   }
}
