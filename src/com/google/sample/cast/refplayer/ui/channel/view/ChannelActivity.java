/*
 * Copyright (C) 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.sample.cast.refplayer.ui.channel.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.codesyntax.jarrion.service.messaging.JarrionMessagingService;
import com.google.sample.cast.refplayer.R;
import com.google.sample.cast.refplayer.navigation.ChannelActivityNavigator;

public class ChannelActivity extends AppCompatActivity implements DispatchKeyEventOwner {
    private String title;
    DispatchKeyEventListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        String jsonURL = getIntent().getStringExtra(JarrionMessagingService.KEY_CHANNEL_URL);
        String coverURL = getIntent().getStringExtra(JarrionMessagingService.KEY_CHANNEL_IMAGE_URL);
        String channelId = getIntent().getStringExtra(JarrionMessagingService.KEY_CHANNEL_ID);
        title = getIntent().getStringExtra(JarrionMessagingService.KEY_TITLE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, ChannelFragment.newInstance(channelId, jsonURL, title, coverURL));
        ft.commit();
    }

    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        boolean result = false;
        if (listener != null) {
            result = listener.dispatchKeyEvent(event);
        }
        return result || super.dispatchKeyEvent(event);
    }

    @Override
    public void setDispatchKeyEventListener(DispatchKeyEventListener listener) {
        this.listener = listener;
    }
}
