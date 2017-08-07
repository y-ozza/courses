package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static java.math.BigInteger.valueOf;

/**
 * Created by shurik on 05.08.2017.
 */
public class SoapHelper {


  public ApplicationManager app;

   public SoapHelper(ApplicationManager app) {
      this.app = app;
   }

   public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnectPort();
      ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
      return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
              .collect(Collectors.toSet());

   }

   private MantisConnectPortType getMantisConnectPort() throws ServiceException, MalformedURLException {
//      return new MantisConnectLocator()
//                 .getMantisConnectPort(new URL("http://localhost:8081/mantisbt-1.2.19/api/soap/mantisconnect.php"));
      return new MantisConnectLocator()
              .getMantisConnectPort(new URL(app.getProperty("soap.Url")));

   }

   public Issue createIssue(Issue newIssue) throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnectPort();
      String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(newIssue.getProject().getId()));
      IssueData issue = new IssueData();
      issue.setSummary(newIssue.getSummary());
      issue.setDescription(newIssue.getDescription());
      issue.setProject(new ObjectRef(BigInteger.valueOf(newIssue.getProject().getId()), newIssue.getProject().getName()));
      issue.setCategory(categories[0]);
      BigInteger issueId = mc.mc_issue_add("administrator", "root", issue);
      IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
      return  new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
              .withDescription(createdIssueData.getDescription())
              .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                      .withName(createdIssueData.getProject().getName()));

   }

   public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnectPort();
//      IssueData issueData = mc.mc_issue_get("administrator", "root", new BigInteger("0000001"));
      IssueData issueData = mc.mc_issue_get("administrator", "root", new BigInteger(valueOf(issueId)));
      String s = issueData.getStatus().getName();
      return issueData.getStatus().getName();
   }

}

