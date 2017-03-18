package com.cracowgo.cracowgo.server.listeners;

import com.cracowgo.cracowgo.server.entities.Tag;

/**
 * Created by Mohru on 18.03.2017.
 */

public interface GetTagsSubscriberListener {

    void onGetTagsCompleted(Tag[] tags);

    void onGetTagsError();

    void onConnectionError();

    void onUnknownError();
}
