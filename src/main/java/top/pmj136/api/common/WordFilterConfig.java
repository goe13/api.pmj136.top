package top.pmj136.api.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.pmj136.api.wordfilter.WordContext;
import top.pmj136.api.wordfilter.WordFilter;

/**
 * @author 彭明久
 * @since 2020-12-22
 */
@Configuration
public class WordFilterConfig {

    @Bean
    public WordFilter wordFilter() {
        WordContext context = new WordContext("/blacklist.txt", "/whitelist.txt");
        return new WordFilter(context);
    }
}
