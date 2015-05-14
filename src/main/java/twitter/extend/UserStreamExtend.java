package twitter.extend;

import twitter4j.TwitterException;
import twitter4j.UserStreamListener;

import java.io.IOException;

/**
 * Created by carlaregadas on 13-05-2015.
 */
public interface UserStreamExtend extends StatusStreamExtend{

        void next(UserStreamListener var1) throws TwitterException;

        void close() throws IOException;

}
