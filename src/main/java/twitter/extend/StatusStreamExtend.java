package twitter.extend;

import twitter4j.StatusListener;
import twitter4j.TwitterException;

import java.io.IOException;

public interface StatusStreamExtend {

        void next(StatusListener var1) throws TwitterException;

        void close() throws IOException;

}
