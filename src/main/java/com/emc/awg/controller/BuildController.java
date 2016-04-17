package com.emc.awg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>Provides build/git related details</pre>
 */
@RestController
public class BuildController {

    Map<String, Object> build = new HashMap<>();

    @Value("${git.commit.id.abbrev:unknown}")
    private String gitCommitAbbrevVal;
    private static String gitCommitAbbrevKey = "git.commit.id.abbrev";

    @Value("${git.commit.user.email:unknown}")
    private String gitCommitEmailVal;
    private static String gitCommitEmailKey = "git.commit.user.email";

    @Value("${git.commit.message.full:unknown}")
    private String gitCommitMsgVal;
    private static String gitCommitMsgKey = "git.commit.message.full";

    @Value("${git.commit.id:unknown}")
    private String gitCommitIdVal;
    private static String gitCommitIdKey = "git.commit.id";

    @Value("${git.commit.user.name:unknown}")
    private String gitCommitUsernameVal;
    private static String gitCommitUsernamekey = "git.commit.user.name";

    @Value("${git.commit.id.describe:unknown}")
    private String gitCommitDescVal;
    private static String gitCommitDescKey = "git.commit.id.describe";

    @Value("${git.build.user.email:unknown}")
    private String gitBuildEmailVal;
    private static String gitBuildEmailKey = "git.build.user.email";

    @Value("${git.branch:unknown}")
    private String gitBranchVal;
    private static String gitBranchKey = "git.branch";

    @Value("${git.commit.time:unknown}")
    private String gitCommitTimeVal;
    private static String gitCommitTimeKey = "git.commit.time";

    @Value("${git.build.time:unknown}")
    private String gitBuildTimeVal;
    private static String gitBuildTimeKey = "git.build.time";

    @Value("${git.remote.origin.url:unknown}")
    private String gitOriginVal;
    private static String gitOriginKey = "git.remote.origin.url";

    @Value("${maven.version:unknown}")
    private String mavenVersionVal;
    private static String mavenVersionKey = "maven.version";

    @Value("${maven.artifactId:unknown}")
    private String mavenArtifactIdVal;
    private static String mavenArtifactIdKey = "maven.artifactId";

    @Value("${maven.groupId:unknown}")
    private String mavenGroupIdVal;
    private static String mavenGroupIdKey = "maven.groupId";

    @Value("${ci.build.number:unknown}")
    private String ciBuildVal;
    private static String ciBuildKey = "ci.build.number";

    @PostConstruct
    public void setup() {

        build.put(mavenVersionKey, mavenVersionVal);
        build.put(mavenArtifactIdKey, mavenArtifactIdVal);
        build.put(mavenGroupIdKey, mavenGroupIdVal);
        build.put(ciBuildKey, ciBuildVal);
        build.put(gitCommitAbbrevKey, gitCommitAbbrevVal);
        build.put(gitCommitEmailKey, gitCommitEmailVal);
        build.put(gitCommitMsgKey, gitCommitMsgVal);
        build.put(gitCommitIdKey, gitCommitIdVal);
        build.put(gitCommitUsernamekey, gitCommitUsernameVal);
        build.put(gitCommitDescKey, gitCommitDescVal);
        build.put(gitBuildEmailKey, gitBuildEmailVal);
        build.put(gitBranchKey, gitBranchVal);
        build.put(gitCommitTimeKey, gitCommitTimeVal);
        build.put(gitBuildTimeKey, gitBuildTimeVal);
        build.put(gitOriginKey, gitOriginVal);
    }


    @RequestMapping("/build")
    public Map<String, Object> build() {
        return build;
    }

}
