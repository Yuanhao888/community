package com.xu.community;

import com.xu.community.dao.DiscussPostMapper;
import com.xu.community.entity.DiscussPost;
import com.xu.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests {
    @Autowired(required = false)
    DiscussPostMapper discussPostMapper;

    @Test
    public void testselectPost() {
        List<DiscussPost> discussposts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost discusspost : discussposts) {
            System.out.println(discusspost);
        }
        int num = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(num);
    }

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void test(){
        String text="这里可以赌(博，吸毒";
        text=sensitiveFilter.filter(text);
        System.out.println(text);
    }
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString(){
        String redisKey="test:count";
        redisTemplate.opsForValue().set(redisKey,1);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));

    }

}
