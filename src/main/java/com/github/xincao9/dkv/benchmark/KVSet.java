/*
 * Copyright 2020 xincao9@gmail.com.
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
package com.github.xincao9.dkv.benchmark;

import com.alibaba.fastjson.JSONObject;
import com.github.xincao9.ptk.core.Logger;
import com.github.xincao9.ptk.core.Method;
import com.github.xincao9.ptk.core.annotation.Test;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author xincao9@gmail.com
 */
@Test(name = "kvset")
public class KVSet extends Method {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void exec(Object o) throws Exception {
        Integer id = (Integer)o;
        String value = RandomStringUtils.randomAscii(128);
        JSONObject data = new JSONObject();
        data.put("k", String.valueOf(id));
        data.put("v", value);
        RequestBody body = RequestBody.create(data.toJSONString(), Consts.JSON);
        Request request = new Request.Builder()
                .url(Consts.ENDPOINT_KV)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Logger.info(response.body().string());
        }
    }

}
