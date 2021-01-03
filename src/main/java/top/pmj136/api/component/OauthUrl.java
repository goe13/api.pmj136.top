package top.pmj136.api.component;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 彭明久
 * @since 2020-11-01
 */
@Component
@Data
public class OauthUrl {
    private final String gitee_client_id = "c0d4ddbdf5dc27245fad262ac4121550fa6c10d9a4460a310a1221040e4238ac";
    private final String gitee_client_secret = "0e5b08370d4fe994a4bb3fd0eaadc0fd92f9ad8adc55f886695463f7a62718ee";
    private final String gitee_cb_url = "https://api.pmj136.top/oauth/gitee/callback";

    private final String github_client_id = "77f728b21a32f4fee322";
    private final String github_client_secret = "0e35b26e54dafd9f5bc6957835f2757ed8dfd424";

    private final String dingtalk_app_id = "dingoaive5bh1vdijmpzcw";
    private final String dingtalk_app_secret = "r_vdIsYxgSA5vaTKaV7P6dHbjiXdnhLVlEM2F3X1HkFcWd7ypQQDE9sQfCEyLjN4";

    public String getGiteeAccessUrl(String code) {
        return "https://gitee.com/oauth/token" +
                "?grant_type=authorization_code" +
                "&code=" + code +
                "&client_id=" + gitee_client_id +
                "&redirect_uri=" + gitee_cb_url +
                "&client_secret=" + gitee_client_secret;
    }

    public String getGiteeInfoUrl(String accessToken) {
        return "https://gitee.com/api/v5/user?access_token=" + accessToken;
    }

    public String getGithubAccessUrl(String code) {
        return "https://github.com/login/oauth/access_token" +
                "?client_id=" + github_client_id +
                "&client_secret=" + github_client_secret +
                "&code=" + code;
    }

    public String getGithubInfoUrl(String accessToken) {
        return "https://api.github.com/user?access_token=" + accessToken;
    }
}
