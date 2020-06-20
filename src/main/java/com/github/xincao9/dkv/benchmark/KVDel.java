/*
 * Copyright 2019 xincao9@gmail.com.
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

import com.github.xincao9.ptk.core.Logger;
import com.github.xincao9.ptk.core.Method;
import com.github.xincao9.ptk.core.annotation.Test;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author xincao9@gmail.com
 */
@Test(name = "kvdel")
public class KVDel extends Method {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void exec(Object o) throws Exception {
        Integer id = (Integer)o;
        Request request = new Request.Builder()
                .url(String.format("%s/%d", Consts.ENDPOINT_KV, id))
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            Logger.info(response.body().string());
        }
    }

}
